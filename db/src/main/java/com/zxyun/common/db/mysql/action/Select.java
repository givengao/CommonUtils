package com.zxyun.common.db.mysql.action;

import com.zxyun.common.db.mysql.condition.GroupByCondition;
import com.zxyun.common.db.mysql.condition.OnCondition;
import com.zxyun.common.db.mysql.condition.OrderByCondition;
import com.zxyun.common.db.mysql.condition.QueryCondition;
import com.zxyun.common.db.mysql.covert.SelectSqlCovert;
import com.zxyun.common.db.mysql.enums.SqlLink;
import com.zxyun.common.db.mysql.factory.ExecutorFactory;
import com.zxyun.common.db.mysql.model.SelectSqlModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 10:59
 */
public class Select<T> {

    public <T> From<T> from (Class<T> table) {
        return new From<T>() {
            @Override
            public Column<T> find() {
                return new ColumnImpl<T>(table);
            }
        };
    }

    public interface Column<T> {

        Column<T> column (String column);

        <U> LeftJoin<T, U> leftJoin (Class<U> table);

        <U> RightJoin<T, U> rightJoin (Class<U> table);

        <U> InnerJoin<T, U> innerJoin (Class<U> table);

        <R> R to ();

        List<T> toList ();
    }

    public interface From<T> {
        Column<T> find ();
    }

    public interface LeftJoin<T, U> {
        On<T, U> on (OnCondition<T, U> onCondition);
    }

    public interface RightJoin<T, U> {
        On<T, U> on (OnCondition<T, U> onCondition);
    }

    public interface InnerJoin<T, U> {
        On<T, U> on (OnCondition<T, U> onCondition);
    }

    public interface On<T, U> {
        Where<T, U> where (QueryCondition<T, U> condition);
    }

    public interface Where<T, U> {

        GroupBy<T, U> groupBy (GroupByCondition<T, U> groupByCondition);

        OrderBy<T, U> orderBy (OrderByCondition<T, U> orderByCondition);
    }

    public interface GroupBy<T, U> {
        <R> R to ();

        OrderBy<T, U> orderBy (OrderByCondition<T, U> orderByCondition);
    }

    public interface OrderBy<T, U> {
        <R> R to ();

        List<T> toList ();
    }

    public interface To<R> {
        R parse ();
    }

    public static class ColumnImpl<T> implements Column<T> {

        private Class<T> tableClass;

        private List<String> columns;

        private SqlLink sqlLink;

        public ColumnImpl(Class<T> table) {
            this.tableClass = table;
            columns = new ArrayList<>();
        }

        @Override
        public Column<T> column(String column) {
            columns.add(column);
            return this;
        }

        @Override
        public <U> LeftJoin<T,U> leftJoin(Class<U> joinClass) {
            this.sqlLink = SqlLink.LEFT;
            return new LeftJoin<T,U>() {
                @Override
                public On<T,U> on(OnCondition<T, U> onCondition) {
                    return new OnImpl<T,U>(tableClass, joinClass, columns, sqlLink, onCondition);
                }
            };
        }

        @Override
        public <U> RightJoin<T,U> rightJoin(Class<U> joinClass) {
            this.sqlLink = SqlLink.RIGHT;
            return new RightJoin<T,U>() {
                @Override
                public On<T,U> on(OnCondition<T, U> onCondition) {
                    return new OnImpl<T,U>(tableClass, joinClass, columns, sqlLink, onCondition);
                }
            };
        }

        @Override
        public <U> InnerJoin<T,U> innerJoin(Class<U> joinClass) {
            this.sqlLink = SqlLink.INNER;
            return new InnerJoin<T,U>() {
                @Override
                public On<T,U> on(OnCondition<T, U> onCondition) {
                    return new OnImpl<T,U>(tableClass, joinClass, columns, sqlLink, onCondition);
                }
            };
        }

