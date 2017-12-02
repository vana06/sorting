package ru.mail.polis.sort.valid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mail.polis.sort.LSD;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.util.Arrays;

public class LSDTestString {

    private LSD<SimpleString> lsd = new LSD<>();
    private String[] array, actual;
    private SimpleString[] temp;


    @Before
    public void setUp() throws Exception {
        //array = new String[]{"abc", "bcd", "cde", "acd", "zxy", "bba"};
        array = SortUtils.generateStringArray(5, -1);
        temp = new SimpleString[array.length];
        for (int i = 0; i < array.length; i++) {
            temp[i] = new SimpleString(array[i]);

        }

    }

    @Test
    public void test() throws Exception {
        lsd.sort(temp);
        actual = new String[temp.length];
        for(int i = 0; i < temp.length; i++){
            actual[i] = temp[i].toString();
        }
        Arrays.sort(array);
        Assert.assertArrayEquals(array, actual);
    }
}
