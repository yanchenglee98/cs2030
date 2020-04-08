import java.util.Scanner;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A BusSg class encapsulate the data related to the bus services and
 * bus stops in Singapore, and supports queries to the data.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
class BusSg {

  /**
   * Return the set of bus services that serve this bus stop as
   * a set.  Query the web server.
   * @return A set of BusService that serve this bus stop.
   */
  public static CompletableFuture<Set<BusService>> getBusServices(BusStop stop) {
    CompletableFuture<Set<BusService>> result = BusAPI.getBusServicesAt(stop.getStopId())
                                                      .thenApply(x -> {
                                                          Scanner sc = new Scanner(x);
                                                          Set<BusService> busServices = sc
                                                                  .useDelimiter("\n")
                                                                  .tokens()
                                                                  .skip(1) // skip first line
                                                                  .flatMap(line -> Stream.of(line.split(",")))
                                                                  .map(id -> new BusService(id))
                                                                  .collect(Collectors.toSet());
                                                          sc.close();
                                                          return busServices;
                                                      });
    return result;
  }


  /**
   * Given a bus stop and a name, find the bus services that serve between
   * the given stop and any bus stop with matching mame.
   * @param  stop The bus stop.  Assume to be not null.
   * @param  searchString The (partial) name of other bus stops, assume not null.
   * @return The (optional) bus routes between the stops.
   */
  public static CompletableFuture<BusRoutes> findBusServicesBetween(BusStop stop, String searchString) {
      try {
          CompletableFuture<Map<BusService, CompletableFuture<Set<BusStop>>>> services = getBusServices(stop)
                  .thenApply(x -> x.stream()
                          .collect(Collectors.toMap(service -> service, service -> service.findStopsWith(searchString))));
          return services.thenApply(x -> new BusRoutes(stop, searchString, x));
      } catch (CompletionException e) {
          System.err.println("Unable to complete query: " + e);
            return CompletableFuture.completedFuture(new BusRoutes(stop, searchString, Map.of()));
      }
  }
}
