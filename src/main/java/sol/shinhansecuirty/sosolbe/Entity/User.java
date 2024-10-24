package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="user")
public class User {
    private final int userId;

    public User(int userId) {
        this.userId = userId;
    }
}
