import java.util.stream.IntStream;
import java.util.OptionalInt;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.IntSummaryStatistics;

class Pair {
    final int first;
    final int second;
    final boolean isDupe;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
        this.isDupe = false;
    }

    Pair(int first, int second, boolean isDupe) {
        this.first = first;
        this.second = second;
        this.isDupe = isDupe;
    }
}


public class Main {
    public static boolean isPrime(int n) {
        return IntStream.range(2, n).noneMatch(x -> n % x == 0);
    }

    public static int[] twinPrimes(int n) {
        IntStream pipeline = IntStream.rangeClosed(2, n);
        return pipeline.filter( x -> (isPrime(x) && isPrime(x + 2)) || (isPrime(x) && isPrime(x-2))).filter(x -> x != 2).toArray();
    }

    public static int gcd(int m, int n) {
        int larger = Math.max(m, n);
        int smaller = Math.min(m, n);

        Pair pair = new Pair(larger, smaller);

        return Stream.iterate(pair, x -> x.second != 0, y -> new Pair(y.second, y.first % y.second)).reduce(pair, (x, y) -> y).second;
    }

    public static long countRepeats(int... array) {
        IntStream pipeline = Arrays.stream(array);
        return pipeline.mapToObj(x -> new Pair(x, 0))
            .reduce((p1, p2) -> (p1.first == p2.first)
                    ? (p1.isDupe
                        ? new Pair(p2.first, p1.second, true)
                        : new Pair(p2.first, p1.second + 1, true))
                    :new Pair(p2.first, p1.second))
            .get()
            .second;
    }
    /*
       public static double normalizedMean(Stream stream) {
       IntSummaryStatistics stats = stream.mapToInt(x -> (int) x).summaryStatistics();
       return stats.getCount()==0?0:(stats.getMax()-stats.getMin()==0)?0:(stats.getAverage() - stats.getMin())/(stats.getMax() - stats.getMin());
       }
    */

    public static double normalizedMean(Stream<Integer> stream) {
       class Stats {
            final int max;
            final int min;
            final int count;
            final int total;
            final int value;

            Stats(int value) {
                max = value;
                min = value;
                count = 1;
                total = value;
                this.value = value;
            }

            Stats(int max, int min, int count, int total) {
                this.max = max;
                this.min = min;
                this.count = count;
                this.total = total;
                value = 0;
            }

            public Stats update(Stats s1) {
                int newMax = Math.max(this.max, s1.max);
                int newMin = Math.min(this.min, s1.min);
                int count = this.count + 1;
                int total = this.total + s1.total;

                return new Stats(newMax, newMin, count, total);
            }

            public String toString() {
                return String.format("max:%d min:%d count:%d total:%d value:%d", max, min, count, total, value);
            }
        }


        Stats stats = stream.map(x -> new Stats(x)).reduce((x, y)-> x.update(y)).orElse(new Stats(0));

        return stats.count==0?0:((stats.max==stats.min)?0:(((double) stats.total/stats.count) - stats.min)/ (double) (stats.max - stats.min));
    }

}
