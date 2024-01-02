package lk.ijse.vehiServePro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class VehicleDTO {
    private String id;
    private String name;
    private String num;
    private String brand;
    private String Type;
}
