package com.zxyun.common.db.mysql.factory;

import com.zxyun.common.db.mysql.executor.SelectExecutor;

/**
 * @des: sql执行工厂
 * @Author: given
 * @Date 2020/4/10 15:16
 */
public class ExecutorFactory {

    public static SelectExecutor getSelectExecutor () {
        return new SelectExecutor();
    }
}
