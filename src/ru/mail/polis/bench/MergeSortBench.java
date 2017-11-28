package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.MergeSort;
import ru.mail.polis.sort.QuickSort1;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.IntKeyStringValueObject;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class MergeSortBench {

    Object[][] data1, data2, data3, data4, data5, data6, data7;
    Object[] curr;
    int index;
    int testNumber = 15;

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        int n = 10000;

        //1. числа с ключами в узком диапазоне
        data1 = new IntKeyStringValueObject[testNumber][];
        //2. длинные строки одинаковой длины
        data2 = new String[testNumber][];
        //3. куча в обратном порядке
        data3 = new Integer[testNumber][];
        //4. отсортированный массив
        data4 = new Integer[testNumber][];
        //5. отсортированный массив в обратном порядке
        data5 = new Integer[testNumber][];
        //6. рандомно заполненный масссив чисел
        data6 = new Integer[testNumber][];
        //7. рандомно заполненный массив строк
        data7 = new String[testNumber][];
        for (int i = 0; i < testNumber; i++) {
            data1[i] = SortUtils.generateKeysValues(n);
            data2[i] = SortUtils.generateStringArray(n, 10);
            data3[i] = SortUtils.generateInverseHeap(n);
            data4[i] = SortUtils.generateSortedArray(n);
            data5[i] = SortUtils.generateInverseSortedArray(n);
            data6[i] = Arrays.stream(SortUtils.generateArray(n)).boxed().toArray(Integer[]::new);
            data7[i] = SortUtils.generateStringArray(n, -1);
        }
    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        index = (index + 1) % testNumber;
    }

    @Benchmark
    public void measureMergeSortKeysValues() {
        curr = Arrays.copyOf(data1[index], data1[index].length);
        new MergeSort<>().sort(curr);
    }

    @Benchmark
    public void measureMergeSortString() {
        curr = Arrays.copyOf(data2[index], data2[index].length);
        new MergeSort<>().sort(curr);
    }

    @Benchmark
    public void measureMergeSortInverseHeap() {
        curr = Arrays.copyOf(data3[index], data3[index].length);
        new MergeSort<>().sort(curr);
    }

    @Benchmark
    public void measureMergeSortSortedArray() {
        curr = Arrays.copyOf(data4[index], data4[index].length);
        new MergeSort<>().sort(curr);
    }

    @Benchmark
    public void measureMergeSortInverseSortedArray() {
        curr = Arrays.copyOf(data5[index], data5[index].length);
        new MergeSort<>().sort(curr);
    }

    @Benchmark
    public void measureMergeSortRandomInt() {
        curr = Arrays.copyOf(data6[index], data6[index].length);
        new MergeSort<>().sort(curr);
    }

    @Benchmark
    public void measureMergeSortRandomString() {
        curr = Arrays.copyOf(data7[index], data7[index].length);
        new MergeSort<>().sort(curr);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MergeSort.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}