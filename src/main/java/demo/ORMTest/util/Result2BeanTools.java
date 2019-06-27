package demo.ORMTest.util;


import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * transfer ResultSet to list<Bean>
 *
 * @author fengcaiwen
 * @since 6/26/2019
 */
public class Result2BeanTools {

    /**
     * transfer ResultSet to List<Bean>
     *
     * @param rs    ResultSet
     * @param clazz type
     */
    public static <T> List<T> trans(ResultSet rs, Class<T> clazz) throws Throwable {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Moves the cursor forward one row from its current position.
        List<T> result = new ArrayList<>();
        while (rs.next()) {
            T t = clazz.getConstructor().newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (columnName.equals(field.getName())) {
                        Object columnValue = rs.getObject(columnName);
                        field.setAccessible(true);
                        field.set(t, columnValue);
                    }
                }
            }
            result.add(t);
        }
        return result;
    }
}
