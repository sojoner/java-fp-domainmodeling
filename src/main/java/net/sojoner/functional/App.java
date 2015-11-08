package net.sojoner.functional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App

{
    static void converterExample(){
        System.out.println("Converter Example.");
        Converter converter = new Converter();
        Double tenMilesInKm = converter.apply(1.609, 10.0);
        System.out.println("Apply a function to a value:: " +  tenMilesInKm);

        Function<Double, Double> miles2KmConverter = converter.curry1(1.609);
        System.out.println("Converted curried value:: " + miles2KmConverter.apply(10.0));

        List<Double> collect = Stream.of(10.0, 20.0, 50.0)
                .map(new Converter().curry1(1.609))
                .collect(Collectors.toList());
        System.out.println("Converted curried values:: " + collect);

        List<Double> collect1 = Stream.of(10.0, 20.0, 50.0)
                .map(new Converter()
                        .curry1(9.0 / 5)
                        .andThen(n -> n + 32))
                .collect(Collectors.toList());
        System.out.println("Converted curried values with andThen step:: "+ collect1);

        Function<Double, Double> f2cConverter =
                new Converter()
                        .compose2((Double n) -> n - 32)
                        .curry1(5.0 / 9);

        System.out.println("Converted composed values:: " + f2cConverter.apply(78.0));

    }

    static void salaryExample(){
        System.out.println("Salary Example.");
        double salary = new SalaryCalculator()
                .with(SalaryRules::plusBonus)
                .with(SalaryRules::plusTax)
                .with(s -> s* 0.95) // Just add a new SalaryRule
                .calculate(40.0);
        System.out.println("The payment:: "+ salary);
    }


    public static void main( String[] args )
    {

        converterExample();
        salaryExample();
    }
}
