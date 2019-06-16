package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseService {

    private Firestore firestore;
    private static final String GAMES_PATH = "games";
    private CollectionReference colRef;


    public FirebaseService() {
        Database db = new Database();
        this.firestore = db.getFirestoreDatabase();
        this.colRef = this.firestore.collection(GAMES_PATH);        // Een generieke referentie naar de games documents.
    }


    /**
     * Geeft een update naar de meegeleverde controller
     * op het moment dat er een wijziging in het firebase document plaatsvindt.
     *
     * @param documentId
     * @param controller
     */
    public void listen(String documentId, final FirebaseControllerObserver controller) {

        DocumentReference docRef = this.colRef.document(documentId);

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {

                    controller.update(snapshot);

                    System.out.println("Current data: " + snapshot.getData());
                } else {
                    System.out.print("Current data: null");
                }
            }
        });
    }


    /**
     * Overschrijft een document als het als bestaat of maakt een nieuwe aan.
     * Wees hier dus voorzichtig mee.
     *
     * @param documentId
     * @param docData
     */
    public void set(String documentId, Map<String, Object> docData) {

        // Add a new document (asynchronously) in collection "cities" with id "LA"
        ApiFuture<WriteResult> future = this.colRef.document(documentId).set(docData);


        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    /**
     * Verkrijgen van 1 document op basis van een documentId.
     *
     * @param documentId
     * @return
     */
    public DocumentSnapshot get(String documentId) {

        DocumentReference docRef = this.colRef.document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;

        try {
            document = future.get();

            if (document.exists()) {
                return document;
            } else {

                System.out.println("No such document!");
            }
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }
        return null;

    }


    /**
     * Verwijdert een document op basis van het documentId.
     * @param documentId
     */
    public void delete(String documentId) {
        ApiFuture<WriteResult> writeResult = this.colRef.document(documentId).delete();
    }

    /**
     * Controlleert of opgegeven gevens kloppen.
     * @param username
     * @param password
     */
    public boolean login(String username, String password) {
        DocumentReference docRef = firestore.collection("Accounts").document(username);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document;
        try {
            document = future.get();
            if (document.exists()) return document.get("password").equals(password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}


