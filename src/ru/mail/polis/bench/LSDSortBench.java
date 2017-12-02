package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.BubbleSort;
import ru.mail.polis.sort.LSD;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.IntKeyStringValueObject;
import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class LSDSortBench {
    SimpleString[][] data2, data7;
    SimpleInteger[][] data1, data3, data4, data5, data6;
    SimpleString[] curr2;
    SimpleInteger[] curr1;
    int index;
    int testNumber = 15;

    private SimpleInteger[] transform(Integer[] array){
        SimpleInteger[] result = new SimpleInteger[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new SimpleInteger(array[i]);
        }
        return result;
    }
    private SimpleString[] transform(String[] array){
        SimpleString[] result = new SimpleString[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new SimpleString(array[i]);
        }
        return result;
    }
    private SimpleInteger[] transform(IntKeyStringValueObject[] array){
        SimpleInteger[] result = new SimpleInteger[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new SimpleInteger(array[i].getKey());
        }
        return result;
    }

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        int n = 10000;

        //1. числа с ключами в узком диапазоне
        data1 = new SimpleInteger[testNumber][];
        //2. длинные строки одинаковой длины
        data2 = new SimpleString[testNumber][];
        //3. куча в обратном порядке
        data3 = new SimpleInteger[testNumber][];
        //4. отсортированный массив
        data4 = new SimpleInteger[testNumber][];
        //5. отсортированный массив в обратном порядке
        data5 = new SimpleInteger[testNumber][];
        //6. рандомно заполненный масссив чисел
        data6 = new SimpleInteger[testNumber][];
        //7. рандомно заполненный массив чисел одинаково длины
        data7 = new SimpleString[testNumber][];
        for(int i = 0; i < testNumber; i++) {
            data1[i] = transform(SortUtils.generateKeysValues(n));
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
    public void measureLsdSortString() {
        curr2 = Arrays.copyOf(data2[index], data2[index].length);
        new LSD<SimpleString>().sort(curr2);
    }
    @Benchmark
    public void measureLsdSortKeysValues() {
        curr1 = Arrays.copyOf(data1[index], data1[index].length);
        new LSD<SimpleInteger>().sort(curr1);
    }
    @Benchmark
    public void measureLsdSortInverseHeap() {
        curr1 = Arrays.copyOf(data3[index], data3[index].length);
        new LSD<SimpleInteger>().sort(curr1);
    }
    @Benchmark
    public void measureLsdSortSortedArray() {
        curr1 = Arrays.copyOf(data4[index], data4[index].length);
        new LSD<SimpleInteger>().sort(curr1);
    }
    @Benchmark
    public void measureLsdSortInverseSortedArray() {
        curr1 = Arrays.copyOf(data5[index], data5[index].length);
        new LSD<SimpleInteger>().sort(curr1);
    }
    @Benchmark
    public void measureLsdSortRandomInt() {
        curr1 = Arrays.copyOf(data6[index], data6[index].length);
        new LSD<SimpleInteger>().sort(curr1);
    }
    /*@Benchmark
    public void measureLsdSortRandomString() {
        curr2 = Arrays.copyOf(data7[index], data7[index].length);
        new LSD<SimpleString>().sort(curr2);
    }*/

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LSDSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
