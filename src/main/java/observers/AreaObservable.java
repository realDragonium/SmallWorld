package observers;

public interface AreaObservable {
	public void register(AreaObserver ao);
	public void notifyAllObservers();
	public boolean isHovering();
	public boolean isSelected();
	public int getRaceFichesAmount();
}
