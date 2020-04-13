package com.zxyun.common.db.mysql.condition;

import com.zxyun.common.db.mysql.enums.SqlKeyword;
import com.zxyun.common.util.utils.ArgumentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:45
 */
public class OrderByCondition<T, U> extends Condition<T,U> {

    private List<String> expressions = new ArrayList<>();

    public static <T, U> OrderByCondition<T, U> template () {
        return new OrderByCondition<>();
    }

    public OrderByCondition<T, U> Desc (String column) {
        if (ArgumentUtils.isBlank(column)) {
            return this;
        }
        String sql = column + " " + SqlKeyword.DESC.getSqlSegment();
        expressions.add(sql);
        return this;
    }

    public OrderByCondition<T, U> Asc (String column) {
        if (ArgumentUtils.isBlank(column)) {
            return this;
        }
        String sql = column + " " + SqlKeyword.ASC.getSqlSegment();
        expressions.add(sql);
        return this;
    }


    @Override
    public String getConditionSql() {
        if (ArgumentUtils.isEmpty(expressions)) {
            return null;
        }
        return expressions.stream().collect(Collectors.joining(","));
    }
}
