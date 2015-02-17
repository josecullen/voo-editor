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
import model.Maquina;
import model.Prenda;
import model.PrendaManoDeObra;
import model.PrendaMaquina;
import service.MDOService;
import service.MaquinaService;
import service.PrendaMDOService;
import service.PrendaMaquinaService;

import com.sun.javafx.collections.ObservableListWrapper;

import controller.components.AddRemoveComboController;

public class MaquinaTableController extends VBox {
	
	MaquinaService maquinaService = new MaquinaService(Main.em);
	PrendaMaquinaService prendaMaquinaService = new PrendaMaquinaService(Main.em);
	
	Prenda prenda;
	
	@FXML TableView<PrendaMaquina> maquinaTable;
	@FXML TableColumn<PrendaMaquina, String> maquinaNombreColumn;
	@FXML TableColumn<PrendaMaquina, Double> maquinaCostoHoraColumn, maquinaCantidadMinutosColumn, maquinaValorColumn;
	@FXML AddRemoveComboController<Maquina> addRemoveCombo;
	
	public MaquinaTableController(Prenda prenda) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Class.class.getResource("/view/editor/prenda/MaquinaTable.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        
        this.prenda = prenda;
        
       
        maquinaTable.setItems(new ObservableListWrapper<PrendaMaquina>(prenda.getPrendaMaquinas()));
        maquinaNombreColumn.setCellValueFactory(new PropertyValueFactory<PrendaMaquina, String>("nombre"));        
        maquinaCantidadMinutosColumn.setCellValueFactory(new PropertyValueFactory<PrendaMaquina, Double>("cantidadHoras"));
        maquinaCostoHoraColumn.setCellValueFactory(new PropertyValueFactory<PrendaMaquina, Double>("costoHora"));
         
        addRemoveCombo.setList(new ObservableListWrapper<Maquina>(maquinaService.findAll()));
        addRemoveCombo.btnAddEntity.setOnAction(value ->{
        	Maquina maquina = addRemoveCombo.cbEntity.getSelectionModel().getSelectedItem(); 
        	if(maquina != null){
        		PrendaMaquina prendaMaquina = prendaMaquinaService.create(prenda, maquina, 1);
        		prenda.addPrendaMaquina(prendaMaquina);
        	}
        });
        addRemoveCombo.btnRemoveEntity.setOnAction(value ->{
        	PrendaMaquina prendaMaquina = maquinaTable.getSelectionModel().getSelectedItem();
        	if(prendaMaquina != null){
        		prendaMaquinaService.remove(prendaMaquina);
        		prenda.removePrendaMaquina(prendaMaquina);
        	}        		
        });
	}
}
