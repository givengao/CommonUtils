package com.zxyun.common.method.register;

import com.zxyun.common.method.function.MethodFunction;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @des:
 * @Author: given
 * @Date 2019/11/29 16:15
 */
public class MethodRegister<T extends MethodFunction> {

    private ConcurrentHashMap<String,T> consumerMap = new ConcurrentHashMap<>();

    public void registerMethod (String key, T t) {
        if (key != null && t != null) {
            consumerMap.put(key, t);
        }
    }

    public void unRegisterMethod (String key) {
        if (key != null) {
            T t = consumerMap.get(key);
            if (t != null) {
                consumerMap.remove(key);
            }
        }
    }

    public T getMethod (String key) {
        return consumerMap.get(key);
    }
}
