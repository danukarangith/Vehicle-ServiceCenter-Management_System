package lk.ijse.vehiServePro.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class CartTm {
    private String stockId;
    private String stockName;
    private int qty;
    private double unitPrice;
    private double tot;
    private Button btn;
}

