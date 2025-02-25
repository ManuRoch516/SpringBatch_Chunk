package com.batch.steps;

import com.batch.entities.Person;
import org.springframework.batch.item.ItemProcessor; //Se debe importar desde org.springframework.batch.item, ya que hay uno de CHUNK y ese no es

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

                    // ItemProcessor nos llegara un dato, lo procesamos y tenemos que retornar un valor
public class PersonItemProcessor implements ItemProcessor<Person, Person> { //En este caso el objeto de entrada y salida es el mismo "Person"

    @Override
    public Person process(Person person) throws Exception { //Recibimos un Person y retornamos un Person

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();

        person.setCreateAt(formatter.format(date)); //Todos los objetos tendran una fecha
        // Asigna la fecha y hora formateada al campo `createAt` del objeto `Person`.
        // Esto enriquece el objeto `Person` con la fecha y hora en que fue procesado.

        return person; // Retorna el objeto `Person` procesado y enriquecido con la fecha y hora.

    }
}
