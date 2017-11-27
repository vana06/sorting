package ru.mail.polis.structures;

import java.util.regex.Pattern;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
public class SimpleString implements Numerical<SimpleString> {

    private static final Pattern SIMPLE_STRING = Pattern.compile("^[a-z]+$");
    private static final int DIGIT_COUNT = 'z' - 'a' + 1;
    private static final int MIN_DIGIT_VALUE = 'a' - 1;

    private final String data;
    private final int length;

    public SimpleString(String data) throws IllegalArgumentException {
        if (data == null || data.length() == 0) {
            throw new IllegalArgumentException("Source must be not null and not empty");
        }
        if (!SIMPLE_STRING.matcher(data).matches()) {
            throw new IllegalArgumentException("Bad characters in string [" + data + "]. Must be [a-z]");
        }
        this.data = data;
        this.length = data.length();
    }

    @Override
    public int getDigit(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index " + index);
        } else if (index >= getDigitCount()) {
            return MIN_DIGIT_VALUE;
        } else {
            return data.charAt(index) - MIN_DIGIT_VALUE;
        }
    }

    @Override
    public int getDigitMaxValue() {
        return DIGIT_COUNT;
    }

    @Override
    public int getDigitCount() {
        return length;
    }


    @Override
    public int compareTo(SimpleString anotherSimpleString) {
        return data.compareTo(anotherSimpleString.data);
    }

    @Override
    public String toString() {
        return data;
    }
}