        @Override
        public <R> R to() {
            SelectSqlModel<T, Object> selectSqlModel = new SelectSqlModel<>();
            selectSqlModel.setTableClass(tableClass);
            selectSqlModel.setQueryColumns(columns);

            SelectSqlCovert<T, Object> selectSqlCovert = new SelectSqlCovert<>();
            String sql = selectSqlCovert.covert(selectSqlModel);
            System.out.println(sql);
            return (R)ExecutorFactory.getSelectExecutor().select(sql, tableClass);
        }

        @Override
        public List<T> toList() {
            SelectSqlModel<T, Object> selectSqlModel = new SelectSqlModel<>();
            selectSqlModel.setTableClass(tableClass);
            selectSqlModel.setQueryColumns(columns);

            SelectSqlCovert<T, Object> selectSqlCovert = new SelectSqlCovert<>();
            String sql = selectSqlCovert.covert(selectSqlModel);
            System.out.println(sql);
            return ExecutorFactory.getSelectExecutor().selectList(sql, tableClass);
        }
    }

    public static class OnImpl<T,U> implements On<T,U> {
        private Class<T> tableClass;

        private Class<U> joinClass;

        private List<String> columns;

        private SqlLink sqlLink;

        private OnCondition<T, U> onCondition;

        private QueryCondition<T,U> queryCondition;

        public OnImpl(Class<T> tableClass, Class<U> joinClass, List<String> columns, SqlLink sqlLink, OnCondition<T, U> onCondition) {
            this.tableClass = tableClass;
            this.joinClass = joinClass;
            this.columns = columns;
            this.sqlLink = sqlLink;
            this.onCondition = onCondition;
        }

        @Override
        public Where<T,U> where(QueryCondition<T,U> condition) {
            this.queryCondition = condition;
            return new WhereImpl<>(tableClass, joinClass, columns, sqlLink, onCondition, queryCondition);
        }
    }

    public static class WhereImpl<T,U> implements Where<T,U> {

        private Class<T> tableClass;

        private Class<U> joinClass;

        private List<String> columns;

        private SqlLink sqlLink;

        private OnCondition<T, U> onCondition;

        private QueryCondition<T,U> queryCondition;

        public WhereImpl(Class<T> tableClass, Class<U> joinClass, List<String> columns, SqlLink sqlLink, OnCondition<T, U> onCondition, QueryCondition<T,U> queryCondition) {
            this.tableClass = tableClass;
            this.joinClass = joinClass;
            this.columns = columns;
            this.sqlLink = sqlLink;
            this.queryCondition = queryCondition;
            this.onCondition = onCondition;
        }

        @Override
        public GroupBy<T,U> groupBy(GroupByCondition<T,U> groupByCondition) {
            return new GroupByImpl(tableClass, joinClass, columns, sqlLink, onCondition,queryCondition, groupByCondition);
        }

        @Override
        public OrderBy<T,U> orderBy(OrderByCondition<T,U> orderByCondition) {
            return new OrderByImpl<>(tableClass, joinClass, columns, sqlLink, onCondition,queryCondition,null, orderByCondition);
        }
    }

    public static class GroupByImpl<T,U> implements GroupBy<T,U> {
        private Class<T> tableClass;

        private Class<U> joinClass;

        private List<String> queryColumns;

        private SqlLink sqlLink;

        private OnCondition<T, U> onCondition;

        private QueryCondition<T,U> queryCondition;

        private GroupByCondition<T,U> groupByCondition;

        public GroupByImpl(Class<T> tableClass, Class<U> joinClass, List<String> queryColumns, SqlLink sqlLink, OnCondition<T,U> onCondition,
                           QueryCondition<T,U> queryCondition, GroupByCondition<T,U> groupByCondition) {
            this.tableClass = tableClass;
            this.joinClass = joinClass;
            this.queryColumns = queryColumns;
            this.sqlLink = sqlLink;
            this.onCondition = onCondition;
            this.queryCondition = queryCondition;
            this.groupByCondition = groupByCondition;
        }

