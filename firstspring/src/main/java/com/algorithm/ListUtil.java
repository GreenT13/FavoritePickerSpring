package com.algorithm;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    /**
     * Create a copy of a list.
     * @param list List to copy.
     * @return The copy.
     */
    public static <T> List<T> copyList(List<T> list) {
        ArrayList<T> copy = new ArrayList<>();
        for (T t : list) {
            copy.add(t);
        }
        return copy;
    }

    /**
     * Pick at most {@code size} number of elements from {@code list}.
     * @param list
     * @param size
     * @return ArrayList of the picked elements.
     */
    public static <T> List<T> pickMaximalSizeList(List<T> list, Integer size) {
        if (list.size() <= size) {
            return copyList(list);
        }

        ArrayList<T> maxSize = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            maxSize.add(list.get(i));
        }
        return maxSize;
    }

}
