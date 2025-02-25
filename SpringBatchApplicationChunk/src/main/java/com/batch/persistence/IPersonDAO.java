package com.batch.persistence;

import com.batch.entities.Person;
import org.springframework.data.repository.CrudRepository;

                //CrudRepository< Entidad, Tipo de la Primary Key de nuestra entidad >
public interface IPersonDAO extends CrudRepository<Person, Long> { //Creamos nuestra capa de Persistencia
}
