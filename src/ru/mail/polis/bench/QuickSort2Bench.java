package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.QuickSort2;
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
public class QuickSort2Bench {
    Object[][] data;
    Object[] curr;
    int index;
    int testNumber = 10;

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        int n = 10000;

        //1. числа с ключами в узком диапазоне
        data = new IntKeyStringValueObject[testNumber][];
        data[0] = SortUtils.generateKeysValues(n);
        //2. длинные строки одинаковой длины
        //data = new String[testNumber][];
        //data[0] = SortUtils.generateStringArray(n, 10);
        //3. куча в обратном порядке
        //data = new Integer[testNumber][];
        //data[0] = SortUtils.generateInverseHeap(n);
        //4. отсортированный массив
        //data = new Integer[testNumber][];
        //data[0] = SortUtils.generateSortedArray(n);
        //5. отсортированный массив в обратном порядке
        //data = new Integer[testNumber][];
        //data[0] = SortUtils.generateInverseSortedArray(n);
        //6. рандомно заполненный масссив чисел
        /*data = new Integer[testNumber][];
        for(int i = 0; i < testNumber; i++) {
            data[i] = Arrays.stream(SortUtils.generateArray(n)).boxed().toArray(Integer[]::new);
        }*/
        //7. рандомно заполненный массив строк
        /*data = new String[testNumber][];
        for(int i = 0; i < testNumber; i++) {
            data[i] = SortUtils.generateStringArray(n, 100);
        }*/

    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        curr = Arrays.copyOf(data[index], data[index].length);
        index = (index + 1) % testNumber;
    }

    @Benchmark
    public void measureQuickSort1Sort() {
        new QuickSort2<>().sort((IntKeyStringValueObject[])curr, 0, data.length - 1);
        //new QuickSort2<>().sort((String[])curr, 0, data.length - 1);
        //new QuickSort2<>().sort((Integer[])curr, 0, curr.length - 1);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(QuickSort2.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
