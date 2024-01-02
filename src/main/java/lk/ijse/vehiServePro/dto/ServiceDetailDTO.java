package lk.ijse.vehiServePro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ServiceDetailDTO {
    private String id;
    private String Cuname;
    private String number;
    private String date;
    private String time;
    private String detail;
    private String Ename;

}
