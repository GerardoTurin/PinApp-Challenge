package trackapp.icube04backend.modules.company_module.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileCompany {
    private String fileName;
    private byte[] file;
}
