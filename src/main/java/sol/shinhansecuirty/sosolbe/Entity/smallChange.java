package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name="small_change")
public class smallChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int changeId;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    private int total;
    private int current_balance;
}
