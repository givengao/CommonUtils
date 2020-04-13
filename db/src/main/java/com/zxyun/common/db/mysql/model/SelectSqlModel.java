package com.zxyun.common.db.mysql.model;

import com.zxyun.common.db.mysql.condition.GroupByCondition;
import com.zxyun.common.db.mysql.condition.OnCondition;
import com.zxyun.common.db.mysql.condition.OrderByCondition;
import com.zxyun.common.db.mysql.condition.QueryCondition;
import com.zxyun.common.db.mysql.enums.SqlLink;

import java.util.List;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:41
 */
public class SelectSqlModel<T, U> {

    private Class<T> tableClass;

    private Class<U> joinClass;

    private List<String> queryColumns;

    private SqlLink sqlLink;

    private OnCondition<T, U> onCondition;

    private QueryCondition<T,U> queryCondition;

    private GroupByCondition<T,U> groupByCondition;

    private OrderByCondition<T,U> orderByCondition;

    public Class<T> getTableClass() {
        return tableClass;
    }

    public void setTableClass(Class<T> tableClass) {
        this.tableClass = tableClass;
    }

    public Class<U> getJoinClass() {
        return joinClass;
    }

    public void setJoinClass(Class<U> joinClass) {
        this.joinClass = joinClass;
    }

    public List<String> getQueryColumns() {
        return queryColumns;
    }

    public void setQueryColumns(List<String> queryColumns) {
        this.queryColumns = queryColumns;
    }

    public SqlLink getSqlLink() {
        return sqlLink;
    }

    public void setSqlLink(SqlLink sqlLink) {
        this.sqlLink = sqlLink;
    }

    public OnCondition<T,U> getOnCondition() {
        return onCondition;
    }

    public void setOnCondition(OnCondition<T,U> onCondition) {
        this.onCondition = onCondition;
    }

    public QueryCondition<T,U> getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(QueryCondition<T,U> queryCondition) {
        this.queryCondition = queryCondition;
    }

    public GroupByCondition<T,U> getGroupByCondition() {
        return groupByCondition;
    }

    public void setGroupByCondition(GroupByCondition<T,U> groupByCondition) {
        this.groupByCondition = groupByCondition;
    }

    public OrderByCondition<T,U> getOrderByCondition() {
        return orderByCondition;
    }

    public void setOrderByCondition(OrderByCondition<T,U> orderByCondition) {
        this.orderByCondition = orderByCondition;
    }
}
