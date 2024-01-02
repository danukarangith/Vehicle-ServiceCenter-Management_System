package lk.ijse.vehiServePro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ReservationDTO {
    private String id;
    private String email;
    private String vehNum;
    private String date;
    private String time;
}
