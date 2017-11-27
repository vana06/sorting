package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;

public class CountingSort<Item extends IntKeyObject<String>> extends AbstractSortOnComparisons<Item>{

    int type = 1; //1 - сортировка по ключам; 2 - сортировка по значениям

    public CountingSort(int type){
        this.type = type;
    }

    @Override
    public void sort(Item[] array) {
        countingSort(array);
    }

    private void countingSort(Item[] array){
        Item max = array[0];
        for(int i = 1; i < array.length; i++){
            if(array[i].compareTo(max) > 0)
                max = array[i];
        }

        int[] count = new int[max.getKey()+1];

        for(int i = 0; i < array.length ; i++){
            count[array[i].getKey()] += 1;
        }
        for (int i = 1; i <= max.getKey(); i++) {
            count[i] += count[i - 1];
        }

        Item[] res = (Item[]) new IntKeyObject[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            res[--count[array[i].getKey()]] = array[i];
        }
            System.arraycopy(res, 0, array, 0, array.length);
    }
}
