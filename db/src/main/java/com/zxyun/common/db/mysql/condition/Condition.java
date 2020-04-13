package com.zxyun.common.db.mysql.condition;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 14:05
 */
public abstract class Condition<T, U> {

    private Class<T> tClass;

    private Class<U> uClass;

    public abstract String getConditionSql ();

    public Class<T> gettClass() {
        return tClass;
    }

    public void settClass(Class<T> tClass) {
        this.tClass = tClass;
    }

    public Class<U> getuClass() {
        return uClass;
    }

    public void setuClass(Class<U> uClass) {
        this.uClass = uClass;
    }
}
