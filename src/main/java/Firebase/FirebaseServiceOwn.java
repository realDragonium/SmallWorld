package Firebase;

import Controller.GameController;
import Managers.SceneManager;
import Objects.RaceFiche;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.EventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.database.annotations.Nullable;
import javafx.application.Platform;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirebaseServiceOwn {
    private Firestore firestore;
    private CollectionReference colRef;
    private DocumentReference gameRef;

    public FirebaseServiceOwn() {
        Database db = new Database();
        this.firestore = db.getFirestoreDatabase();
        this.colRef = this.firestore.collection("Games");
    }

    public void setGame(String lobbyName){
        gameRef = colRef.document(lobbyName);
    }

    public Firestore getFireStore(){
        return firestore;
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
        docRef.addSnapshotListener((new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    controller.update(snapshot);
                } else {
                }
            }
        }));
    }

    public void playerListen(String player, final FirebaseControllerObserver controller) {
        DocumentReference docRef = gameRef.collection("Players").document(player);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.exists()) controller.update(snapshot);
            }
        });
    }

    //AreaRegister
    public void AreaListener(String areaId, final FirebaseControllerObserver controller) {
        DocumentReference docRef = gameRef.collection("Areas").document(areaId);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.exists()) controller.update(snapshot);
            }
        });
    }

    // create a lobby
    public boolean createLobby(int playerAmount, String lobbyNaam, String name) {
        HashMap<String, Object> lobbySettings = new HashMap<>();
        lobbySettings.put("Naam", lobbyNaam);
        lobbySettings.put("Amount", playerAmount);
        lobbySettings.put("begin", false);
        lobbySettings.put("player1", name);
        lobbySettings.put("player2", null);
        lobbySettings.put("player3", null);
        lobbySettings.put("player4", null);
        firestore.collection("Lobby").document(lobbyNaam).set(lobbySettings);
        return true;
    }

    //Is er nog plek in deze lobby?
    public int joinLobby(String lobbyNaam, String Name) {
        DocumentReference docRef = firestore.collection("Lobby").document(lobbyNaam);
        DocumentSnapshot doc = null;
        try {
            doc = docRef.get().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Map<String, Object> lobbySet = doc.getData();
        for (int i = 1; i < ((int)lobbySet.get("Amount") + 1); i++) {
            if (lobbySet.get("player" + i) == null) {
                docRef.update("player" + i, Name);
                System.out.println("join lobby");
                return i;
            }
        }
        return 0;
    }

    public void leaveLobby(String lobbyNaam, String Name) {
        DocumentReference docRef = firestore.collection("Lobby").document(lobbyNaam);
        DocumentSnapshot doc = null;
        try {
            doc = docRef.get().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Map<String, Object> lobbySet = doc.getData();
        for (int i = 1; i < 5; i++) {
            if (lobbySet.get("player" + i).equals(Name)) {
                docRef.update("player" + i, null);
                if (i == 1) firestore.collection("Lobby").document(lobbyNaam).delete();
                return;
            }
        }
    }


    //InLobbyListener
    public void inLobbyListener(String lobbyName, final FirebaseControllerObserver controller) {
        DocumentReference docRef = firestore.collection("Lobby").document(lobbyName);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    controller.update(snapshot);
                }
            }
        });
    }


    //Werkt wel niet toegepast, runtime items toevoegen vind javafx niet leuk.
    //LobbyListener
    public void LobbyListener(final FirebaseLobbyObserver controller) {
        CollectionReference docRef = firestore.collection("Lobby");
        docRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                List<String> buttonLijst = new ArrayList<>();
                for (DocumentSnapshot doc : snapshot.getDocuments()) {
                    //System.out.println("fb: "+doc.getId());
                    buttonLijst.add(doc.getId());
                }
                controller.update(buttonLijst);
            }
        });
    }

    // retrieves active lobbies
    public List<String> getActiveLobbies() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> querys = firestore.collection("Lobby").get();
        QuerySnapshot query = querys.get();
        List<String> namen = new ArrayList<>();
        for (QueryDocumentSnapshot QDoc : query) {
            namen.add(QDoc.getId());
            System.out.println(QDoc.getId());
        }
        return namen;
    }

    //player Updates
    public void playerUpdateFiches(String player, int fichesCount) {
        DocumentReference docRef = gameRef.collection("Players").document(player);
        docRef.update("fiche", fichesCount);
    }
    public void playerUpdate(String id, Map<String, Object> info) {
        gameRef.collection("Players").document(id).update(info);
    }


    //areaUpdates
    public void areaUpdateFiches(String areaId, int count) {
        DocumentReference docRef = gameRef.collection("Areas").document(areaId);
        docRef.update("fiches", count);
    }

    //Areas setten in firebase
    public void setAreas(String areaId, Map<String, Object> area) {
        gameRef.collection("Areas").document(areaId).set(area);
    }

    public void startGame(String lobbyNaam) {
        firestore.collection("Lobby").document(lobbyNaam).update("begin", true);
        setGame(lobbyNaam);

//        firestore.collection("Games").document(lobbyNaam).collection("Players").document("player1").set(info);
    }


    public void registerPlayer(String playerId, Map<String, Object> info){
        gameRef.collection("Players").document(playerId).set(info);
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


