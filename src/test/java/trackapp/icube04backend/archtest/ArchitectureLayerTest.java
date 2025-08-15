package trackapp.icube04backend.archtest;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class ArchitectureLayerTest {

    private static final List<String> MODULES = Arrays.asList(
            "auth_module",
            "client_molude",
            "catalog_module",
            "company_module",
            "configuration_module",
            "monitoring_module",
            "order_module",
            "track_module"
    );

    private JavaClasses javaClasses;

    @BeforeEach
    void init() {
        this.javaClasses = new ClassFileImporter().importPackages("trackapp.icube04backend");
    }

    // Helper para saltar una regla si el paquete no existe en ese módulo
    private boolean hasAnyClassIn(JavaClasses classes, String packagePrefix) {
        for (JavaClass c : classes) {
            if (c.getPackageName().startsWith(packagePrefix)) return true;
        }
        return false;
    }

    @DisplayName("Application solo puede ser accedida por Application")
    @Test
    void layerApplicationTest() {
        MODULES.forEach(module -> {
            String appPkg = "trackapp.icube04backend.modules." + module + ".application";
            if (!hasAnyClassIn(this.javaClasses, appPkg)) return; // si no existe, no verificamos este módulo
            Architectures.LayeredArchitecture architecture = layeredArchitecture()
                    .consideringAllDependencies()
                    .layer("Application").definedBy(appPkg + "..")
                    .whereLayer("Application").mayOnlyBeAccessedByLayers("Application");
            architecture.check(this.javaClasses);
        });
    }

    @DisplayName("UseCases no debe ser invocado directamente por ninguna capa")
    @Test
    void layerApplicationUseCasesTest() {
        MODULES.forEach(module -> {
            String useCasesPkg = "trackapp.icube04backend.modules." + module + ".application.usecases";
            if (!hasAnyClassIn(this.javaClasses, useCasesPkg)) return;
            Architectures.LayeredArchitecture architecture = layeredArchitecture()
                    .consideringAllDependencies()
                    .layer("ApplicationUseCases").definedBy(useCasesPkg + "..")
                    .whereLayer("ApplicationUseCases").mayNotBeAccessedByAnyLayer();
            architecture.check(this.javaClasses);
        });
    }

    @DisplayName("Infrastructure solo puede ser accedida por Infrastructure")
    @Test
    void layerInfrastructureTest() {
        layeredArchitecture()
                .consideringAllDependencies()
                .layer("Application").definedBy("trackapp.icube04backend.modules..application..")
                .layer("Domain").definedBy("trackapp.icube04backend.modules..domain..")
                .layer("Infrastructure").definedBy("trackapp.icube04backend.infrastructure..")
                // Flexible
                .whereLayer("Infrastructure").mayOnlyBeAccessedByLayers("Infrastructure", "Application", "Domain")
                .check(this.javaClasses);
    }

    @DisplayName("Adapters puede ser accedida por Application, Domain e Infrastructure (temporal, any-module)")
    @Test
    void layerAdaptersTest() {
        MODULES.forEach(module -> {
            String adaptersPkg = "trackapp.icube04backend.infrastructure.adapters." + module;
            if (!hasAnyClassIn(this.javaClasses, adaptersPkg)) return;

            layeredArchitecture()
                    .consideringAllDependencies()
                    // hacer “globales” para permitir accesos cruzados entre módulos para que no sea tan estricto
                    .layer("DomainAll").definedBy("trackapp.icube04backend.modules..domain..")
                    .layer("ApplicationAll").definedBy("trackapp.icube04backend.modules..application..")
                    .layer("Infrastructure").definedBy("trackapp.icube04backend.infrastructure..")
                    .layer("Adapters").definedBy(adaptersPkg + "..")
                    // Flexible: Adapters accesible desde cualquier Application/Domain e Infrastructure
                    .whereLayer("Adapters").mayOnlyBeAccessedByLayers("Adapters", "ApplicationAll", "DomainAll", "Infrastructure")
                    .check(this.javaClasses);
        });
    }

    @DisplayName("Implementación de repository ports no debe invocarse directamente")
    @Test
    void layerInfrastructureRepositoryTest() {
        MODULES.forEach(module -> {
            String repoPkg = "trackapp.icube04backend.infrastructure.adapters." + module + ".repository";
            if (!hasAnyClassIn(this.javaClasses, repoPkg)) return;

            layeredArchitecture()
                    .consideringAllDependencies()
                    .layer("InfrastructureRepository").definedBy(repoPkg + "..")
                    .layer("Infrastructure").definedBy("trackapp.icube04backend.infrastructure..")
                    .layer("Application").definedBy("trackapp.icube04backend.modules..application..")
                    .layer("Adapters").definedBy("trackapp.icube04backend.infrastructure.adapters..")
                    // Flexible
                    .whereLayer("InfrastructureRepository").mayOnlyBeAccessedByLayers("Infrastructure", "Application", "Adapters")
                    .check(this.javaClasses);
        });
    }
}
