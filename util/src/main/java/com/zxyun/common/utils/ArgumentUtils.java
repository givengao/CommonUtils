package com.zxyun.common.utils;


import java.util.*;

/**
 * @des: 集合常用判断
 * @Author: given
 * @Date 2019/7/12 10:08
 */
public class ArgumentUtils {

    /**
     * 判断空
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty (Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断非空
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断非空
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> boolean isNotEmpty (Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 空数组 list
     * @param <T>
     * @return
     */
    public static <T> List<T> emptyList () {
        return (List<T>) Collections.emptyList();
    }

    /**
     * 空集合 map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> emptyMap () {
        return (Map<K, V>)Collections.emptyMap();
    }

    /**
     * 空数组 set
     * @param <T>
     * @return
     */
    public static <T> Set<T> emptySet () {
        return (Set<T>)Collections.emptySet();
    }

    /**
     * 数组为空
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String join(Object[] array, String separator) {
        return array == null ? null : join((Object[])array, separator, 0, array.length);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        } else {
            if (separator == null) {
                separator = "";
            }

            int noOfItems = endIndex - startIndex;
            if (noOfItems <= 0) {
                return "";
            } else {
                StringBuilder buf = newStringBuilder(noOfItems);

                for(int i = startIndex; i < endIndex; ++i) {
                    if (i > startIndex) {
                        buf.append(separator);
                    }

                    if (array[i] != null) {
                        buf.append(array[i]);
                    }
                }

                return buf.toString();
            }
        }
    }

    private static StringBuilder newStringBuilder(int noOfItems) {
        return new StringBuilder(noOfItems * 16);
    }

    /**
     * 切割数组
     * @param source
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> partition(List<T> source, int n) {

        List<List<T>> result = new ArrayList<>();

        //(先计算出余数)
        int remainder = source.size() % n;

        //然后是商
        int number = source.size() / n;

        //偏移量
        int offset = 0;

        for (int i = 0; i < n; i++) {
            List<T> value;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
}
