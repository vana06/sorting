package ru.mail.polis.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.mail.polis.sort.BubbleSort;
import ru.mail.polis.sort.LSD;
import ru.mail.polis.sort.SortUtils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class LSDSortBench {
    String[][] data;
    String [] curr;
    int index;

    @Setup(value = Level.Trial)
    public void setUpTrial() {
        data = new String[10][100];
        for (int i = 0; i < 10; i++) {
            //define arrays here
            data[i] = SortUtils.generateStringArray(100, 10);
        }
    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        curr = Arrays.copyOf(data[index], data[index].length);
        index = (index + 1) % 10;
    }

    @Benchmark
    public void measureLsdSort() {
        new LSD<>().sort(curr);
    }

    public static void main(String[] args) throws RunnerException {
        SortUtils.generateStringArray(100, 10);
        Options opt = new OptionsBuilder()
                .include(LSDSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
