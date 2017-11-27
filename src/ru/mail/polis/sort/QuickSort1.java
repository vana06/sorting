package ru.mail.polis.sort;

import java.util.Random;

public class QuickSort1<Item extends Comparable> extends AbstractSortOnComparisons<Item> {

    private Random r = new Random();

    public Item[] sort(Item[] array, int left, int right){
        if(right - left <= 15){
            insertionSort(array, left, right);
            return null;
        }
        if (left >= right) return null;
        int idx = partition(array, left, right);
        sort(array, left, idx);
        sort(array, idx + 1, right);
        return array;
    }

    private int partition(Item[] array, int left, int right) {
        Item p = array[left + 1 + r.nextInt(right - (left + 1))];
        int i = left, j = right;
        while (i <= j) {
            while (lesser(array[i],p)) i++;
            while (greater(array[j],p)) j--;
            if (i <= j) {
                swap(array, i++, j--);
            }
        }
        return j;
    }

    private void insertionSort(Item[] array, int left, int right){
        for (int i = left; i <= right; i++) {
            for (int j = i; j > 0; j--) {
                if(lesser(array[j],array[j-1])){
                    swap(array, j, j-1);
                }
            }
        }
    }
}
