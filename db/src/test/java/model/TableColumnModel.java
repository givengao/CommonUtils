package model;

import com.zxyun.common.db.mysql.annotation.TableField;
import com.zxyun.common.db.mysql.annotation.TableName;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/14 16:30
 */
@TableName("COLUMNS")
public class TableColumnModel {

    /**
     * 表所属的数据库名
     */
    @TableField("TABLE_SCHEMA")
    private String tableSchema;

    /**
     * 表名
     */
    @TableField("TABLE_NAME")
    private String tableName;

    /**
     * 字段名
     */
    @TableField("COLUMN_NAME")
    private String columnName;

    /**
     * 字段备注
     */
    @TableField("COLUMN_COMMENT")
    private String columnComment;

    /**
     * 字段类型
     */
    @TableField("DATA_TYPE")
    private String dataType;

    /**
     * 是否可为空 YES, NO
     */
    @TableField("IS_NULLABLE")
    private String isNull;

    public String getTableSchema() {
        return tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public String getDataType() {
        return dataType;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }
}
