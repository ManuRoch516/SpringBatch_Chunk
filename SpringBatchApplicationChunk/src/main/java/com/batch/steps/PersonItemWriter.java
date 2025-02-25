package com.batch.steps;

import com.batch.entities.Person;
import com.batch.service.IPersonService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// Clase PersonItemWriter que implementa la interfaz ItemWriter de Spring Batch
public class PersonItemWriter implements ItemWriter<Person> { // Le indicamos con qué tipo de dato vamos a trabajar (Person)

    @Autowired
    private IPersonService personService; // Inyectamos el servicio IPersonService para interactuar con la base de datos

    @Override // Sobrescribimos el metodo write de la interfaz ItemWriter
    public void write(List<? extends Person> list) throws Exception {
        // Este metodo recibe una lista de objetos Person que deben ser guardados en la base de datos

        list.forEach(person -> person.toString()); // Recorremos la lista y llamamos al metodo toString() de cada objeto Person

        personService.saveAll((List<Person>) list); // Guardamos todos los objetos Person en la base de datos usando el metodo saveAll del servicio IPersonService
    }
}