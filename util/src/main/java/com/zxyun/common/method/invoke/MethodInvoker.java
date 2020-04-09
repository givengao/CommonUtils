package com.zxyun.common.method.invoke;

import com.zxyun.common.method.function.MethodFunction;
import com.zxyun.common.method.register.MethodRegister;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @des:
 * @Author: given
 * @Date 2019/11/29 16:26
 */
public abstract class MethodInvoker<T extends MethodFunction> {

    protected MethodRegister<T> methodRegister;

    protected ExecutorService executorService = Executors.newFixedThreadPool(10);

    public MethodInvoker(MethodRegister methodRegister) {
        this.methodRegister = methodRegister;
    }

    protected <T> T getMethod (String key) {
        return (T)methodRegister.getMethod(key);
    }
}
