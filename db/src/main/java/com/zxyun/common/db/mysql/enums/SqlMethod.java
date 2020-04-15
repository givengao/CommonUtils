package com.zxyun.common.db.mysql.enums;


/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:53
 */
public enum  SqlMethod implements ISqlSegment {
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert"),
    DELETE("delete"),
            ;

    private final String keyword;

    private SqlMethod(final String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getSqlSegment() {
        return keyword;
    }
}
