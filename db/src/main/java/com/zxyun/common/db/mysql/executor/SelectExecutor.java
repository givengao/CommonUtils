package com.zxyun.common.db.mysql.executor;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;


/**
 * @des:
 * @Author: given
 * @Date 2020/4/10 15:15
 */
public class SelectExecutor {

    public <T> T select () {
        SqlSession session = new DefaultSqlSessionFactory(new Configuration()).openSession();
        session.select("", "", new ResultHandler<T>() {
            @Override
            public void handleResult(ResultContext resultContext) {
                T t = (T) resultContext.getResultObject();
            }
        });
        return null;
    }
}
