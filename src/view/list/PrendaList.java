package view.list;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Prenda;
import ar.com.josecullen.util.RapidFrame;
import ar.josecullen.components.comp.JMCTextListManipulatePanel;

public class PrendaList extends JPanel{
	
	JPanel pTitulo;
	JLabel lblTitulo;
	JMCTextListManipulatePanel<Prenda> tlmp;
	
	public PrendaList() {
		lblTitulo = new JLabel("Prendas");		
		pTitulo = new JPanel();
		pTitulo.add(lblTitulo);
		//tlmp = new JMCTextListManipulatePanel<Prenda>(new PrendaConfigurable<Prenda>());
		
		setLayout(new BorderLayout());
		add(pTitulo, BorderLayout.NORTH);
		add(tlmp, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		RapidFrame frame = new RapidFrame(new PrendaList());
	}

}
