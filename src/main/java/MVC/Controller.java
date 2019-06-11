package MVC;

public class Controller {
    Model model = new Model();
    Controller2 controller = new Controller2();



    public void addValue() {
        model.addValue();
    }

    public void registreer(Observer ob){
        model.register(ob);
    }
}
