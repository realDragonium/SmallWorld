package Controller;

import Managers.SceneManager;
import Model.InfoModel;
import Observer.infoObserver;
import View.InfoView;

public class InfoController {

    InfoModel infoModel = new InfoModel();

    public InfoController(){
        SceneManager.getInstance().loadInfoscreen(this);
    }

    public void register(infoObserver ob) {
        infoModel.register(ob);
    }

    public void setText(String menuItem){

    }
}
