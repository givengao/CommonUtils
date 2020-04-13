package com.zxyun.common.db.mysql.enums;

import java.io.Serializable;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 12:14
 */
public interface IEnum<T extends Serializable> {
    T getValue();
}
