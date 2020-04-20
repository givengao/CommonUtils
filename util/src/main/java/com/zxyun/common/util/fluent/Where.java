package com.zxyun.common.util.fluent;

import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/20 15:37
 */
public
interface Where<S, D> {
    Where<S,D> and(Predicate<S> sPredicate, Predicate<D> dPredicate);

    <E> IFluent<E> map(BiFunction<S,D,E> function);
}
