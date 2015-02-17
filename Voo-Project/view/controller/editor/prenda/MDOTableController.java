package controller.editor.prenda;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import main.Main;
import model.ManoDeObra;
import model.Prenda;
import model.PrendaManoDeObra;
import service.MDOService;
import service.PrendaMDOService;

import com.sun.javafx.collections.ObservableListWrapper;

import controller.components.AddRemoveComboController;

public class MDOTableController extends VBox {
	
	MDOService MDOService = new MDOService(Main.em);
	PrendaMDOService PrendaMDOService = new PrendaMDOService(Main.em);
	
	Prenda prenda;
	
	@FXML TableView<PrendaManoDeObra> mdoTable;
	@FXML TableColumn<PrendaManoDeObra, String> mdoNombreColumn;
	@FXML TableColumn<PrendaManoDeObra, Double> mdoCostoHoraColumn, mdoCantidadHorasColumn, mdoValorColumn;
	@FXML AddRemoveComboController<ManoDeObra> addRemoveCombo;
	
	public MDOTableController(Prenda prenda) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Class.class.getResource("/view/editor/prenda/MDOTable.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        
        this.prenda = prenda;
        
      //  mdoTable.setItems(prenda.prendaManoDeObrasProperty());
        
        mdoTable.setItems(new ObservableListWrapper<PrendaManoDeObra>(prenda.getPrendaManoDeObras()));
        mdoNombreColumn.setCellValueFactory(new PropertyValueFactory<PrendaManoDeObra, String>("nombre"));        
        mdoCantidadHorasColumn.setCellValueFactory(new PropertyValueFactory<PrendaManoDeObra, Double>("cantidadHoras"));
        mdoCostoHoraColumn.setCellValueFactory(new PropertyValueFactory<PrendaManoDeObra, Double>("costoHora"));
        
        
        addRemoveCombo.setList(new ObservableListWrapper<ManoDeObra>(MDOService.findAll()));
        addRemoveCombo.btnAddEntity.setOnAction(value ->{
        	ManoDeObra manoDeObra = addRemoveCombo.cbEntity.getSelectionModel().getSelectedItem(); 
        	if(manoDeObra != null){
        		PrendaManoDeObra prendaMDO = PrendaMDOService.create(prenda, manoDeObra, 1);
        		prenda.addPrendaManoDeObra(prendaMDO);
        	}
        });
        addRemoveCombo.btnRemoveEntity.setOnAction(value ->{
        	PrendaManoDeObra prendaMDO = mdoTable.getSelectionModel().getSelectedItem();
        	if(prendaMDO != null){
        		PrendaMDOService.remove(prendaMDO);
        		prenda.removePrendaManoDeObra(prendaMDO);
        	}        		
        });
	}
}
