package Objects;

public class RattenKracht implements Kracht{

    @Override
    public Kracht getKracht() {
        return this;
    }

    @Override
    public void doAction() {
        System.out.println("ratten hebben geen kracht maar hebben genoeg aan hun aantallen");
    }
}
