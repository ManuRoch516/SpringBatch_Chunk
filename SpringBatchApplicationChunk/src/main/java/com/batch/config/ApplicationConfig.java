package com.batch.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource; //Importacion para levantar nuestra Base de Datos

// CONFIGURACIÓN DE LA APLICACIÓN

@Configuration //Indica que será solo para Configuración
public class ApplicationConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setDriverClassName("com.mysql.cj.jdbc.Driver"); //Driver de nuestra conexion
        driverManager.setUrl("jdbc:mysql://localhost:3306/batch_chunk"); //URl de nuestra Base de Datos
        driverManager.setUsername("root"); //Nombre de usuario que tenemos en nuestra Base de Datos Local
        driverManager.setPassword("1234"); //Contraseña que tenemos, en caso de no tener no digitar nada

        return driverManager;
    }
}
