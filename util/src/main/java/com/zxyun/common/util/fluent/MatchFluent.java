package com.zxyun.common.util.fluent;

import com.zxyun.common.util.pair.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @des: 多集合关联的流处理
 * @Author: given
 * @Date 2020/4/20 12:26
 */
public class MatchFluent<S, D> implements IMatchFluent<S, D> {

    private String linkType;

    private Iterable<S> sIterable;

    private Iterable<D> dIterable;

    public MatchFluent(Iterable<S> sIterable) {
        this.sIterable = sIterable;
    }

    public static <S, D> IMatchFluent<S, D> from(Iterable<S> iterable, Class<D> clazz) {
        List<S> copy = IFluent.copyToList(iterable);

        return new MatchFluent(copy);
    }

    @Override
    public JoinOn<S,D> leftJoin(Iterable<D> iterable) {
        dIterable = iterable;
        return new JoinOnImpl<>("left", sIterable, dIterable);
    }

    @Override
    public JoinOn<S,D> rightJoin(Iterable<D> iterable) {
        dIterable = iterable;
        return new JoinOnImpl<>("right", sIterable, dIterable);
    }

    @Override
    public JoinOn<S,D> innerJoin(Iterable<D> iterable) {
        dIterable = iterable;
        return new JoinOnImpl<>("inner", sIterable, dIterable);
    }

    @Override
    public Iterator<S> iterator() {
        return null;
    }

    private class JoinOnImpl<S, D> implements JoinOn<S, D> {

        private String linkType;

        private Iterable<S> sIterable;

        private Iterable<D> dIterable;

        private List<Pair<S, D>> pairs = new ArrayList<>();

        private List<Predicate<S>> sPredicates = new ArrayList<>();

        private List<Predicate<D>> dPredicates = new ArrayList<>();

        public JoinOnImpl(String linkType, Iterable<S> sIterable, Iterable<D> dIterable) {
            this.linkType = linkType;
            this.sIterable = sIterable;
            this.dIterable = dIterable;
        }

        @Override
        public JoinOn<S,D> on(Function<S,String> sKeyMapper, Function<D,String> dKeyMapper) {
            Pair<S,D> pair = new Pair(sKeyMapper, dKeyMapper);
            pairs.add(pair);
            return JoinOnImpl.this;
        }

        @Override
        public Where<S,D> where(Predicate<S> sPredicate, Predicate<D> dPredicate) {
            if (sPredicate != null) {
                sPredicates.add(sPredicate);
            }
            if (dPredicate != null) {
                dPredicates.add(dPredicate);
            }
            return new WhereImpl<>(linkType, sIterable, dIterable, pairs, sPredicates, dPredicates);
        }
    }

    private class WhereImpl<S,D> implements Where<S,D> {

        private String linkType;

        private Iterable<S> sIterable;

        private Iterable<D> dIterable;

        private List<Pair<S, D>> pairs;

        private List<Predicate<S>> sPredicates;

        private List<Predicate<D>> dPredicates;

        public WhereImpl(String linkType, Iterable<S> sIterable, Iterable<D> dIterable, List<Pair<S, D>> pairs,
                         List<Predicate<S>> sPredicates, List<Predicate<D>> dPredicates) {
            this.linkType = linkType;
            this.sIterable = sIterable;
            this.dIterable = dIterable;
            this.pairs = pairs;
            this.sPredicates = sPredicates;
            this.dPredicates = dPredicates;
        }

        @Override
        public Where<S,D> and(Predicate<S> sPredicate, Predicate<D> dPredicate) {
            if (sPredicate != null) {
                sPredicates.add(sPredicate);
            }
            if (dPredicate != null) {
                dPredicates.add(dPredicate);
            }
            return this;
        }

        @Override
        public <E> IFluent<E> map(BiFunction<S,D,E> function) {
            filter(sIterable, sPredicates);
            filter(dIterable, dPredicates);
            List<E> eIterable = new ArrayList<>();
            if ("left".equals(linkType)) {
                Map<String, D> dMap = Fluent.from(dIterable).toMap(e -> pairs.stream().map(v -> v.getdFunction().apply(e)).collect(Collectors.joining("_")));
                Iterator<S> iterator = sIterable.iterator();

                while (iterator.hasNext()) {
                    S nextElement = iterator.next();
                    String sKey = pairs.stream().map(v -> v.getsFunction().apply(nextElement)).collect(Collectors.joining("_"));
                    D dElement = dMap.get(sKey);
                    E e = function.apply(nextElement, dElement);
                    eIterable.add(e);
                }
            }
            return Fluent.from(eIterable);
        }

        private <R> void filter (Iterable<R> iterable, List<Predicate<R>> predicates) {
            Iterator<R> iterator = iterable.iterator();

            while (iterator.hasNext()) {
                R nextElement = iterator.next();
                for (Predicate<R> rPredicate : predicates) {
                    if (!rPredicate.test(nextElement)) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }

    }
}
