package trackapp.icube04backend.infrastructure.configuration.db;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EntityScan("trackapp.icube04backend.infrastructure.db.model")
@EnableJpaRepositories(
        basePackages         = "trackapp.icube04backend.infrastructure.db.jpa",
        entityManagerFactoryRef = "localEntityManagerFactory",
        transactionManagerRef   = "localTransactionManager"
)
public class LocalDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties localDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "localDataSource")
    @Primary
    public DataSource localDataSource(
            @Qualifier("localDataSourceProperties") DataSourceProperties props) {
        return props.initializeDataSourceBuilder().build();
    }

    @Bean(name = "localEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("localDataSource") DataSource ds) {
        return builder
                .dataSource(ds)
                .packages("trackapp.icube04backend.infrastructure.db.model")
                .persistenceUnit("local")
                .build();
    }

    @Bean(name = "localTransactionManager")
    @Primary
    public PlatformTransactionManager localTransactionManager(
            @Qualifier("localEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }

}
