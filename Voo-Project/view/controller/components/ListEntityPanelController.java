package controller.components;

import java.io.IOException;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ListEntityPanelController<E> extends VBox{
	
	public @FXML AddRemovePanelController<E> addRemovePanel;
	public @FXML ListView<E> listEntity;
	
	ObservableList<E> list;
	
	public ListEntityPanelController() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Class.class.getResource("/view/components/ListEntityPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        
        // Habilita y deshabilita los botones según haya algo seleccionado en la lista.
        InvalidationListener controlIsSelected = observable -> {
        	if(listEntity.selectionModelProperty().get().getSelectedItem() == null){
        		addRemovePanel.btnRemoveEntity.setDisable(true);
        		addRemovePanel.btnEditEntity.setDisable(true);
        	}else{
        		addRemovePanel.btnRemoveEntity.setDisable(false);
        		addRemovePanel.btnEditEntity.setDisable(false);
        	}
        };
        
        listEntity.setOnMouseClicked(value -> controlIsSelected.invalidated(null));
        controlIsSelected.invalidated(null);
	}
	
	public void setList(ObservableList<E> list){
		this.list = list;
		listEntity.setItems(list);
	}
	public ObservableList<E> getList(){
		return list;
	}
	
	
}
