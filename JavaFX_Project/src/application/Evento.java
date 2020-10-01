package application;

import java.util.Date;

public class Evento {

	//ATRIBUTOS
	private int Id;
	private String Nome;
	private Date Data;
	private float Valor;
	
	
	
	//CONSTRUTORES
	public Evento() {
	}
	
	public Evento(String nome, Date data, float valor) {
		super();
		Nome = nome;
		Data = data;
		Valor = valor;
	}
	
	public Evento(int id, String nome, Date data, float valor) {
		super();
		Id = id;
		Nome = nome;
		Data = data;
		Valor = valor;
	}
		

	//GET 'n SET
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}

	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
		Data = data;
	}

	public float getValor() {
		return Valor;
	}
	public void setValor(float valor) {
		Valor = valor;
	}

	
	
	//HASH CODE 'n EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Data == null) ? 0 : Data.hashCode());
		result = prime * result + Id;
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		result = prime * result + Float.floatToIntBits(Valor);
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (Data == null) {
			if (other.Data != null)
				return false;
		} else if (!Data.equals(other.Data))
			return false;
		if (Id != other.Id)
			return false;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		if (Float.floatToIntBits(Valor) != Float.floatToIntBits(other.Valor))
			return false;
		return true;
	}

	
	
	//TO STRING
	@Override
	public String toString() {
		return "Evento [Id=" + Id + ", Nome=" + Nome + ", Data=" + Data + ", Valor=" + Valor + "]";
	}
	
	
	
}
