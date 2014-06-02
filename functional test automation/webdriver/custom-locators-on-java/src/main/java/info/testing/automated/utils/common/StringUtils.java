package info.testing.automated.utils.common;

/**
 * Author: Serhii Kuts
 */
public final class StringUtils {

    private StringUtils() {
    }

    public static String replaceWithValues(final String target, final String replaceToken, final String... values) {
        return String.format(target.replace("%", "%%").replace(replaceToken, "%s"), (Object[]) values);
    }
}
