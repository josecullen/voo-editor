package controller;

import java.io.IOException;

import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManoDeObra;
import model.Prenda;
import service.BasicServices;
import service.MDOService;
import service.PrendaService;

import com.sun.javafx.collections.ObservableListWrapper;

import controller.editor.EntityEditorController;

public class PrincipalController extends AnchorPane{
	PrendaService prendaService = new PrendaService(Main.em);
	MDOService mdoService = new MDOService(Main.em);
	
	@FXML EntityEditorController<Prenda> editorPrendas;
	@FXML EntityEditorController<ManoDeObra> editorMDO;
	
	public PrincipalController() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(	Class.class.getResource("/view/Principal.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		fxmlLoader.load();
		
		setEditor(editorPrendas, prendaService);
		setEditor(editorMDO, mdoService);
		
	}

	private <E> void setEditor(EntityEditorController<E> editor, BasicServices<E> service) {
		InvalidationListener create = observable ->{
			Main.em.getTransaction().begin();
			E e = service.create(editor.listEntityPanel.addRemovePanel.txtNewEntity.getText());
			editor.listEntityPanel.getList().add(e);
			Main.em.getTransaction().commit();
			editor.listEntityPanel.addRemovePanel.txtNewEntity.setText("");
			editor.listEntityPanel.addRemovePanel.txtNewEntity.requestFocus();
			editor.listEntityPanel.listEntity.getSelectionModel().select(e);
			editor.setEditor();
		};
		
		editor.getListEntityPanelController().setList(
				new ObservableListWrapper<E>(service.findAll())
		);
		
		editor.listEntityPanel.addRemovePanel.btnAddEntity.setOnAction(value -> {
			create.invalidated(null);
		});
		
		editor.listEntityPanel.addRemovePanel.txtNewEntity.setOnAction(value -> {
			create.invalidated(null);
		});
	}
	
}
