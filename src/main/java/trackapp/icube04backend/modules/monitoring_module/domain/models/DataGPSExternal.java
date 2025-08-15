package trackapp.icube04backend.modules.monitoring_module.domain.models;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataGPSExternal {
    private Long id;
    private String protocol;
    private Integer deviceId;
    private LocalDateTime serverTime;
    private LocalDateTime deviceTime;
    private LocalDateTime fixTime;
    private Boolean valid;
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Double speed;
    private Double course;
    private String address;
    private String attributes;
    private Double accuracy;
    private String network;
    private String nearestPoint;
    private Boolean proccesNearPoint;
    private Boolean proccesGeofences;
    private Integer geofencesId;
}
