package com.umg.problema2;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func2;
import rx.observables.MathObservable;

/**
 *
 * @author john
 */
public class Problema2 {

    public static void main(String[] args) {
        
        List<Producto> productos = new ArrayList<>();
        
        Producto losProductos = new Producto("sp4", 300);
        productos.add(losProductos);
        productos.add(new Producto("gamecube", 300));
        productos.add(new Producto("external disk", 200));
        productos.add(new Producto("laptop", 800));
        productos.add(new Producto("vr", 230));
        
        // Promedio de los precios de los productos
        Observable<Producto> productoObservable = Observable.from(productos);
        
        MathObservable
                .from(productoObservable)
                .averageInteger(Producto::getPrecio)
                .subscribe((promedioPrecio) -> {
                   System.out.println("Promedio de los precios = " + promedioPrecio); 
                });
        
        // Observable de la Suma de los Productos
        Observable miObservableSuma =
            Observable
                .from(productos.toArray())
                .map((resultado) -> {
                    Producto producto = (Producto) resultado;
                        return producto.getPrecio();
                })
            .reduce(
                new Func2<Integer, Integer, Integer>(){
                    @Override
                    public Integer call(Integer acumulador, Integer actual) {
                        return acumulador + actual;
                    }
                }
            );
        
        // Observable del Producto com mayor precio
        Observable miObservableMax =
            Observable
                .from(productos.toArray())
                .map((resultado) -> {
                    Producto producto = (Producto) resultado;
                        return producto.getPrecio();
                })
            .reduce(
                new Func2<Integer, Integer, Integer>(){
                    @Override
                    public Integer call(Integer acumulador, Integer actual) {
                        if (acumulador > actual) {
                            return acumulador;
                        } else {
                            return actual;
                        }
                    }
                }
            );
        
        miObservableSuma.subscribe((suma) -> {
            System.out.println("Suma de precios de los productos = " + suma);
        });
        
        miObservableMax.subscribe((precioMax) -> {
            System.out.println("Precio máximo de los productos = " + precioMax);
        });
    }
}
