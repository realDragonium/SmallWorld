package Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseServiceOwn {

    private Firestore firestore;
    private static final String GAMES_PATH = "games";
    private CollectionReference colRef;


    public FirebaseServiceOwn() {
        Database db = new Database();
        this.firestore = db.getFirestoreDatabase();
        this.colRef = this.firestore.collection(GAMES_PATH);
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
        docRef.addSnapshotListener((snapshot, e) -> {
            System.out.println("test2");
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

    public void playerListen(String player, final FirebaseControllerObserver controller) {
        DocumentReference docRef = firestore.collection("Games").document("First").collection("Spelers").document(player); //.getCollections().forEach()

        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {

                    System.out.println("Current data2: " + snapshot.getData());
                    controller.update(snapshot);

                } else {
                    System.out.print("Current data: null");
                }
            }
        });
    }

    public void testen() {
        DocumentReference docRef = firestore.collection("Games").document("First").collection("Spelers").document("player0");

        // De listener
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    System.out.println("Other data: " + snapshot.getDouble("fiches"));
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
        this.colRef.document(documentId).set(docData);
    }


    /**
     * Verkrijgen van 1 document op basis van een documentId.
     *
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
     *
     * @param username
     * @param password
     * @return
     */
    public boolean login(@NotNull String username, @NotNull String password) {
        DocumentReference docRef = firestore.collection("Accounts").document(username);
        DocumentSnapshot document = getDocSnapshot(docRef);
        if (document.exists()) return document.get("password").equals(password);
        return false;
    }


    /**
     * Registeerd gebruiker, returned false als gebruikers naam bezet is.
     *
     * @param username
     * @param password
     * @return
     */
    public boolean register(String username, String password) {
        DocumentReference docRef = firestore.collection("Accounts").document(username);
        DocumentSnapshot document = getDocSnapshot(docRef);
        if (document.exists()) return false;
        Map<String, String> pass = new HashMap<>();
        pass.put("password", password);
        firestore.collection("Accounts").document(username).set(pass);
        return true;
    }

    /**
     * Gebruikt voor cleanup, de try/catch van future.get() gecentraliseerd.
     *
     * @param docRef
     * @return
     */
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


