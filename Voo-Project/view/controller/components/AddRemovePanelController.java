package controller.components;

import java.io.IOException;

import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddRemovePanelController<E> extends AnchorPane{
	
	
	public @FXML TextField txtNewEntity;
	public @FXML Button btnAddEntity, btnRemoveEntity, btnEditEntity;
	
	
	public AddRemovePanelController() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Class.class.getResource("/view/components/AddRemovePanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        
        // Habilita o deshabilita el Bot�n Add seg�n haya algo escrito en el TextField.
        final InvalidationListener controlIsEmpty = observable ->{
        	if(txtNewEntity.getText().length() == 0)
        		btnAddEntity.setDisable(true);
        	else
        		btnAddEntity.setDisable(false);      
        };
        
        txtNewEntity.textProperty().addListener(listener ->	controlIsEmpty.invalidated(txtNewEntity.textProperty()));
        controlIsEmpty.invalidated(null);
       
	}
	
	public String getText(){
		return txtNewEntity.getText();
	}
	public void setText(String text){
		txtNewEntity.setText(text);
	}
	
	
	
}
