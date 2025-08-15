package trackapp.icube04backend.infrastructure.db.model;


import jakarta.persistence.*;
import lombok.*;
import trackapp.icube04backend.modules.order_module.domain.models.Booking;

import java.time.LocalDate;

@Table(name = "bookings")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "status_config_detail_id")
    private ConfigDetailEntity status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;


    public Booking convertToDomain(){
        return Booking.builder()
                .id(getId())
                .name(getName())
                .code(getCode())
                .status(getStatus() != null ? getStatus().convertToDomain() : null)
                .company(getCompany().convertToDomain())
                .updatedAt(getUpdatedAt())
                .build();
    }


    public static BookingEntity convertFromDomain(Booking booking){
        return BookingEntity.builder()
                .id(booking.getId())
                .name(booking.getName())
                .code(booking.getCode())
                .status(ConfigDetailEntity.convertFromDomain(booking.getStatus()))
                .company(CompanyEntity.convertFromDomain(booking.getCompany()))
                .updatedAt(booking.getUpdatedAt())
                .build();
    }
}
