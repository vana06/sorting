package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.sort.QuickSort1;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.IntKeyStringValueObject;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class CountingSortBench {
    IntKeyStringValueObject[][] data1, data2, data3, data4, data5, data6, data7 ;
    IntKeyStringValueObject[] curr;
    int index;
    int testNumber = 15;

    private IntKeyStringValueObject[] transform(Integer[] array){
        IntKeyStringValueObject[] result = new IntKeyStringValueObject[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new IntKeyStringValueObject(array[i], "abc");
        }
        return result;
    }
    private IntKeyStringValueObject[] transform(String[] array){
        IntKeyStringValueObject[] result = new IntKeyStringValueObject[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new IntKeyStringValueObject(123, array[i]);
        }
        return result;
    }

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        int n = 10000;

        //1. числа с ключами в узком диапазоне
        data1 = new IntKeyStringValueObject[testNumber][];
        //2. длинные строки одинаковой длины
        data2 = new IntKeyStringValueObject[testNumber][];
        //3. куча в обратном порядке
        data3 = new IntKeyStringValueObject[testNumber][];
        //4. отсортированный массив
        data4 = new IntKeyStringValueObject[testNumber][];
        //5. отсортированный массив в обратном порядке
        data5 = new IntKeyStringValueObject[testNumber][];
        //6. рандомно заполненный масссив чисел
        data6 = new IntKeyStringValueObject[testNumber][];
        //7. рандомно заполненный массив строк
        data7 = new IntKeyStringValueObject[testNumber][];
        for(int i = 0; i < testNumber; i++) {
            data1[i] = SortUtils.generateKeysValues(n);
            data2[i] = transform(SortUtils.generateStringArray(n, 10));
            data3[i] = transform(SortUtils.generateInverseHeap(n));
            data4[i] = transform(SortUtils.generateSortedArray(n));
            data5[i] = transform(SortUtils.generateInverseSortedArray(n));
            data6[i] = transform(Arrays.stream(SortUtils.generateArray(n)).boxed().toArray(Integer[]::new));
            data7[i] = transform(SortUtils.generateStringArray(n, -1));
        }
    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        index = (index + 1) % testNumber;
    }

    @Benchmark
    public void measureCountingSortKeysValues() {
        curr = Arrays.copyOf(data1[index], data1[index].length);
        new CountingSort<IntKeyStringValueObject>(1).sort(curr);
    }
    @Benchmark
    public void measureCountingSortString() {
        curr = Arrays.copyOf(data2[index], data2[index].length);
        new CountingSort<>(2).sort(curr);
    }
    @Benchmark
    public void measureCountingSortInverseHeap() {
        curr = Arrays.copyOf(data3[index], data3[index].length);
        new CountingSort<>(1).sort(curr);
    }
    @Benchmark
    public void measureCountingSortSortedArray() {
        curr = Arrays.copyOf(data4[index], data4[index].length);
        new CountingSort<>(1).sort(curr);
    }
    @Benchmark
    public void measureCountingSortInverseSortedArray() {
        curr = Arrays.copyOf(data5[index], data5[index].length);
        new CountingSort<>(1).sort(curr);
    }
    @Benchmark
    public void measureCountingSortRandomInt() {
        curr = Arrays.copyOf(data6[index], data6[index].length);
        new CountingSort<>(1).sort(curr);
    }
    @Benchmark
    public void measureCountingSortRandomString() {
        curr = Arrays.copyOf(data7[index], data7[index].length);
        new CountingSort<>(2).sort(curr);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CountingSort.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
