package model;

public class Carro extends Model {
	private String marca;
	private String modelo;
	private int ano;

	public Carro(int id, String marca, String modelo, int ano) {
		super(id);
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public boolean equals(Object obj) {
		Carro other = (Carro) obj;
		return super.equals(other) && this.marca.equals(other.marca) && this.modelo.equals(other.modelo);
	}

	@Override
	public int hashCode() {
		return super.hashCode() + marca.hashCode() + modelo.hashCode();
	}
}
