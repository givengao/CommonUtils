package com.zxyun.common.db.mysql.condition;

import com.zxyun.common.util.utils.ArgumentUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:44
 */
public class OnCondition<T, U> extends Condition<T,U> {
    Map<String, String> joinKeyMap = new HashMap<>();

    public static <T, U> OnCondition<T, U> template () {
        return new OnCondition<>();
    }

    public OnCondition append (String key1, String key2) {
        if (ArgumentUtils.isBlank(key1) || ArgumentUtils.isBlank(key2)) {
            return this;
        }
        joinKeyMap.put(key1, key2);
        return this;
    }

    @Override
    public String getConditionSql() {
        List<String> expressions = new ArrayList<>();

        if (ArgumentUtils.isNotEmpty(joinKeyMap)) {
            joinKeyMap.forEach((k,v) -> {
                expressions.add(k + "=" + v);
            });
        }
        return expressions.stream().collect(Collectors.joining(" and "));
    }
}
