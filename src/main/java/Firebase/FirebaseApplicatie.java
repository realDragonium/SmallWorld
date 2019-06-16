package Firebase;

import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;

import java.io.IOException;

public class FirebaseApplicatie {

    private Firestore db;

    public FirebaseApplicatie() throws IOException, InterruptedException {

        Database setup = new Database();
        db = setup.getFirestoreDatabase();

        DocumentReference docRef = db.collection("Games").document("Game 1");

        // De listener
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {

                    System.out.println("Current data: " + snapshot.getData());
                } else {
                    System.out.print("Current data: null");
                }

            }
        });

//        this.waitForFirebaseObservable(100000);
    }

//    public synchronized void waitForFirebaseObservable(int ms) throws InterruptedException {
//        // Deze methode gebruiken we alleen om het programma niet te laten beeïndigen.
//        // Zodat we kunnen 'luisteren' naar de updates.
//
//        int counter = 0;
//        for (int i = 0; i < ms; i++) {
//
//            if(counter % 1000 == 0) {
//                System.out.println("waiting for: " + counter + "ms");
//            }
//            this.wait(1);
//            counter++;
//        }
//        System.out.println("Exiting program");
//    }

    public Firestore getFireStor(){
        return db;
    }

}
