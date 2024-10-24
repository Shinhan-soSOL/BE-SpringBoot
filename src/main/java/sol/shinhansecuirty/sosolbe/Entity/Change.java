package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name="change")
public class Change {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int changeId;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    private int total;
    private int current_balance;
}
