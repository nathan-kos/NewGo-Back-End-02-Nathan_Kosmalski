package org.example.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public static Properties lerProperties(){
        Properties properties = new Properties();

        try (InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream("application.properties")) {

            if(input != null) {
                properties.load(input);
            }else{
                throw new RuntimeException("Arquivo de propriedades não encontrado");
            }

        }catch (Exception e){
            throw new RuntimeException("Erro ao ler arquivo de propriedades");
        }

        return properties;
    }
}
