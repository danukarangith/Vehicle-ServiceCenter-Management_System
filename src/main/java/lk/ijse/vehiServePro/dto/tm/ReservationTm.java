package lk.ijse.vehiServePro.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReservationTm {
    private String id;
    private String email;
    private String vehNum;
    private String date;
    private String time;
}
