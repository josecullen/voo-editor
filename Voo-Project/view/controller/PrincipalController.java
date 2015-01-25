package controller;

import java.io.IOException;

import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.Prenda;
import service.PrendaService;

import com.sun.javafx.collections.ObservableListWrapper;

import controller.editor.EntityEditorController;

public class PrincipalController extends AnchorPane{
	PrendaService prendaService = new PrendaService(Main.em);
	
	
	@FXML EntityEditorController<Prenda> editorPrendas;
	
	public PrincipalController() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(	Class.class.getResource("/view/Principal.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();
		
		InvalidationListener createPrenda = observable ->{
			Main.em.getTransaction().begin();
			Prenda prenda = prendaService.create(editorPrendas.listEntityPanel.addRemovePanel.txtNewEntity.getText());
			editorPrendas.listEntityPanel.getList().add(prenda);
			Main.em.getTransaction().commit();
			editorPrendas.listEntityPanel.addRemovePanel.txtNewEntity.setText("");
			editorPrendas.listEntityPanel.addRemovePanel.txtNewEntity.requestFocus();
			editorPrendas.listEntityPanel.listEntity.getSelectionModel().select(prenda);
		};
		
		editorPrendas.getListEntityPanelController().setList(
				new ObservableListWrapper<Prenda>(prendaService.findAll())
		);
		
		editorPrendas.listEntityPanel.addRemovePanel.btnAddEntity.setOnAction(value -> {
			createPrenda.invalidated(null);
		});
		
		editorPrendas.listEntityPanel.addRemovePanel.txtNewEntity.setOnAction(value -> {
			createPrenda.invalidated(null);
		});
		
		
	}
	
}
