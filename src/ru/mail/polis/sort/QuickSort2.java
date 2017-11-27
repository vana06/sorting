package ru.mail.polis.sort;

import ru.mail.polis.structures.Numerical;

import java.util.Random;

public class QuickSort2<Item> extends AbstractSortOnComparisons<Item>{

    private Random r = new Random();

    @Override
    public void sort(Item[] array) {
        quickSort(array, 0, array.length-1);
    }

    private void quickSort(Item[] array, int left, int right){
        if (left >= right) return;
        swap(array, right, left + r.nextInt(right - left + 1));
        int i = left, j = right;
        int k = i;
        while (i < j){
            if(lesser(array[i],array[right])){
                swap(array, i++, k++);
            } else if(equal(array[i],array[right])){
                swap(array, i, --j);
            } else i++;
        }
        int m = Math.min(j-k, right-j+1);
        for(int n1 = k, n2 = right-m+1; n1 <= k+m-1; n1++,n2++){
            swap(array, n1, n2);
        }
        quickSort(array, left, k - 1);
        quickSort(array, right - j + k + 1, right);
    }
}
