package com.batch.steps;
import com.batch.entities.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;

                                      //FlatFileItemReader - NOS AYUDA A LEER UN ARCHIVO AUTOMÁTICAMENTE, ponemos el tipo de dato con el que trabajaremos
public class PersonItemReader extends FlatFileItemReader<Person> {

    //Constructor
    public PersonItemReader() {
        //Propiedades
        setName("readPersons");//Esto indica Nombre del paso
        setResource(new ClassPathResource("persons.csv"));//Indica la Ruta del archivo, en este caso el objeto (new ClassPathResource) especifica la carpeta resources
        setLinesToSkip(1);//con esto indicamos Cuantas líneas debe saltar antes de comenzar a leer el archivo, ya que en algunos archivos hay un título en la línea 1
        setEncoding(StandardCharsets.UTF_8.name());//Enconding del archivo, esto para especificar la codificación del archivo
        setLineMapper(getLineMapper());//Configuración de como se va a leer el archivo
    }    // 👆👆👆
        // 👆👆👆
        //👆👆👆 Aquí empieza la Configuración del setLineMapper
    //Toma cada registro, cada fila y lo convierte en un objeto de tipo persona
    public LineMapper<Person>getLineMapper(){
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>(); //El csv a Objetos de tipo persona y el DefaultLineMapper divide la línea en campos/propiedades
        DelimitedLineTokenizer Tokenizer = new DelimitedLineTokenizer(); //toma una línea de texto y la divide en un arreglo de cadenas y cada elemento es un campo.

        //Configuración de lineTokenizer
        String[] columnsToBeInserted = new String[]{"name", "lastName", "age"}; //Cabeceras de nuestro archivo, en la Base de datos
        int[] fields = new int[]{0, 1, 2}; //especificamos el índice de cada uno de los registros de personas, por medio de las cabeceras que tiene

        Tokenizer.setNames(columnsToBeInserted); //Asigna los nombres de las columnas que se esperan en el archivo.
        Tokenizer.setIncludedFields(fields); // Especifica los índices de los campos que se incluirán en el mapeo.
        Tokenizer.setDelimiter(","); //separador de líneas del archivo

        //Configuración de lineMapper
        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>(); //Mapeara lo que lea de las filas y lo mapeara con un objeto de tipo Persona
        fieldSetMapper.setTargetType(Person.class); //Especifica el tipo de objeto al que se mapearán los datos, en este caso de tipo Person
        fieldSetMapper.setDistanceLimit(0); // Establece el límite de distancia para el mapeo. Un valor de 0 significa que no hay límite.

        lineMapper.setLineTokenizer(Tokenizer); //Asigna el tokenizer configurado al lineMapper.
        lineMapper.setFieldSetMapper(fieldSetMapper);// Asigna el fieldSetMapper configurado al lineMapper.

        return lineMapper;
    }

}
