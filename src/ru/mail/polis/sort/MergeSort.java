package ru.mail.polis.sort;

public class MergeSort<Item> extends AbstractSortOnComparisons<Item> {

    @Override
    public void sort(Item[] array) {
        mergeSort(array);
    }

    private void mergeSort(Item[] a){
        if(a.length > 2){
            int bLength = a.length / 2;
            int cLength = a.length - bLength;
            Item[] b = (Item[])new Object[bLength];
            Item[] c = (Item[])new Object[cLength];

            System.arraycopy(a, 0, b, 0, bLength);
            System.arraycopy(a, bLength, c, 0, a.length - bLength);

            mergeSort(b);
            mergeSort(c);

            int j = 0;
            int i = 0;
            for(i = 0; i < b.length && j < c.length;){
                if(lesser(b[i],c[j])){
                    a[i+j] = b[i];
                    i++;
                } else {
                    a[i+j] = c[j];
                    j++;
                }
            }
            if(i!=b.length){
                for(; i < b.length; i++){
                    a[i + j] = b[i];
                }
            }
            if(j!=c.length){
                for(; j < c.length; j++){
                    a[i + j] = c[j];
                }
            }
        }

        if(a.length == 2){
            if(greater(a[0], a[1])){
                Item temp = a[0];
                a[0] = a[1];
                a[1] = temp;
            }
        }
    }
}
