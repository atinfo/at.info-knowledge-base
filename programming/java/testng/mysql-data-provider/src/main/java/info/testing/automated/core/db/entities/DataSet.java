package info.testing.automated.core.db.entities;

import info.testing.automated.core.db.dao.DAO;

import java.util.List;

/**
 * Author: Sergey Kuts
 */
public class DataSet {

    private List<BaseEntity> fields;

    public DataSet(final List<BaseEntity> fields) {
        this.fields = fields;
    }

    public DataSet updateFieldsWith(final DAO dao) {
        for (BaseEntity entity : fields) {
            entity.setDAO(dao);
        }

        return this;
    }

    public List<BaseEntity> getFields() {
        return fields;
    }
}
