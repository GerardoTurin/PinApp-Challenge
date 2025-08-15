package trackapp.icube04backend.modules.client_module.application;


import org.springframework.stereotype.Component;

@Component
public class LifeExpectancyPolicy {
    private static final int DEFAULT_EXPECTANCY_YEARS = 78;

    public int expectancyYears() {
        return DEFAULT_EXPECTANCY_YEARS;
    }
}
