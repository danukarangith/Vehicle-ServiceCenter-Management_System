package lk.ijse.vehiServePro.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class StockTm {

    private String id;
    private String name;
    private String price;
    private String remain;
    private String user;
}
