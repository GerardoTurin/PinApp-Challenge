package trackapp.icube04backend.modules.company_module.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {
    private String contentName;
    private byte[] content;
}
