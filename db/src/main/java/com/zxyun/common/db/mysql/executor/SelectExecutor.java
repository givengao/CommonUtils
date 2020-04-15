package com.zxyun.common.db.mysql.executor;

import com.zxyun.common.db.mysql.annotation.TableField;
import com.zxyun.common.db.mysql.annotation.TableId;
import com.zxyun.common.db.mysql.factory.MysqlConnection;
import com.zxyun.common.util.utils.ArgumentUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:15
 */
public class SelectExecutor {

    private static ConcurrentHashMap<Class<?>,Map<String, String>> tableColumnMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Class<?>,Map<String, String>> tableColumnDataTypeMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Class<?>,Map<String, Field>> tableColumnFieldMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Class<?>, String> tableMap = new ConcurrentHashMap<>();



    public <T> T select (String sql, Class<T> targetClass) {
        return null;
    }

    public  <T> List<T> selectList (String sql, Class<T> targetClass) {
//        if (ArgumentUtils.isBlank(sql)) {
//            return new ArrayList<>();
//        }
        try {
            Connection connection = MysqlConnection.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Map<String, String> columnMap = this.getTableColumnMap(targetClass);
            if (columnMap == null || columnMap.isEmpty()) {
                return new ArrayList<>();
            }
            List<T> list = new ArrayList<>();
            while(resultSet.next()){
                T t = targetClass.newInstance();
                columnMap.forEach((k, v) -> {
                    try {
                        setValue(targetClass, k, t, resultSet);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
                list.add(t);
            }
            connection.close();
            statement.close();
            resultSet.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
        }
        return null;
    }

    private Map<String, String> getTableColumnMap (Class<?> targetClass) {
        Map<String, String> columnMap = tableColumnMap.get(targetClass);
        Map<String, String> columnDataTypeMap = tableColumnDataTypeMap.get(targetClass);
        Map<String, Field> columnFieldMap = tableColumnFieldMap.get(targetClass);
        if (columnMap == null || columnMap.isEmpty()) {
            columnMap = new HashMap<>();
            columnDataTypeMap = new HashMap<>();
            columnFieldMap = new HashMap<>();
            Field[] fields = targetClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                String column;
                TableField tableField = field.getAnnotation(TableField.class);
                if (tableField == null) {
                    TableId tableId = field.getAnnotation(TableId.class);
                    column = tableId.value();
                    if (ArgumentUtils.isBlank(column)) {
                        continue;
                    }
                } else {
                    column = tableField.value();
                    if (ArgumentUtils.isBlank(column)) {
                        continue;
                    }
                }
                columnMap.put(column, field.getName());
                String type = null;
                if (field.getType().equals(Short.class)) {
                    type = Short.class.getSimpleName();
                } else if (field.getType().equals(Integer.class)) {
                    type = Integer.class.getSimpleName();
                } else if (field.getType().equals(Long.class)) {
                    type = Long.class.getSimpleName();
                } else if (field.getType().equals(Float.class)) {
                    type = Float.class.getSimpleName();
                } else if (field.getType().equals(Double.class)) {
                    type = Double.class.getSimpleName();
                } else if (field.getType().equals(String.class)) {
                    type = String.class.getSimpleName();
                } else {
                    type = BigDecimal.class.getSimpleName();
                }
                columnDataTypeMap.put(column, type);
                columnFieldMap.put(column, field);
            }
            tableColumnMap.put(targetClass, columnMap);
            tableColumnDataTypeMap.put(targetClass, columnDataTypeMap);
            tableColumnFieldMap.put(targetClass, columnFieldMap);
        }
        return columnMap;
    }

    private <T> void setValue (Class<?> targetClass, String column, T t, ResultSet resultSet) throws SQLException, IllegalAccessException {
       Map<String, String> columnDataTypeMap  = tableColumnDataTypeMap.get(targetClass);
       Map<String, Field> columnFieldMap = tableColumnFieldMap.get(targetClass);

       if (columnDataTypeMap != null && !columnDataTypeMap.isEmpty()) {
           String type = columnDataTypeMap.get(column);
           Field field = columnFieldMap.get(column);
           Object val;
           if (type.equals("Short")) {
               val = resultSet.getShort(column);
           } else if (type.equals("Integer")) {
               val = resultSet.getInt(column);
           } else if (type.equals("Long")) {
               val = resultSet.getLong(column);
           } else if (type.equals("Float")) {
               val = resultSet.getFloat(column);
           } else if (type.equals("Double")) {
               val = resultSet.getDouble(column);
           } else if (type.equals("String")) {
               val = resultSet.getString(column);
           } else {
               val = resultSet.getBigDecimal(column);
           }
           field.set(t, val);
       }
    }
}
