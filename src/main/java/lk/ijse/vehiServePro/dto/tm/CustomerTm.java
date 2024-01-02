package lk.ijse.vehiServePro.dto.tm;

import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerTm {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String email;

}
