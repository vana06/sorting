package ru.mail.polis.sort;

public class HeapSort<Item> extends AbstractSortOnComparisons<Item>{
    @Override
    public void sort(Item[] array) {
        heapSort(array);
    }
    private void heapSort(Item[] array){
        build(array);
        int heapSize = array.length;
        for(int i = 0; i < array.length - 1; i++){
            swap(array, 0, array.length - 1 -i);
            siftDown(array, --heapSize,0);
        }
    }

    private void siftDown(Item[] array, int size, int index) {
        if(2 * index + 1 > size)
            return;

        int left = 2 * index + 1;
        int right = left + 1;

        if(right < size && (lesser(array[right], array[left]) || equal(array[right], array[left]))) {
            left=right;
        }

        if(left < size && greater(array[index],array[left])){
            swap(array, index, left);
            siftDown(array, size, left);
        }
    }
    private void build(Item[] array){
        int last = array.length - 1;
        if(last%2 == 0){
            last = (last-2)/2;
        } else{
            last = (last-1)/2;
        }

        for(int i = last; i >= 0; i--){
            siftDown(array,array.length, i);
        }
    }
}
