package com.batch.entities;
import javax.persistence.*;

@Entity //Indicamos que se trata de una ENTIDAD y se mapeará a una tabla en la base de datos.
@Table(name = "persons") //Indicamos que se creará una tabla con el nombre de persons
public class Person {

    @Id //Indicamos el Identificador para la Base de Datos, clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera el id de manera automática, autoincrementa
    private Long id;

    private String name;

    @Column(name = "last_name") //Mapea el campo lastName a la columna last_name en la tabla.
    private String lastName;

    private String age;

    private String createAt; //Este atributo se introducirá en el proceso, al momento de crear un person indicara la fecha de creación

    //GETTERS Y SETTERS

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    //@Data //Con esto se crean los constructores (setter, getters, toString)
}
