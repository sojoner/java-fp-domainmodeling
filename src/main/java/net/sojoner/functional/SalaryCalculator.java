package net.sojoner.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SalaryCalculator {

    private final List<Function<Double, Double>> fs = new ArrayList<>();

    public SalaryCalculator with(Function<Double,Double> f){
        fs.add(f);
        return this;
    }

    public double calculate(double basic){
        Function<Double, Double> reduce = fs.stream()
                .reduce(Function.<Double>identity(),
                        Function::andThen);
        return reduce
                .apply(basic);
    }
}


