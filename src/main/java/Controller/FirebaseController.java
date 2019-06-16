package Controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseController {

    public FirebaseController(){
        loadFireBase();
        getInformation();
    }

    public void loadFireBase() {
        try {
            System.out.println("Init van FireBase");
            FileInputStream serviceAccount = new FileInputStream("/FireBaseKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://small-world-462b7.firebaseio.com/%22")
                            .build();

            FirebaseApp.initializeApp(options);
        }
        catch (FileNotFoundException e) {

        }
        catch (IOException ie){

        }
    }

    public void getInformation(){

    }
}