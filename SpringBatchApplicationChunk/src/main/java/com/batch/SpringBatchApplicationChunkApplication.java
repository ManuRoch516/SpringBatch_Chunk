package com.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpringBatchApplicationChunkApplication {

	@Autowired
	private JobLauncher jobLauncher; //Se encargará de Correr nuestra Aplicación

	@Autowired
	private Job job; //Inyectamos el Job, ya que cuenta con un @Bean desde el BatchConfig.java

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplicationChunkApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> { // Expresión lambda que implementa el metodo `run` de la interfaz `CommandLineRunner`. Se ejecutará cuando la aplicación se inicie.
			JobParameters jobParameters = new JobParametersBuilder() // Crea un nuevo objeto `JobParametersBuilder` para construir los parámetros del trabajo (Job).
					.addString("name", "chunk") // Agrega un parámetro de tipo String con el nombre "name" y el valor "chunk".

					// Agrega un parámetro de tipo Long con el nombre "id" y el tiempo actual en milisegundos, ID único para el Job.
					.addLong("id", System.currentTimeMillis())
					.addDate("date", new Date()) // Agrega un parámetro de tipo Date con el nombre "date" y el valor de la fecha y hora actual.
					.toJobParameters(); // Convierte el `JobParametersBuilder` en un objeto `JobParameters` que se puede pasar al `JobLauncher`.

			// Ejecuta el Job utilizando el `JobLauncher`, pasando el Job y los parámetros configurados. Inicia la ejecución del Job en la aplicación.
			jobLauncher.run(job, jobParameters);
		};
	}
}
