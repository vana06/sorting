package ru.mail.polis.sort;

import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleString;

public class LSD<Item extends Numerical<Item>>{

    public void sort(Item[] array){
        //Numerical temp = new SimpleString(array[0]);
        int digitNum = array[0].getDigitCount();
        for(int i = 0; i < digitNum; i++){
            int[] count = new int[array[0].getDigitMaxValue()];
            for(int j = 0; j < array.length; j++){
                count[array[j].getDigit(digitNum-(i+1)) - 1]++;
            }
            for(int j = 1; j < count.length; j++){
                count[j] += count[j-1];
            }
            Item[] tempArray = (Item[]) new Numerical[array.length];
            for(int j = array.length-1; j >= 0; j--){
                int item = array[j].getDigit(digitNum-(i+1)) - 1;
                tempArray[--count[item]] = array[j];
            }
            System.arraycopy(tempArray, 0, array, 0, tempArray.length);
        }
    }
}
