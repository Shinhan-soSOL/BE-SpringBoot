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
@Table(name="change")
public class Change {

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    private int total;
    private int current_balance;
}
