import java.util.stream.IntStream;
import java.util.OptionalInt;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.IntSummaryStatistics;

class Pair {
    int first;
    int second;
    boolean isDupe;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
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
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int count = 0;
        }

        Stats stats = new Stats();
        double total = (double) stream.map(x -> {stats.max = Math.max(x, stats.max); return x;}).map(y -> {stats.min = Math.min(y, stats.min); return y;}).map(z -> {stats.count++; return z;}).reduce(0, (x, y)->x+y);
        //System.out.println("total: "+total + " min: "+stats.min+" max: "+stats.max+" count: "+stats.count); 
        return stats.count==0?0:((stats.max==stats.min)?0:((total/stats.count) - stats.min)/ (stats.max - stats.min));
    }

}
