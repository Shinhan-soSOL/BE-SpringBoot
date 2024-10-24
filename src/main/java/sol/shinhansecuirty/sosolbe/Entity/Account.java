package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Builder
@Table(name="account_list")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int account_id;
    private String type;
    private String account;
    private int balance;
    private int balanceSize;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
