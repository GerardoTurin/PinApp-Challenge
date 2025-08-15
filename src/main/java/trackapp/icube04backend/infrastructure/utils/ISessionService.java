package trackapp.icube04backend.infrastructure.utils;

import java.util.List;

public interface ISessionService {
    Long getCompanyId();

    String getUrlBase();

    List<Integer> getUsersCompanies();

    String getTenantId();

    Long getUserTypeId();

    Long getUserId();
}
