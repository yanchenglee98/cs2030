import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
public class Main {
  /**
   * The program read a sequence of (id, search string) from standard input.
   * @param args Command line arguments
   */
  public static void main(String[] args) throws ExecutionException, InterruptedException {
      Instant start = Instant.now();
      Scanner sc = new Scanner(System.in);

      CompletableFuture<String> result = CompletableFuture.completedFuture("");
      while (sc.hasNext()) {
          BusStop srcId = new BusStop(sc.next());
          String searchString = sc.next();
          result = result.thenCombine(BusSg.findBusServicesBetween(srcId, searchString).thenCompose(BusRoutes::description), (x,y)->(x=="")?x+y:x+"\n"+y);
      }
      sc.close();

      CompletableFuture.allOf(result);

      result.join();
      System.out.println(result.get());
      Instant stop = Instant.now();
      System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
  }
}
