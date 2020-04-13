package com.zxyun.common.db.mysql.condition;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:45
 */
public class QueryCondition<T, U> extends Condition<T,U> {

    private StringBuilder querySql = new StringBuilder();

    @Override
    public String getConditionSql() {
        return null;
    }


}
