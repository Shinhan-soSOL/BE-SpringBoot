package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Data;

@Data
public class AccountRequestDTO {
    private Integer userId;

    private String bank;

    private String security;

    private Integer changeRange;

    private String stockCode;

    private String stockName;
}
