package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name="history")
public class History {

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private String tradeDate;
    private String stockName;
    private String stockCode;
    private int tradeCount;
    private int tradePrice;
    private int resultChange;
    private boolean isJandon;
}
