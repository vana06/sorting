package ru.mail.polis.structures;

import java.util.Comparator;

/**
 * Created by Nechaev Mikhail
 * Since 13/11/2017.
 */
public class IntKeyStringValueObject implements IntKeyObject<String> {

    private final int key;
    private final String value;

    public IntKeyStringValueObject(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "IntKeyStringValueObject{key=" + key + ", value='" + value + "\'}";
    }

}
