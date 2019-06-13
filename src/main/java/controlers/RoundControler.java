package main.java.controlers;

import javafx.scene.shape.Shape3D;
import main.java.models.RoundModel;
import main.java.observers.RoundObserver;

import java.util.Collection;

public class RoundControler {

    private RoundModel roundModel = new RoundModel();

    public RoundControler(){}


    public void configureRoundData(){
        roundModel.roundData();
    }

    public void configureTextureData(Collection textures) {
        roundModel.textureData(textures);
    }

    public void changeStateRound() {
        roundModel.changeStateRound();
    }

    public void registerObserver(RoundObserver ro) {
        roundModel.register(ro);
    }

}
