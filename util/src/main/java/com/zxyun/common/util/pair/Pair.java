package com.zxyun.common.util.pair;

import java.util.function.Function;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/20 14:35
 */
public class Pair<S, D> {
    private Function<S, String> sFunction;

    private Function<D, String> dFunction;

    public Pair(Function<S, String> sFunction, Function<D, String> dFunction) {
        this.sFunction = sFunction;
        this.dFunction = dFunction;
    }

    public Function<S,String> getsFunction() {
        return sFunction;
    }

    public void setsFunction(Function<S,String> sFunction) {
        this.sFunction = sFunction;
    }

    public Function<D,String> getdFunction() {
        return dFunction;
    }

    public void setdFunction(Function<D,String> dFunction) {
        this.dFunction = dFunction;
    }
}
