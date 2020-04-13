package com.zxyun.common.db.mysql.factory;

import com.zxyun.common.db.mysql.action.Select;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 11:05
 */
public class SqlActionHelper {

    public static <T> Select<T> select() {
        return new Select<T>();
    }
}
