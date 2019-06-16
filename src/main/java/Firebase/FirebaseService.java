package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseService {

    private Firestore firestore;
    private static final String GAMES_PATH = "games";
    private CollectionReference colRef;


    public FirebaseService() {
        Database db = new Database();
        this.firestore = db.getFirestoreDatabase();
        this.colRef = this.firestore.collection(GAMES_PATH);
    }

    /**
     * Geeft een update naar de meegeleverde controller
     * op het moment dat er een wijziging in het firebase document plaatsvindt.
     * @param documentId
     * @param controller
     */
    public void listen(String documentId, final FirebaseControllerObserver controller) {
        DocumentReference docRef = this.colRef.document(documentId);
        docRef.addSnapshotListener((snapshot, e) -> {
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
        });
    }


    /**
     * Overschrijft een document als het als bestaat of maakt een nieuwe aan.
     * Wees hier dus voorzichtig mee.
     * @param documentId
     * @param docData
     */
    public void set(String documentId, Map<String, Object> docData) {
        this.colRef.document(documentId).set(docData);
    }


    /**
     * Verkrijgen van 1 document op basis van een documentId.
     * @param documentId
     * @return
     */
    public DocumentSnapshot getColRef(String documentId) {

        DocumentReference docRef = this.colRef.document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = getDocSnapshot(docRef);

            if (document.exists()) return document;
            else System.out.println("No such document!");
        return null;

    }


    /**
     * Verwijdert een document op basis van het documentId.
     *
     * @param documentId
     */
    public void delete(String documentId) {
        this.colRef.document(documentId).delete();
    }

    /**
     * Controlleert of opgegeven gevens kloppen.
     * @param username
     * @param password
     */
    public boolean login(@NotNull String username, @NotNull String password) {
        DocumentReference docRef = firestore.collection("Accounts").document(username);
        DocumentSnapshot document = getDocSnapshot(docRef);
        if (document.exists()) return document.get("password").equals(password);
        return false;
    }

    public boolean register(String username, String password) {
        DocumentReference docRef = firestore.collection("Accounts").document(username);
        DocumentSnapshot document = getDocSnapshot(docRef);
        if (document.exists()) return false;
        Map<String, String> pass = new HashMap<>();
        pass.put("password", password);
        firestore.collection("Accounts").document(username).set(pass);
        return true;
    }

    private DocumentSnapshot getDocSnapshot(DocumentReference docRef) {
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}


