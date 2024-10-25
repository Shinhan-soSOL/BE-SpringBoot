package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="history")
public class History {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int historyId;
    private String tradeDate;
    private String stockName;
    private String stockCode;
    private int tradeCount;
    private int tradePrice;
    private int resultChange;
    private boolean isJandon;
}
