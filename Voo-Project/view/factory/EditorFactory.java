package factory;

import java.io.IOException;

import controller.editor.prenda.PrendaEditorController;
import model.Prenda;
import javafx.scene.Node;

public class EditorFactory {
	public static Node getEditor(Object o) {
		try {
			if (o instanceof Prenda)	return new PrendaEditorController((Prenda)o);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
