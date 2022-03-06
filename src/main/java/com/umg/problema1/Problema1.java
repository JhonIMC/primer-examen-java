package com.umg.problema1;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func2;

/**
 *
 * @author john
 */
public class Problema1 {

    public static void main(String[] args) {
        
        List<Persona> personas = new ArrayList<>();
        
        Persona laPersona = new Persona("Jonathan", 22);
        personas.add(laPersona);
        personas.add(new Persona("Samantha", 20));
        personas.add(new Persona("Bryan", 33));
        personas.add(new Persona("Ivan", 44));
        personas.add(new Persona("Andrea", 15));
        
        Observable observable = 
            Observable
            .from(personas.toArray())
            .map((resultado) -> {
                Persona persona = (Persona) resultado;
                    return persona.getEdad();
            })
        .reduce(
            new Func2<Integer, Integer, Integer>() {
                @Override
                public Integer call(Integer numAnterior, Integer numActual) {
                    if (numAnterior > numActual) {
                        return numAnterior;
                    } else {
                        return numActual;
                    }
                }
            }
        );
        observable.subscribe((edadMaxima) -> {
            System.out.println("La edad Maxima es de = "+ edadMaxima + " años."); 
        });
    }
}
