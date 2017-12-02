package ru.mail.polis.sort.valid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mail.polis.sort.LSD;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.util.Arrays;

public class LSDTestInteger {

    private LSD<SimpleInteger> lsd = new LSD<>();
    private Integer[] array, actual;
    private SimpleInteger[] temp;


    @Before
    public void setUp() throws Exception {
        array = new Integer[]{60,5,6,3,3,9};
        temp = new SimpleInteger[array.length];
        for (int i = 0; i < array.length; i++) {
            temp[i] = new SimpleInteger(array[i]);

        }

    }

    @Test
    public void test() throws Exception {
        lsd.sort(temp);
        actual = new Integer[temp.length];
        for(int i = 0; i < temp.length; i++){
            actual[i] = temp[i].toInt();
        }
        Arrays.sort(array);
        Assert.assertArrayEquals(array, actual);
    }
}
