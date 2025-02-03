package com.ccapstools_app.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

    Logger logger = Logger.getLogger(FirebaseConfig.class.getName());

    @Bean
    FirebaseApp firebaseApp() throws IOException {
        String firebasePath = System.getenv("FIREBASE_CREDENTIALS");
        System.out.println("Caminho do arquivo JSON do Firebase: " + firebasePath);

        if (firebasePath == null || firebasePath.isEmpty()) {
            throw new IllegalArgumentException("FIREBASE_CREDENTIALS env var must be set");
        }

        File file = new File(firebasePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo de credenciais do Firebase não encontrado: " + firebasePath);
        }

        // Teste de leitura do arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("Arquivo lido com sucesso!");
        } catch (IOException e) {
            throw new IOException("Erro ao ler o arquivo de credenciais do Firebase.", e);
        }

        FileInputStream serviceAccount = new FileInputStream(firebasePath);
        FirebaseOptions option = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(option);
    }

}
