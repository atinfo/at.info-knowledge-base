package info.testing.automated.core.db.entities;

import java.util.List;

/**
 * Author: Sergey Kuts
 */
public class DataSet {

    private List<Object> fields;

    public DataSet(final List<Object> fields) {
        this.fields = fields;
    }

    public List<Object> getFields() {
        return fields;
    }
}
