package com.zxyun.common.db.mysql.condition;

import com.zxyun.common.db.mysql.enums.SqlKeyword;
import com.zxyun.common.util.fluent.Fluent;
import com.zxyun.common.util.utils.ArgumentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:45
 */
public class QueryCondition<T, U> extends Condition<T,U> {

    private List<String> expressions = new ArrayList<>();

    private T tableQuery;

    public QueryCondition(T tableQuery) {
        this.tableQuery = tableQuery;
    }

    public static <T, U> QueryCondition<T, U> template (T tableQuery) {
        return new QueryCondition<>(tableQuery);
    }

    public QueryCondition<T, U> eq (String column, Function<T, Object> queryFunction) {
        if (ArgumentUtils.isBlank(column)) {
            return this;
        }
        Object obj = queryFunction.apply(tableQuery);
        if (obj == null) {
            return this;
        }
        String expression = column + SqlKeyword.EQ.getSqlSegment() + "'" + obj.toString() + "'";
        if (expressions.isEmpty()) {
            expressions.add(expression);
        } else {
            String last = Fluent.from(expressions).last().get();
            if (!last.equals(SqlKeyword.AND.getSqlSegment()) && !last.equals(SqlKeyword.OR.getSqlSegment())) {
                expressions.add(SqlKeyword.AND.getSqlSegment());
                expressions.add(expression);
            } else {
                expressions.add(expression);
            }
        }
        return this;
    }



    @Override
    public String getConditionSql() {
        if (ArgumentUtils.isEmpty(expressions)) {
            return null;
        }
        return expressions.stream().collect(Collectors.joining(" "));
    }


}
