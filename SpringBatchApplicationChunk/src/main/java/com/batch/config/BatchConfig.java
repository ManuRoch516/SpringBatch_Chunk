
package com.batch.config;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.batch.steps.PersonItemProcessor;
import com.batch.entities.Person;
import com.batch.steps.PersonItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.batch.steps.PersonItemReader;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Indicamos que esta clase es una configuración de Spring
@EnableBatchProcessing // Habilita la configuración de Spring Batch en la aplicación
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory; // Inyecta JobBuilderFactory para crear jobs
    @Autowired
    private StepBuilderFactory stepBuilderFactory; // Inyecta StepBuilderFactory para crear steps

    @Bean
    public PersonItemReader itemReader() {
        return new PersonItemReader(); // Crea y retorna una instancia de PersonItemReader
    }
    @Bean
    public PersonItemWriter itemWriter() {
        return new PersonItemWriter(); // Crea y retorna una instancia de PersonItemWriter
    }

    @Bean
    public PersonItemProcessor itemProcessor() {
        return new PersonItemProcessor();
    }

    //// todo CONFIGURACIÓN DE LOS HILOS QUE SE USARA EN LA APLICACION
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor(); // JAVA - tipo de objeto de executor que ayuda a gestionar HILOS (Coordinarlos)
        taskExecutor.setCorePoolSize(1); //Con cuantos hilos comenzará nuestra aplicación
        taskExecutor.setMaxPoolSize(5); // Hilos maximos, por si la aplicaciones requiere mas recursos y asi poder agregar hilos, Ayuda adicional
        taskExecutor.setQueueCapacity(5); //Capacidad de tareas en cola

        return taskExecutor;
    }
    // TODO FIN DE LA CONFIGURACIÓN DE LOS HILOS////

    // Configuración del Step que incluye el Reader y el Writer
    @Bean
    public Step readFile() {
        return stepBuilderFactory.get("readFile") // Crea un Step con el nombre "readFile"
                // Definimos el tipo de entrada y salida, en este caso es el mismo (Person)
                .<Person, Person>chunk(10) // El chunk define el tamaño del lote de registros que se procesarán (10 registros a la vez)
                .reader(itemReader()) // Especifica el Reader que se usará para leer los datos
                .writer(itemWriter()) // Especifica el Writer que se usará para escribir los datos
                .processor(itemProcessor()) //Especifica el Proceso que hará nuestra Aplicación
                .taskExecutor(taskExecutor()) //todo Indicamos la configuración de los hilos para que sea Spring quien los maneje
                .build(); // Construye y retorna el Step
    }
    // Configuración del Job que ejecuta el Step
    @Bean
    public Job job() {
        return jobBuilderFactory.get("readFileWithChunk") // Crea un Job con el nombre "readFileWithChunk"
                .start(readFile()) // Especifica que el Job comenzará con el Step "readFile"
                .build(); // Construye y retorna el Job
    }
}




















