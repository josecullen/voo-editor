package MaquinaTableController;

import java.io.IOException;

import model.ManoDeObra;
import model.Prenda;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class MDOEditorController extends VBox{
	ManoDeObra mdo;
	
	public MDOEditorController(ManoDeObra mdo) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(	Class.class.getResource("/view/editor/manodeobra/MDOEditor.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();
		
		this.mdo = mdo;
	}

}
