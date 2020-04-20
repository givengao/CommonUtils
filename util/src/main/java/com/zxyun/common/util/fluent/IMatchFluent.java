package com.zxyun.common.util.fluent;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/20 12:00
 */
public interface IMatchFluent<S, D> extends Iterable<S> {

    JoinOn<S, D> leftJoin (Iterable<D> iterable);

    JoinOn<S, D> rightJoin (Iterable<D> iterable);

    JoinOn<S, D> innerJoin (Iterable<D> iterable);

}