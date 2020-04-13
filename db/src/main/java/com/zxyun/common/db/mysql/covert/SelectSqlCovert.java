package com.zxyun.common.db.mysql.covert;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zxyun.common.db.mysql.condition.GroupByCondition;
import com.zxyun.common.db.mysql.condition.OnCondition;
import com.zxyun.common.db.mysql.condition.OrderByCondition;
import com.zxyun.common.db.mysql.condition.QueryCondition;
import com.zxyun.common.db.mysql.enums.SqlKeyword;
import com.zxyun.common.db.mysql.enums.SqlLink;
import com.zxyun.common.db.mysql.enums.SqlMethod;
import com.zxyun.common.db.mysql.model.SelectSqlModel;
import com.zxyun.common.util.covert.Covert;
import com.zxyun.common.util.utils.ArgumentUtils;

import java.util.List;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:47
 */
public class SelectSqlCovert<T, U> implements Covert<SelectSqlModel<T, U>, String> {

    private static String selectSqlSegment = SqlMethod.SELECT.getSqlSegment();

    @Override
    public String covert(SelectSqlModel<T, U> selectSqlModel) {
        Class<T> tableClass = selectSqlModel.getTableClass();
        Class<U> joinClass = selectSqlModel.getJoinClass();
        List<String> queryColumns = selectSqlModel.getQueryColumns();
        SqlLink sqlLink = selectSqlModel.getSqlLink();
        OnCondition<T, U> onCondition = selectSqlModel.getOnCondition();
        QueryCondition<T,U> queryCondition = selectSqlModel.getQueryCondition();
        GroupByCondition<T,U> groupByCondition = selectSqlModel.getGroupByCondition();
        OrderByCondition<T,U> orderByCondition = selectSqlModel.getOrderByCondition();
        if (tableClass == null) {
            return null;
        }
        TableName tableName = tableClass.getAnnotation(TableName.class);
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(selectSqlSegment);
        if (!ArgumentUtils.isEmpty(queryColumns)) {
            sqlBuilder.append(ArgumentUtils.join(queryColumns.toArray(new String[queryColumns.size()]), ","));
        }
        sqlBuilder.append(SqlKeyword.FROM.getSqlSegment());
        sqlBuilder.append(tableName.value());
        if (sqlLink != null && joinClass != null && onCondition != null) {
            TableName joinTableName = joinClass.getAnnotation(TableName.class);
            sqlBuilder.append(sqlLink.getSqlSegment())
                    .append(" ")
                    .append(joinTableName.value())
                    .append(SqlKeyword.ON.getSqlSegment())
                    .append(onCondition.getConditionSql());
        }
        if (queryCondition != null) {
            sqlBuilder.append(SqlKeyword.WHERE.getSqlSegment());
            sqlBuilder.append(queryCondition.getConditionSql());
        }
        if (groupByCondition != null) {
            sqlBuilder.append(SqlKeyword.GROUP_BY.getSqlSegment());
            sqlBuilder.append(groupByCondition.getConditionSql());
        }
        if (orderByCondition != null) {
            sqlBuilder.append(SqlKeyword.ORDER_BY.getSqlSegment());
            sqlBuilder.append(orderByCondition.getConditionSql());
        }
        return sqlBuilder.toString();
    }
}
