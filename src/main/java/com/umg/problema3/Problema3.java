package com.umg.problema3;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.MathObservable;

/**
 *
 * @author john
 */
public class Problema3 {

    public static void main(String[] args) {
        Integer[] numeros = {2, 5, 6, 8, 10, 35, 2, 10};
        
        Observable<Integer> numObservable = Observable.from(numeros);
        
        // Promedio del Arreglo
        MathObservable
            .from(numObservable)
            .averageInteger(numObservable)
            .subscribe((promedio) -> {
                System.out.println("Promedio del Arreglo = " + promedio);
            });
        
        // Valores mayores o iguales a 10
        Observable
            .from(numeros)
            .filter(
                new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer result){
                        return result >= 10;
                    }
                }
            )
            .subscribe((valores) -> {
               System.out.println("Valores >= 10 = " + valores); 
            });
        
        // Sumatoria de los valores
        Observable sumatoriaReduce = Observable.from(numeros)
            .reduce(
                new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer numAnterior, Integer numActual) {
                        Integer suma = numAnterior + numActual;
                        return suma;
                    }
                }
            );
        
        sumatoriaReduce.subscribe((suma) -> {
            System.out.println("Suma del Arreglo = " + suma); 
        });
    }
}
