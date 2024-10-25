package sol.shinhansecuirty.sosolbe.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankUpdateDTO {

    private String accountNum;
    private int tradePrice;
}
