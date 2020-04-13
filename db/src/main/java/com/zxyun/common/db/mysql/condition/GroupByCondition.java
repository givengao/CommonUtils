package com.zxyun.common.db.mysql.condition;

import com.zxyun.common.util.utils.ArgumentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:45
 */
public class GroupByCondition<T, U> extends Condition<T,U> {

    private List<String> expressions = new ArrayList<>();

    public static <T, U> GroupByCondition<T, U> template () {
        return new GroupByCondition<>();
    }

    public GroupByCondition<T, U> group (String column) {
        if (ArgumentUtils.isBlank(column)) {
            return this;
        }
        expressions.add(column);
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
