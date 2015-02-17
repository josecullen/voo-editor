package controller.editor.prenda;

import java.io.IOException;

import model.Prenda;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class PrendaEditorController extends VBox{
	Prenda prenda;
	
	@FXML Tab tabMDO, tabMaquinas;
	MDOTableController mdoTable;
	MaquinaTableController maquinaTable;
	public PrendaEditorController(Prenda prenda) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(	Class.class.getResource("/view/editor/prenda/PrendaEditor.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();
		
		this.prenda = prenda;		
		
		 mdoTable = new MDOTableController(prenda);
		 tabMDO.setContent(mdoTable);
		 
		 maquinaTable = new MaquinaTableController(prenda);
		 tabMaquinas.setContent(maquinaTable);
		 
	}
}
