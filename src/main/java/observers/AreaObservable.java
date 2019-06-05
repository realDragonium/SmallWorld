package observers;

public interface AreaObservable {
	public void register(observers.AreaObserver ao);
	public void notifyAllObservers();
	public boolean isHovering();
	public boolean isSelected();
}
