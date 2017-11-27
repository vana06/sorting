package ru.mail.polis.sort;

import ru.mail.polis.structures.SimpleString;

public class LSD<Item extends String> {

    public Item[] sort(Item[] array){
        SimpleString temp = new SimpleString(array[0]);
        int digitNum = temp.getDigitCount();
        for(int i = 0; i < digitNum; i++){
            int[] count = new int[temp.getDigitMaxValue()];
            for(int j = 0; j < array.length; j++){
                temp = new SimpleString(array[j]);
                count[temp.getDigit(digitNum-(i+1)) - 1]++;
            }
            for(int j = 1; j < count.length; j++){
                count[j] += count[j-1];
            }
            Item[] tempArray = (Item[]) new String[array.length];
            for(int j = array.length-1; j >= 0; j--){
                temp = new SimpleString(array[j]);
                int item = temp.getDigit(digitNum-(i+1)) - 1;
                tempArray[--count[item]] = array[j];
            }
            System.arraycopy(tempArray, 0, array, 0, tempArray.length);
        }

        return array;
    }
}
