package com.zxyun.common.db.mysql.covert;

import com.zxyun.common.db.mysql.annotation.TableName;
import com.zxyun.common.db.mysql.condition.GroupByCondition;
import com.zxyun.common.db.mysql.condition.OnCondition;
import com.zxyun.common.db.mysql.condition.OrderByCondition;
import com.zxyun.common.db.mysql.condition.QueryCondition;
import com.zxyun.common.db.mysql.enums.SqlKeyword;
import com.zxyun.common.db.mysql.enums.SqlLink;
import com.zxyun.common.db.mysql.enums.SqlMethod;
import com.zxyun.common.db.mysql.model.SelectSqlModel;
import com.zxyun.common.util.utils.ArgumentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:47
 */
public class SelectSqlCovert<T, U> {

    private static String selectSqlSegment = SqlMethod.SELECT.getSqlSegment();

    private static String totalColumn = "*";


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
        List<String> expressions = new ArrayList<>();
        expressions.add(selectSqlSegment);
        if (!ArgumentUtils.isEmpty(queryColumns)) {
            expressions.add(ArgumentUtils.join(queryColumns.toArray(new String[queryColumns.size()]), ","));
        } else {
            expressions.add(totalColumn);
        }
        TableName tableName = tableClass.getAnnotation(TableName.class);
        if (tableName == null) {
            //todo 日志打印
            return null;
        }
        expressions.add(SqlKeyword.FROM.getSqlSegment());
        expressions.add(tableName.value());
        if (sqlLink != null && joinClass != null && onCondition != null) {
            TableName joinTableName = joinClass.getAnnotation(TableName.class);
            if (joinTableName != null) {
                expressions.add(sqlLink.getSqlSegment());
                expressions.add(joinTableName.value());
                expressions.add(SqlKeyword.ON.getSqlSegment());
                expressions.add(onCondition.getConditionSql());
            }
        }
        if (queryCondition != null) {
            expressions.add(SqlKeyword.WHERE.getSqlSegment());
            expressions.add(queryCondition.getConditionSql());
        }
        if (groupByCondition != null) {
            expressions.add(SqlKeyword.GROUP_BY.getSqlSegment());
            expressions.add(groupByCondition.getConditionSql());
        }
        if (orderByCondition != null) {
            expressions.add(SqlKeyword.ORDER_BY.getSqlSegment());
            expressions.add(orderByCondition.getConditionSql());
        }
        return expressions.stream().collect(Collectors.joining(" "));
    }
}
