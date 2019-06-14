package views;

import controlers.PlayerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import observable.PlayerObservable;
import observers.PlayerObserver;

public class PuntenView implements PlayerObserver{
	
	String playerId;
	HBox punten;
	PlayerController controller;
	Text puntenAantal = new Text("8");
	Pane pane;
	
	public PuntenView(PlayerController controller, String playerId){
		this.playerId = playerId;
		this.controller = controller;
		loadPane();
	}
	
	public void loadPane(){
		punten = new HBox();
		punten.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
		punten.setPadding(new Insets(5));
		punten.setAlignment(Pos.CENTER);
		Font font = new Font(50);
		puntenAantal.setFont(font);
		puntenAantal.setTextAlignment(TextAlignment.CENTER);
		punten.getChildren().add(puntenAantal);

	}

	public void setPane(Pane myPane) {
		pane = myPane;
		punten.prefWidthProperty().bind(pane.widthProperty());
		punten.prefHeightProperty().bind(pane.heightProperty());
		pane.getChildren().add(punten);
	}

	@Override
	public void Update(PlayerObservable po) {
		puntenAantal.setText(Integer.toString(po.getPuntenAmount()));
		
	}
}
