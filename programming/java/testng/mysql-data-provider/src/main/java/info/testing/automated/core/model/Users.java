package info.testing.automated.core.model;

import info.testing.automated.core.db.entities.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Author: Sergey Kuts
 */
@DynamicUpdate
@Entity
@Table(name = "USERS")
public class Users extends BaseEntity {

    private static final long serialVersionUID = -1285369847251036803L;

    @Column
    private String email;

    @Column
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "[email : " + getEmail() +
                "; password : " + getPassword() + "]";
    }
}
