package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mindrot.jbcrypt.BCrypt;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model {
    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false)
    public String password;

    public boolean enabled;

    @Column(nullable = false)
    public Long createdAt;

    @Column(nullable = false)
    public Long updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> roles;

    public static Finder<String, User> find = new Finder<>(String.class, User.class);

    public static User authenticate(String username, String password) {
        User user = find.where().eq("username", username).findUnique();
        final boolean matched = BCrypt.checkpw(password, user.password);
        if (matched) {
            return user;
        }
        return null;
    }
}
