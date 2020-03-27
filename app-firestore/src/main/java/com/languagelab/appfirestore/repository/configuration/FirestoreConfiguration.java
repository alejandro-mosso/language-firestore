package com.languagelab.appfirestore.repository.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirestoreConfiguration {


    /**
    * Revoke access key:
    * 1) Go to https://console.cloud.google.com
    * 2) Search for "Service Accounts" in the search input text.
    * 3) Select Service Accounts / IAM & Admin
    * 4) Select the key and click "Delete" button
    *
    * Create new access key:
    * 1) Go to https://console.firebase.google.com
    * 2) Select "Project Settings" menu option in the mesh icon.
    * 3) Select "Service accounts" tab.
    * 4) Select "Java" radio button.
    * 5) Click "Generate new private key" (a popup will showup, click "Generate key")
    * 6) Save / Download the json file
    * 7) Copy the content of .json file inside resource/firestore.json
    * 8) Package a new jar: <code>mvn clean package</code>
    * 9) Push jar to Git repo
    * 10) Deploy in Heroku: <code>git push heroku master</code>
    *
    */
    @Bean(name = "firestore")
    public Firestore getFirestore() throws IOException {
        Resource resource = new ClassPathResource("firestore.json");
        InputStream serviceAccount = resource.getInputStream();
        FirebaseOptions options = new FirebaseOptions.Builder().
                setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
        FirebaseApp.initializeApp(options);
        return FirestoreClient.getFirestore();
    }


}
