package controller.editor.prenda;

import java.io.IOException;

import model.Prenda;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class PrendaEditorController extends VBox{
	
	public PrendaEditorController(Prenda prenda) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(	Class.class.getResource("/view/editor/prenda/PrendaEditor.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();
	}
}
