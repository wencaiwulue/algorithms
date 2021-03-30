package sql;


import com.google.common.base.Joiner;

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
    public static <T> List<T> transfer(ResultSet rs, Class<T> clazz) throws Throwable {
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

    public static <T> String generateSql(T t) {
        // todo optimize if class name is like AppUser
        StringBuilder sql = new StringBuilder("insert into " + t.getClass().getSimpleName().toLowerCase() + " set ");
        List<String> sub = new ArrayList<>();
        Class<?> aClass = t.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            Object value = null;
            try {
                value = field.get(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value != null) {
                sub.add(field.getName() + "='" + value + "'");
            }
        }

        sql.append(Joiner.on(",").join(sub));
        return sql.toString();

    }
}
