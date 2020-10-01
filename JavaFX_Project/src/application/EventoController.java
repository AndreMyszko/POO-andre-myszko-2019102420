package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.conSQL;
import application.Evento;
import application.HomeController;


public class EventoController {

	public int BuscarRegistro(Evento evento) {
		System.out.println("Buscar Registro");
		
		int retorno = 0;
			
		try {
			if (retorno > 0) {
				//evento = listaEvento.stream().filter(x -> x.getId() == cod).findFirst().orElse(null);
				evento = new conSQL().BuscarId(retorno);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		return retorno;
		
		
	}
		
	public int SalvarRegistro(Evento evento) {
		int retorno = 0;
		
		try {
			if (evento != null) {
				evento.setId(HomeController.listaEvento.size() + 1);
				HomeController.listaEvento.add(evento);
				retorno = new conSQL().Inserir(evento);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retorno;
	}
	
	public int AlterarRegistro(Evento evento) {
		int retorno = 0;
		
		try {
			if (evento != null) {
				
				retorno = new conSQL().Atualizar(evento);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retorno;

	}
	
	public int ExcluirRegistro(int cod) {
		int retorno = 0;
		
		try {
			
			if (cod > 0) {
				retorno = new conSQL().Excluir(cod);
			}
			else {
				throw new Exception("Informe o código.");
			}	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retorno;
		
	}
		
}
