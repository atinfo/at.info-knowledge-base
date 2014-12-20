package info.testing.automated.core.model;

import info.testing.automated.core.db.entities.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Author: Sergey Kuts
 */
@DynamicUpdate
@Entity
@Table(name = "IMPORT_DATA")
public class ImportData extends BaseEntity {

    private static final long serialVersionUID = 198687822641031913L;

    @OneToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private Users user;

    @OneToOne
    @JoinColumn(name = "FILE_ID", insertable = false, updatable = false)
    private Files file;

    public Users getUser() {
        return user;
    }

    public void setUser(final Users user) {
        this.user = user;
    }

    public Files getFile() {
        return file;
    }

    public void setFile(final Files file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "User : " + getUser() + "\n" +
                "File : " + getFile();
    }
}
