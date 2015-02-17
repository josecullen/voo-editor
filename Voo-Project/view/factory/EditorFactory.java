package factory;

import java.io.IOException;

import MaquinaTableController.MDOEditorController;
import controller.editor.prenda.PrendaEditorController;
import model.ManoDeObra;
import model.Prenda;
import javafx.scene.Node;

public class EditorFactory {
	public static Node getEditor(Object o) {
		try {
			if (o instanceof Prenda)	return new PrendaEditorController((Prenda)o);
			if (o instanceof ManoDeObra)return new MDOEditorController((ManoDeObra)o);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
