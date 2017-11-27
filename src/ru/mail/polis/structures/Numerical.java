package ru.mail.polis.structures;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
public interface Numerical<T> extends Comparable<T> {

    /**
     * @param index - порядковый номер разряда
     * @return Значение index разряда
     * @throws IndexOutOfBoundsException если такого разряда нет
     */
    int getDigit(int index) throws IndexOutOfBoundsException;

    /**
     * @return Возращается максимальное значение разряда (минимальное значение == 0)
     */
    int getDigitMaxValue();

    /**
     * @return Количество разрядов
     */
    int getDigitCount();
}