        @Override
        public <R> R to() {
            SelectSqlModel<T, U> selectSqlModel = new SelectSqlModel<>();
            selectSqlModel.setTableClass(tableClass);
            selectSqlModel.setJoinClass(joinClass);
            selectSqlModel.setQueryColumns(queryColumns);
            selectSqlModel.setSqlLink(sqlLink);
            selectSqlModel.setOnCondition(onCondition);
            selectSqlModel.setQueryCondition(queryCondition);
            selectSqlModel.setGroupByCondition(groupByCondition);
            selectSqlModel.setOrderByCondition(null);

            SelectSqlCovert<T, U> selectSqlCovert = new SelectSqlCovert<>();
            String sql = selectSqlCovert.covert(selectSqlModel);
            System.out.println(sql);
            return (R)ExecutorFactory.getSelectExecutor().select(sql, tableClass);
        }

        @Override
        public OrderBy<T,U> orderBy(OrderByCondition<T, U> orderByCondition) {
            return new OrderByImpl(tableClass, joinClass, queryColumns, sqlLink, onCondition,queryCondition, groupByCondition, orderByCondition);
        }
    }

    public static class OrderByImpl<T,U> implements OrderBy<T,U> {

        private Class<T> tableClass;

        private Class<U> joinClass;

        private List<String> queryColumns;

        private SqlLink sqlLink;

        private OnCondition<T, U> onCondition;

        private QueryCondition<T,U> queryCondition;

        private GroupByCondition<T,U> groupByCondition;

        private OrderByCondition<T,U> orderByCondition;

        public OrderByImpl(Class<T> tableClass, Class<U> joinClass, List<String> queryColumns, SqlLink sqlLink, OnCondition<T,U> onCondition,
                           QueryCondition<T,U> queryCondition, GroupByCondition<T,U> groupByCondition, OrderByCondition<T,U> orderByCondition) {
            this.tableClass = tableClass;
            this.joinClass = joinClass;
            this.queryColumns = queryColumns;
            this.sqlLink = sqlLink;
            this.onCondition = onCondition;
            this.queryCondition = queryCondition;
            this.groupByCondition = groupByCondition;
            this.orderByCondition = orderByCondition;
        }

        @Override
        public <R> R to() {
            SelectSqlModel<T, U> selectSqlModel = new SelectSqlModel<>();
            selectSqlModel.setTableClass(tableClass);
            selectSqlModel.setJoinClass(joinClass);
            selectSqlModel.setQueryColumns(queryColumns);
            selectSqlModel.setSqlLink(sqlLink);
            selectSqlModel.setOnCondition(onCondition);
            selectSqlModel.setQueryCondition(queryCondition);
            selectSqlModel.setGroupByCondition(groupByCondition);
            selectSqlModel.setOrderByCondition(orderByCondition);

            SelectSqlCovert<T, U> selectSqlCovert = new SelectSqlCovert<>();
            String sql = selectSqlCovert.covert(selectSqlModel);
            System.out.println(sql);
            return (R)ExecutorFactory.getSelectExecutor().select(sql, tableClass);
        }

        @Override
        public List<T> toList() {
            SelectSqlModel<T, U> selectSqlModel = new SelectSqlModel<>();
            selectSqlModel.setTableClass(tableClass);
            selectSqlModel.setJoinClass(joinClass);
            selectSqlModel.setQueryColumns(queryColumns);
            selectSqlModel.setSqlLink(sqlLink);
            selectSqlModel.setOnCondition(onCondition);
            selectSqlModel.setQueryCondition(queryCondition);
            selectSqlModel.setGroupByCondition(groupByCondition);
            selectSqlModel.setOrderByCondition(orderByCondition);

            SelectSqlCovert<T, U> selectSqlCovert = new SelectSqlCovert<>();
            String sql = selectSqlCovert.covert(selectSqlModel);
            System.out.println(sql);
            return ExecutorFactory.getSelectExecutor().selectList(sql, tableClass);
        }
    }


}
