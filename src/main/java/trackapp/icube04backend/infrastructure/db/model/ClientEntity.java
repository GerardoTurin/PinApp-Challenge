package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.client_module.domain.models.Client;

import java.time.LocalDate;

@Table(name = "clients")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer age;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "company_id")
    private Long companyId;

    public static ClientEntity convertFromDomain(Client client) {
        return ClientEntity.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .age(client.getAge())
                .dateOfBirth(client.getDateOfBirth() != null ? LocalDate.parse(client.getDateOfBirth().toString()) : null) // Convertir LocalDate a String
                .userId(client.getUserId())
                .companyId(client.getCompanyId())
                .build();
    }

    public Client convertToDomain() {
        return Client.builder()
                .id(this.getId())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .age(this.getAge())
                .dateOfBirth(this.getDateOfBirth() != null ? LocalDate.parse(this.getDateOfBirth().toString()) : null) // Convertir String a LocalDate
                .userId(this.getUserId())
                .companyId(this.getCompanyId())
                .build();
    }
}
