package lk.ijse.vehiServePro.dto;

import lk.ijse.vehiServePro.dto.tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockManageDTO {
    private String userName;
    private String supplierId;
   // private LocalDate date;
    private List<CartTm> tmList = new ArrayList<>();
}
