package ru.mail.polis.sort.valid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mail.polis.sort.LSD;
import ru.mail.polis.structures.SimpleString;

public class LSDTest {

    private LSD<String> lsd = new LSD<>();
    private String[] array;

    @Before
    public void setUp() throws Exception {
        array = new String[]{"abc", "bcd", "cde", "acd", "zxy", "bba"};
    }

    @Test
    public void test() throws Exception {
        lsd.sort(array);
        Assert.assertArrayEquals(new String[]{"abc", "acd", "bba", "bcd", "cde", "zxy"}, array);
    }
}
