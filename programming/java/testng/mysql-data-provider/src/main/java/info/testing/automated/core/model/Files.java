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
@Table(name = "FILES")
public class Files extends BaseEntity {

    private static final long serialVersionUID = 5605519498078496021L;

    @Column
    private String name;

    @Column
    private String path;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "[file name : " + getName() +
                "; path : " + getPath() + "]";
    }
}
