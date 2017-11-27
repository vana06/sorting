package ru.mail.polis.sort;

import ru.mail.polis.structures.Numerical;

import java.util.Random;

public class QuickSort1<Item> extends AbstractSortOnComparisons<Item> {

    private Random r = new Random();

    @Override
    public void sort(Item[] array) {
        quickSort(array, 0, array.length-1);
    }

    private void quickSort(Item[] array, int left, int right){
        if(right - left < 15){
            insertionSort(array, left, right);
            return;
        }
        if (left >= right) return;
        int idx = partition(array, left, right);
        quickSort(array, left, idx);
        quickSort(array, idx + 1, right);
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
            for (int j = i; j > 0 && lesser(array[j],array[j-1]); j--) {
                swap(array, j, j-1);
            }
        }
    }


}
