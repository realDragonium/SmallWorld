package Firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FireBaseController {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/FireBaseKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://small-world-462b7.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();

        FireBaseController app = new FireBaseController();

//        app.voegAccountToe(db, "dev", "admin");
        app.update(db);
        System.out.println(app.controlleerPassWord(db, "dev", "admin"));

        // insert/ update
//        app.update(db);
//		// getColRef
//        app.getQuoteFromFirestore(db);
//		// delete
//		app.deleteFromFirestore(db);
    }

    public void deleteFromFirestore(Firestore db) throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("sampleData").document("my_new_document");
        ApiFuture<WriteResult> future = docRef.delete();
        WriteResult result = future.get();

        System.out.println("Successfully deleted at: " + future.get().getUpdateTime());
    }


    private void getQuoteFromFirestore(Firestore db) throws InterruptedException, ExecutionException {

        DocumentReference docRef = db.collection("sampleData").document("my_new_document");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            String quote = (String) document.get("Gandhi");
            System.out.println(quote);
        } else {
            System.out.println("No such document :(");
        }
    }

    public void update(Firestore db) throws IOException, InterruptedException, ExecutionException {
        // insert & update
        // Als een document nog niet bestaat wordt het aangemaakt.
        // Als een document al bestaat wordt het aagepast.
        HashMap<String, String> password = getSomethingToInsert();

        ApiFuture<WriteResult> future = db.collection("Accounts")
                .document("dev")
                .set(password);

        System.out.println("Successfully updated at: "
                + future.get().getUpdateTime());
    }

    private HashMap<String, String> getSomethingToInsert() throws IOException {
        HashMap<String, String> quoteHashMap = new HashMap<>();
        quoteHashMap.put("password", "Hallo niemand");

        return quoteHashMap;
    }

    public boolean controlleerPassWord(Firestore db, String username, String password) throws IOException, InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection("Accounts").document("admin");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            String pass = (String) document.get("password");
            return password.equals(pass);
        } else {
            System.out.println("No such document :(");
        }
        return false;
    }

    public void voegAccountToe(Firestore db, String username, String password) throws IOException, InterruptedException, ExecutionException {
        Map<String, String> pass = new HashMap<>();
        pass.put("password", password);
        ApiFuture<WriteResult> future = db.collection("Accounts").document(username).set(pass);

        System.out.println("Update time : " + future.get().getUpdateTime());
    }
}