package ru.mail.polis.sort;

import java.util.Comparator;

/**
 * Created by Nechaev Mikhail
 * Since 14/11/2017.
 */
public abstract class AbstractSortOnComparisons<T> {

    private Comparator<? super T> comparator;

    public AbstractSortOnComparisons() {
        /* empty */
    }

    public AbstractSortOnComparisons(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public void setComparator(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public abstract void sort(T[] array);

    protected void swap(T[] array, int i, int j) {
        T t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    @SuppressWarnings("unchecked")
    protected int compare(T firstKey, T secondKey) {
        if (comparator == null) {
            return ((Comparable<? super T>) firstKey).compareTo(secondKey);
        }
        return comparator.compare(firstKey, secondKey);
    }

    protected boolean greater(T firstKey, T secondKey) {
        return compare(firstKey, secondKey) > 0;
    }

    protected boolean lesser(T firstKey, T secondKey) {
        return compare(firstKey, secondKey) < 0;
    }

    protected boolean equal (T firstKey, T secondKey) {
        return compare(firstKey, secondKey) == 0;
    }
}
