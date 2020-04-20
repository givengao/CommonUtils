import com.zxyun.common.util.fluent.MatchFluent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/20 15:36
 */
public class FluentTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FluentTest.class);

    public static void main (String[] args) {
        List<Integer> alist = new ArrayList<>();
        alist.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10));

        List<Integer> blist = new ArrayList<>();
        blist.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        List<Integer> firstFiveNegatives = MatchFluent.from(alist, Integer.class)
                .leftJoin(blist)
                .on(e -> e.toString(), e -> e.toString())
                .where(e -> e != 2, e -> e != 2)
                .and(e -> e != 3, e -> e != 3)
                .map((k, v) -> k + v)
                .sorted(Integer::compareTo)
                .toList();

        prettyPrint("The first three negative values are: ", firstFiveNegatives);
    }

    private static <E> void prettyPrint(String prefix, Iterable<E> iterable) {
        prettyPrint(", ", prefix, iterable);
    }

    private static <E> void prettyPrint(String delimiter, String prefix,
                                        Iterable<E> iterable) {
        StringJoiner joiner = new StringJoiner(delimiter, prefix, ".");
        Iterator<E> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            joiner.add(iterator.next().toString());
        }

        System.out.println(joiner.toString());
    }
}
