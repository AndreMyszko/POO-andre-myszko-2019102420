package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class conSQL {
	
	//CONEXÃO BANCO SQL
    private String connectionUrl = "jdbc:sqlserver://winsrv-sql.winsrv.local:1433;databaseName=DB_Evento;user=sa;password=MySzK@138";

    public Connection conectar() throws SQLException {
    	try {
			return DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    }
    
    //COMANDOS SQL    
	//final String SQL_INSERT_EVENTO = "INSERT INTO TB_Evento(nomeEvento, dataEvento, valorEvento) VALUES(?, ?, ?)";
	final String SQL_SELECT_EVENTO = "SELECT * FROM TB_Evento";	
	final String SQL_SELECT_EVENTO_ID = "SELECT * FROM TB_Evento WHERE codigoEvento = ?";
	final String SQL_INSERT_EVENTO = "INSERT INTO TB_Evento(nomeEvento, dataEvento, valorEvento) VALUES (?, ?, ?)";
	final String SQL_UPDATE_EVENTO = "UPDATE TB_Evento SET nomeEvento = ?, dataEvento = ?, valorEvento = ? WHERE codigoEvento = ?";
	final String SQL_DELETE_EVENTO ="DELETE FROM TB_Evento WHERE codigoEvento = ?";
	final String SQL_COUNT_EVENTO ="SELECT COUNT(*) FROM AS count TB_Evento";
	
    //EXECUTA DECLARAÇÃO
	PreparedStatement pst = null;	
	
	public List<Evento> Listar(){
		
		List<Evento> listaEvento = new ArrayList<Evento>();
		
		try(Connection connection = this.conectar()) {
			pst = connection.prepareStatement(SQL_SELECT_EVENTO);
			ResultSet rs = pst.executeQuery();
			//Evento evento = new Evento();
			
			while(rs.next()) {
				Evento evento = new Evento();
				
				evento.setId(rs.getInt("codigoEvento"));
				evento.setNome(rs.getString("nomeEvento"));
				evento.setData(rs.getDate("dataEvento"));
				evento.setValor(rs.getFloat("valorEvento"));
				
				
				listaEvento.add(evento);
				System.out.println(evento.getId() + " " + " " + evento.getNome() + " " + evento.getData() + " " + evento.getValor());
				
			}	
			
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaEvento;

	}
	
	public int Inserir(Evento evento) {
		int qtd = 0;
		
		try(Connection connection = this.conectar(); 
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_EVENTO);){
					
			pst.setString(1, evento.getNome());
			
			java.util.Date dateDate = evento.getData();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
			String dataSrt = format.format(dateDate);
			pst.setString(2, dataSrt);
			
			pst.setFloat(3, evento.getValor());

			qtd = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return qtd;

	}
	
	public Evento BuscarId(int cod) {
		Evento evento = null;
		
		try(Connection connection = this.conectar();			
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_EVENTO_ID);) {
			
			pst.setInt(1, cod);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				evento = new Evento();
				
				evento.setId(rs.getInt("codigoEvento"));
				evento.setNome(rs.getString("nomeEvento"));
				evento.setData(rs.getDate("dataEvento"));
				evento.setValor(rs.getFloat("valorEvento"));
				
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return evento;
	}
	
	public int Atualizar(Evento evento) {
		int qtd = 0;
		
		try(Connection connection = this.conectar(); 
			PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_EVENTO);){
					
			pst.setString(1, evento.getNome());
			
			java.util.Date dateDate = evento.getData();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
			String dataSrt = format.format(dateDate);
			pst.setString(2, dataSrt);
			
			pst.setFloat(3, evento.getValor());
			
			pst.setInt(4, evento.getId());

			qtd = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return qtd;

	}
	
	public int Excluir(int cod) {
		int qtd = 0;
		
		try(Connection connection = this.conectar();
				
			PreparedStatement pst = connection.prepareStatement(SQL_DELETE_EVENTO);){
			
			pst.setInt(1, cod);
			
			pst.executeUpdate();
					
		}
		
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		return qtd;
	}

	public int Count() {
		int qtd = 0;
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_EVENTO);
				ResultSet rs = pst.executeQuery();){	
				
		           
		      while (rs.next()) {
		           qtd++;
		      }		
		      System.out.println("Total number of rows in ResultSet object = " + qtd);
			}
			
				catch (SQLException e) {
					e.printStackTrace();
				}
		return qtd;
	}
	
	
	
	

}
