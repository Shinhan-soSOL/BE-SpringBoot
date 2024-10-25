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
@Table(name="asset")
public class Asset {

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account accountId;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int assetId;
    private String stockCode;
    private String stockName;
    private int quantity;
    private double averagePrice;
}
