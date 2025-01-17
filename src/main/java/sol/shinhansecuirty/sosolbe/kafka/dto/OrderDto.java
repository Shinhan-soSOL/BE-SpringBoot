package sol.shinhansecuirty.sosolbe.kafka.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@JsonSerialize
@JsonDeserialize
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private int userId;
    private int accountId;
    private String stockCode;
    private String stockName;
    private Integer quantity;
    private int currentBalance;
}
