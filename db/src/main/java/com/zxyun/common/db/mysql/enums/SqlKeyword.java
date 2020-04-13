package com.zxyun.common.db.mysql.enums;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 12:13
 */
public enum SqlKeyword implements ISqlSegment {
    AND("AND"),
    OR("OR"),
    IN("IN"),
    NOT("NOT"),
    LIKE("LIKE"),
    EQ("="),
    NE("<>"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    GROUP_BY("GROUP BY"),
    HAVING("HAVING"),
    ORDER_BY("ORDER BY"),
    EXISTS("EXISTS"),
    BETWEEN("BETWEEN"),
    ASC("ASC"),
    DESC("DESC"),
    FROM("FROM"),
    WHERE("WHERE"),
    ON("on");

    private final String keyword;

    private SqlKeyword(final String keyword) {
        this.keyword = keyword;
    }

    public String getSqlSegment() {
        return this.keyword;
    }
}
