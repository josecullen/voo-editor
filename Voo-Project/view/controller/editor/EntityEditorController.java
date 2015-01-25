package controller.editor;

import java.io.IOException;

import controller.components.ListEntityPanelController;
import factory.EditorFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;

public class EntityEditorController<E> extends SplitPane {
	
	public @FXML ListEntityPanelController<E> listEntityPanel;
	public @FXML TitledPane titledEditorPane;

	
	public EntityEditorController() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(	Class.class.getResource("/view/editor/EntityEditor.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();
		
		listEntityPanel.listEntity.setOnMouseClicked(value -> {
			E e = listEntityPanel.listEntity.getSelectionModel().getSelectedItem();
			titledEditorPane.setContent(EditorFactory.getEditor(e));
			titledEditorPane.setText(e.toString());
		});
		
	}
	
	public ListEntityPanelController<E> getListEntityPanelController(){
		return listEntityPanel;
	}
	
	
	
}
