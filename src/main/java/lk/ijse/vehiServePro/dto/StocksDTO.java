package lk.ijse.vehiServePro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StocksDTO {
    private String id;
    private String name;
    private String price;
    private String remain;
    private String user;
}
