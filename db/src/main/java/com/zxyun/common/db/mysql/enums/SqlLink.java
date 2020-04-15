package com.zxyun.common.db.mysql.enums;


/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 13:22
 */
public enum SqlLink implements ISqlSegment {
    LEFT("left join"),
    RIGHT("right join"),
    INNER("inner join");

    private final String keyword;

    private SqlLink(final String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getSqlSegment() {
        return keyword;
    }
}
