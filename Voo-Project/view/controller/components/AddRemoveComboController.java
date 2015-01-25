package controller.components;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

public class AddRemoveComboController<E> extends AnchorPane {
	
	public @FXML ChoiceBox<E> cbEntity;
	public @FXML Button btnAddEntity, btnRemoveEntity;
	
	public ObservableList<E> list;
	
	public AddRemoveComboController() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Class.class.getResource("/view/components/AddRemoveComboPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();        
	}
	
	public void setList(ObservableList<E> list){
		this.list = list;
		cbEntity.setItems(list);
	}
	public ObservableList<E> getList(){
		return list;
	}
	
	
	
}


