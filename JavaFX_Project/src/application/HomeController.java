package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class HomeController extends conSQL {

	//ELEMENTOS
	@FXML private Label labelTotal;
	
	@FXML private TextField textCodigo;
	@FXML private TextField textData;
	@FXML private TextField textValor;
	@FXML private TextField textNome;
	
	@FXML private TextArea textTabela;
	
	@FXML private Button btnBuscar;
	@FXML private Button btnNovo;
	@FXML private Button btnExcluir;
	@FXML private Button btnSalvar;
	@FXML private Button btnEditar;
	@FXML private Button btnVoltar;
	@FXML private Button btnSalvarAlteracao;
	@FXML private Button btnListarEventos;
	


	//AÇÕES
	public static List<Evento> listaEvento = new ArrayList<Evento>();
	
	
	@FXML
	void BuscarRegistro(ActionEvent event) {
		System.out.println("Buscar Registro");
		
		textCodigo.setDisable(true);		
		textNome.setDisable(true);
		textData.setDisable(true);
		textValor.setDisable(true);
		
		
		btnBuscar.setVisible(false);
		btnNovo.setVisible(false);
		btnExcluir.setVisible(true);
		btnEditar.setVisible(true);
		btnSalvar.setVisible(false);
		btnVoltar.setVisible(true);
		btnSalvarAlteracao.setVisible(false);

		textTabela.setText("");
		
		String IdString = textCodigo.getText();
		Evento evento = null;
		if (!IdString.equals("")) {
			try {
				int id = Integer.valueOf(IdString);
				evento = new conSQL().BuscarId(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if (evento != null) {
				textCodigo.setText(evento.getId() + "");
				textNome.setText(evento.getNome());
				textData.setText(evento.getData() + "");
				textValor.setText(evento.getValor() + "");
				
				
				textTabela.setText(evento.toString());
			}
			
		}
		int i = new conSQL().Count();
		labelTotal.setText("Total: " + String.valueOf(i));

		btnListarEventos.setVisible(false);
		
	}
	
	@FXML
	private void ListarEventos() {
		ListarRegistro();
		btnListarEventos.setVisible(false);
	}
	
	@FXML
	private void ListarRegistro() {
		System.out.println("Inserir Registro");
		
		textCodigo.setText("");	
		textCodigo.setDisable(false);
		
		textNome.setText("");
		textNome.setDisable(true);
		
		textData.setText("");
		textData.setDisable(true);
		
		textValor.setText("");
		textValor.setDisable(true);
		
		textTabela.setText("");
				
		
		btnBuscar.setVisible(true);
		btnNovo.setVisible(true);
		btnExcluir.setVisible(false);
		btnEditar.setVisible(false);
		btnSalvar.setVisible(false);
		btnVoltar.setVisible(false);
		btnSalvarAlteracao.setVisible(false);
		btnListarEventos.setVisible(false);
		
		List<Evento> listaEvento = new conSQL().Listar();
		listaEvento.forEach(evento -> {
			textTabela.appendText(evento.toString() + "\n");
		});

		int i = new conSQL().Count();
		labelTotal.setText("Total: " + String.valueOf(i));
	}

	@FXML
	private void InserirRegistro() {
		System.out.println("Inserir Registro");
		
		textCodigo.setText("");
		textCodigo.setDisable(true);
		
		textNome.setText("");
		textNome.setDisable(false);
		
		textData.setText("");
		textData.setDisable(false);
		
		textValor.setText("");
		textValor.setDisable(false);
		
		btnBuscar.setVisible(false);
		btnNovo.setVisible(false);
		btnExcluir.setVisible(false);
		btnEditar.setVisible(false);
		btnSalvar.setVisible(true);
		btnVoltar.setVisible(true);
		btnSalvarAlteracao.setVisible(false);
		btnListarEventos.setVisible(false);
		
	}
	
	@FXML
	void SalvarRegistro(ActionEvent event) {

		System.out.println("Salvar Registro");
		
		//Alert alert1 = new Alert(AlertType.CONFIRMATION, "Deseja salvar registro " + textNome.getText() + "?");
		//alert1.show();
		
		//textCodigo.setText("");	
		//textCodigo.setDisable(false);
		
		//textNome.setText("");
		//textNome.setDisable(true);
		
		//textData.setText("");
		//textData.setDisable(true);
		
		//textValor.setText("");
		//textValor.setDisable(true);

		try {
			textData.getText();
			float valorFlo = Float.parseFloat(textValor.getText());
			
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		        Date parsed = null;
				try {
					parsed = format.parse(textData.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        java.sql.Date sql = new java.sql.Date(parsed.getTime());

			
			new EventoController().SalvarRegistro(new Evento(textNome.getText(), sql , valorFlo));
			ListarRegistro();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		int i = new conSQL().Count();
		labelTotal.setText("Total: " + String.valueOf(i));
					
	}

	@FXML
	public int SalvarAlteracao() {
		
		btnSalvarAlteracao.setVisible(false);
		btnListarEventos.setVisible(false);
		
		Evento evento = new Evento();
		
		evento.setId(Integer.valueOf(textCodigo.getText()));
		evento.setNome(textNome.getText());
		
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date parsed = null;
			try {
				parsed = format.parse(textData.getText());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        java.sql.Date sql = new java.sql.Date(parsed.getTime());
		
		
		evento.setData(sql);
		evento.setValor(Float.parseFloat(textValor.getText()));
		int qtd = 0;
			
		new EventoController().AlterarRegistro(evento);	
		
		ListarRegistro();
		
		return qtd;

	}
	
	@FXML
	private void EditarRegistro() {
		System.out.println("Editar Registro");
		
		textCodigo.setDisable(true);		
		textNome.setDisable(false);
		textData.setDisable(false);
		textValor.setDisable(false);

				

		btnBuscar.setVisible(false);
		btnNovo.setVisible(false);
		btnExcluir.setVisible(false);
		btnEditar.setVisible(false);
		btnSalvar.setVisible(false);
		btnSalvarAlteracao.setVisible(true);
		btnListarEventos.setVisible(false);

		
		///*
		
		
		//labelTotal.setText("Total Itens: " + "77");
		//*/
	}
	
	@FXML
	private void ExcluirRegistro(ActionEvent event) {
		
		System.out.println("Exluir Registro");


		btnSalvarAlteracao.setVisible(false);

		textTabela.setText("");
		
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	
    	alert.setTitle("Excluir");
    	alert.setHeaderText("Deseja realmente excluir este evento?");  	
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	 if (result.isPresent() && result.get() == ButtonType.OK) {
    			try {
    				int cod = Integer.valueOf(textCodigo.getText());
    				
    				new EventoController().ExcluirRegistro(cod);
    				
    				textCodigo.setText("");	
    				textNome.setText("");
    				textData.setText("");
    				textValor.setText("");

    				int i = new conSQL().Count();
    				labelTotal.setText("Total: " + String.valueOf(i));
    				
    				btnListarEventos.setVisible(false);
    				
    				ListarRegistro();

    			} catch (Exception e) {
    				// TODO: handle exception
    			}

    	 }    	


	}	
	
	@FXML
	private void Sair(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	
    	alert.setTitle("Sair");
    	alert.setHeaderText("Deseja realmente sair?");  	
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	 if (result.isPresent() && result.get() == ButtonType.OK) {
    	     System.exit(0);
    	 }    	

	}
	

}






