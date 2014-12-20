package info.testing.automated.core.model;

import info.testing.automated.core.db.entities.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Sergey Kuts
 */
@DynamicUpdate
@Entity
@Table(name = "PRODUCTS")
public class Products extends BaseEntity {

    private static final long serialVersionUID = 3103488731618653860L;

    @Column
    private String name;

    @Column
    private int amount;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "[product name : " + getName() +
                "; amount : " + getAmount() + "]";
    }
}
