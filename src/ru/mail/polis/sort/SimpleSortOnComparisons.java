package ru.mail.polis.sort;

/**
 * Created by Nechaev Mikhail
 * Since 14/11/2017.
 */
public class SimpleSortOnComparisons<T> extends AbstractSortOnComparisons<T> {

    public SimpleSortOnComparisons() {
        super();
    }

    @Override
    public void sort(T[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && lesser(array[j], array[j - 1]); j--) {
                swap(array, j, j - 1);
            }
        }
    }
}
