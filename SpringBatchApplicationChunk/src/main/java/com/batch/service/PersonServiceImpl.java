package com.batch.service;

import com.batch.entities.Person;
import com.batch.persistence.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService{ //Agregamos/Implementamos el servicio

    //Inyección del DAO
    @Autowired //Tenemos que definir la clase principal como @Service
    private IPersonDAO personDAO;

    @Override
    public Iterable<Person> saveAll(List<Person> personList) { //Retorna un Iterable
        return personDAO.saveAll(personList);
    }
}
