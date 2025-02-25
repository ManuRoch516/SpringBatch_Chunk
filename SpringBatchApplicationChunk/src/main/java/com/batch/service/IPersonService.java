package com.batch.service;

import com.batch.entities.Person;

import java.util.List;

public interface IPersonService {

    Iterable<Person> saveAll(List<Person> persons); //Lista de Personas
}
//Aquí guardaremos a las personas, creando una lista