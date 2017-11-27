package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyStringValueObject;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SortUtils {

    private static final Random r = ThreadLocalRandom.current();

    public static void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static int[] generateArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        for (int i = a.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(a, i, j);
        }
        return a;
    }
    public static String[] generateStringArray(int n, int length) {
        String[] a = new String[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = "";
        }
        if(length != -1) {
            for (int j = 0; j < length; j++) {
                for (int i = 0; i < a.length; i++) {
                    char temp = (char) ('a' + r.nextInt('z' - 'a' + 1));
                    a[i] = a[i].concat(String.valueOf(temp));
                }
            }
        } else{
            for (int i = 0; i < a.length; i++) {
                char[] str = new char[1 + r.nextInt(99)];
                for (int j = 0; j < str.length; j++) {
                    str[j] = (char) ('a' + r.nextInt('z' - 'a' + 1));
                }
                a[i] = new String(str);
            }
        }
        /*SimpleString[] result = new SimpleString[n];
        for(int i = 0; i < n; i++) {
            result[i] = new SimpleString(a[i]);
        }*/
        return a;
    }
    public static IntKeyStringValueObject[] generateKeysValues(int n){
        IntKeyStringValueObject[] a = new IntKeyStringValueObject[n];
        int max = 100;
        String temp = "abcdefg";
        for(int i = 0; i < n; i++){
            a[i] = new IntKeyStringValueObject(r.nextInt(max), temp);
        }
        return a;
    }
    public static Integer[] generateInverseHeap(int n){
        Integer[] array = Arrays.stream(generateArray(n)).boxed().toArray(Integer[]::new);
        build(array);
        for (int i = 0; i < array.length/2; i++) {
            Integer temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
    public static Integer[] generateSortedArray(int n){
        Integer[] array = new Integer[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }
    public static Integer[] generateInverseSortedArray(int n){
        Integer[] array = new Integer[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - 1 - i;
        }
        return array;
    }
    public static SimpleInteger[] generateSameLengthInt(int n, int length){
        SimpleInteger[] array = new SimpleInteger[n];
        for (int i = 0; i < array.length; i++) {
            int data = 0;
            for (int j = 0; j < length; j++) {
                int digit = r.nextInt(9);
                data += digit<<j;
            }
            array[i] = new SimpleInteger(data);
        }
        return array;
    }

    public static boolean isArraySorted(int[] a) {
        boolean isSorted = true;
        for (int i = 0; i < a.length - 1 && isSorted; i++) {
            isSorted = a[i] <= a[i + 1];
        }
        return isSorted;
    }

    public static <T extends Comparable<? super T>> boolean isArraySorted(T[] array) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = array[i].compareTo(array[i + 1]) <= 0;
        }
        return isSorted;
    }

    public static <T> boolean isArraySorted(T[] array, Comparator<T> comparator) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = comparator.compare(array[i], array[i + 1]) <= 0;
        }
        return isSorted;
    }

    private static void siftDown(Integer[] array, int size, int index) {
        if(2 * index + 1 > size)
            return;

        int left = 2 * index + 1;
        int right = left + 1;

        if(right < size && array[right] <= array[left]) {
            left=right;
        }

        if(left < size && array[index] > array[left]){
            Integer temp = array[index];
            array[index] = array[left];
            array[left] = temp;
            siftDown(array, size, left);
        }
    }
    private static void build(Integer[] array){
        int last = array.length - 1;
        if(last%2 == 0){
            last = (last-2)/2;
        } else{
            last = (last-1)/2;
        }

        for(int i = last; i >= 0; i--){
            siftDown(array, array.length, i);
        }
    }
}
