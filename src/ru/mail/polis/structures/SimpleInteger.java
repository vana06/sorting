package ru.mail.polis.structures;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
public class SimpleInteger implements Numerical<SimpleInteger> {

    private static final int DIGIT_COUNT = 10;

    private final char[] data;
    private final int length;

    public SimpleInteger(Integer data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Source must be not null");
        }
        this.data = String.valueOf(data).toCharArray();
        this.length = this.data.length;
    }

    @Override
    public int getDigit(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index " + index);
        } else if (index >= getDigitCount()) {
            return 0;
        } else {
            return Character.getNumericValue(data[index]);
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
    public int compareTo(SimpleInteger anotherSimpleInteger) {
        return 0;
    }

    public Integer toInt(){
        return Integer.parseInt(new String(data));
    }
}
