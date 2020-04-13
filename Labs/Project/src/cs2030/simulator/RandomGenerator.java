package cs2030.simulator;

import java.util.Random;

class RandomGenerator {
    private Random rngArrival;
    private Random rngService;
    private Random rngRest;
    private Random rngRestPeriod;
    private Random rngWait;
    private Random rngCustomerType;
    private final double customerArrivalRate;
    private final double customerServiceRate;
    private final double serverRestingRate;

    RandomGenerator(int var1, double var2, double var4) {
        this.rngArrival = new Random((long)var1);
        this.rngService = new Random((long)(var1 + 1));
        this.customerArrivalRate = var2;
        this.customerServiceRate = var4;
        this.serverRestingRate = 0.0D;
    }

    RandomGenerator(int var1, double var2, double var4, double var6) {
        this.rngArrival = new Random((long)var1);
        this.rngService = new Random((long)(var1 + 1));
        this.rngRest = new Random((long)(var1 + 2));
        this.rngRestPeriod = new Random((long)(var1 + 3));
        this.rngCustomerType = new Random((long)(var1 + 4));
        this.customerArrivalRate = var2;
        this.customerServiceRate = var4;
        this.serverRestingRate = var6;
    }

    double genInterArrivalTime() {
        return -Math.log(this.rngArrival.nextDouble()) / this.customerArrivalRate;
    }

    double genServiceTime() {
        return -Math.log(this.rngService.nextDouble()) / this.customerServiceRate;
    }

    double genRandomRest() {
        return this.rngRest.nextDouble();
    }

    double genRestPeriod() {
        return -Math.log(this.rngRestPeriod.nextDouble()) / this.serverRestingRate;
    }

    double genCustomerType() {
        return this.rngCustomerType.nextDouble();
    }
}