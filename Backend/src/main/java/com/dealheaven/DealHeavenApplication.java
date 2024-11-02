package com.dealheaven;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DealHeavenApplication {

    public static void main(String[] args) throws IOException {
        // Check if FirebaseApp has already been initialized
        if (FirebaseApp.getApps().isEmpty()){
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

        }

        SpringApplication.run(DealHeavenApplication.class, args);
    }
}
