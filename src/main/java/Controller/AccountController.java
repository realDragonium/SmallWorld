package Controller;

import Model.AccountModel;

public class AccountController {

    AccountModel model;

    public AccountController(String username) {
        model = new AccountModel(username);
    }

    public String getAccountName(){
        return model.getAccountName();
    }

    public void setPlayerId(String id){
        model.setPlayerId(id);
    }

    public String getPlayerId(){
        return model.getPlayerId();
    }
}
