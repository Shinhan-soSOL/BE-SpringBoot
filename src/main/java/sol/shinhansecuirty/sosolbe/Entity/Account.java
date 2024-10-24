package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Builder
@Table(name="account_list")
public class Account {

    private int account_id;
    private String type;
    private String account;
    private int balance;
    private int balanceSize;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
}
