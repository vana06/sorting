package ru.mail.polis.sort.valid;

import org.junit.Assert;
import org.junit.Test;
import ru.mail.polis.sort.QuickSort1;
import ru.mail.polis.sort.QuickSort2;
import ru.mail.polis.sort.SimpleSortOnComparisons;
import ru.mail.polis.sort.SortUtils;

import java.io.IOException;
import java.util.Arrays;

public class QuickSort2Test {

    @Test
    public void test01() throws IOException {
        //String[] array = new String[]{"abc", "bcd", "cde", "acd", "zxy", "bba"};
        Integer[] array = SortUtils.generateInverseHeap(10000);
        Integer[] sorted = new Integer[10000];
        System.arraycopy(array,0,sorted,0,10000);
        Arrays.sort(sorted);
        new QuickSort2<>().sort(array);
        Assert.assertArrayEquals(sorted, array);
    }
}
