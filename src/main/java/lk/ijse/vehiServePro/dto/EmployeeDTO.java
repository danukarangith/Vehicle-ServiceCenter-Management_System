package lk.ijse.vehiServePro.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeDTO {
    private String name;
    private String id;
    private String address;
    private String contact;
    private String type;
    private String user;
}
