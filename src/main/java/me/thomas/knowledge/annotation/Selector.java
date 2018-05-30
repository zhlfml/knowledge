package me.thomas.knowledge.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.thomas.knowledge.annotation.def.Column;
import me.thomas.knowledge.annotation.def.Table;
import me.thomas.knowledge.annotation.entity.Entity;
import me.thomas.knowledge.utils.StringUtil;

/**
 * 自定义查询器
 *
 * @author zhaoxinsheng
 * @date 8/7/16.
 */
public class Selector {

    public String query(Entity entity) {
        StringBuilder sb = new StringBuilder();
        Class clazz = entity.getClass();
        if (clazz.isAnnotationPresent(Table.class)) {
            sb.append("SELECT * FROM ");
            Table table = (Table) clazz.getDeclaredAnnotation(Table.class);
            sb.append(table.value());
            sb.append(" WHERE 1 = 1 ");
            Field[] fields = clazz.getDeclaredFields(); // 所有申明的字段，包括private, protected, public。但是不包含父类继承的字段。
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    String getMethodName = "get" + StringUtil.upperCaseFirstLetter(field.getName());
                    Method method = null;
                    try {
                        method = clazz.getDeclaredMethod(getMethodName);
                        Object value = method.invoke(entity);
                        if (value == null || (value instanceof Integer && (Integer) value == 0)) {
                            continue;
                        }
                        Column column = field.getDeclaredAnnotation(Column.class);
                        sb.append(" AND ");
                        sb.append(column.value());
                        sb.append(" = ");
                        if (value instanceof String) {
                            sb.append("'");
                        }
                        sb.append(value.toString());
                        if (value instanceof String) {
                            sb.append("'");
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // 测试方法继承时，能否继承方法的注解。（该注解没有@Inherited）: 测试结果，可以继承。
        // 也就是说，方法上的注解能自动继承。与该注解是否有定义@Inherited无关。
        try {
            Method method = clazz.getMethod("getAdmin");
            if (method.isAnnotationPresent(Column.class)) {
                Column column = method.getAnnotation(Column.class);
                String value = column.value();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
