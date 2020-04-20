package com.zxyun.common.util.fluent;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/20 15:37
 */
public
interface JoinOn<S, D> {
    JoinOn<S,D> on(Function<S,String> sKeyMapper, Function<D,String> dKeyMapper);

    Where<S,D> where(Predicate<S> sPredicate, Predicate<D> dPredicate);
}
