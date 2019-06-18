package Objects;

public class HumanKracht implements Kracht{

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
        System.out.println("op fields heb je een extra punt");
    }
}
