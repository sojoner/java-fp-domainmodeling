package net.sojoner.functional;

public class Converter implements ExtendedBiFunction<Double, Double, Double> {

    @Override
    public Double apply(Double conversionRate, Double value){
        return conversionRate * value;
    }
}
