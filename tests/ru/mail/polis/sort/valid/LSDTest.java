package ru.mail.polis.sort.valid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mail.polis.sort.LSD;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.SimpleString;

import java.util.Arrays;

public class LSDTest {

    private LSD<SimpleString> lsd = new LSD<>();
    private String[] array, actual;
    private SimpleString[] temp;

    @Before
    public void setUp() throws Exception {
        array = new String[]{"abc", "bcd", "cde", "acd", "zxy", "bba"};
        temp = new SimpleString[]{new SimpleString("abc"), new SimpleString("bcd"), new SimpleString("cde"),
                new SimpleString("acd"), new SimpleString("zxy"), new SimpleString("bba")};
    }

    @Test
    public void test() throws Exception {
        lsd.sort(temp);
        actual = new String[temp.length];
        for(int i = 0; i < temp.length; i++){
            actual[i] = temp[i].toString();
        }
        Assert.assertArrayEquals(new String[]{"abc", "acd", "bba", "bcd", "cde", "zxy"}, actual);
    }
}
