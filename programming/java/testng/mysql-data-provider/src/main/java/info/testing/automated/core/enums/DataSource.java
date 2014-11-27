package info.testing.automated.core.enums;

/**
 * Author: Sergey Kuts
 */
public enum DataSource {
    AUTOMATION_SOURCE("mysql-data-source.xml"),
    PRODUCTION_SOURCE("mysql-prod-data-source.xml");

    private String source;

    private DataSource(final String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
