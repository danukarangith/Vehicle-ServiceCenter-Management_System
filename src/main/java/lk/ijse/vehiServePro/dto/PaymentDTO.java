package lk.ijse.vehiServePro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentDTO {
    private String id;
    private String name;
    private String amount;
    private String method;
    private String detail;
}
