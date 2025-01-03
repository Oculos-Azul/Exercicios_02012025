package model;

public class Bicicleta extends Model {
	private String tipo;
	private String material;
	private boolean possuiMarcha;

	public Bicicleta(int id, String tipo, String material, boolean possuiMarcha) {
		super(id);
		this.tipo = tipo;
		this.material = material;
		this.possuiMarcha = possuiMarcha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public boolean isPossuiMarcha() {
		return possuiMarcha;
	}

	public void setPossuiMarcha(boolean possuiMarcha) {
		this.possuiMarcha = possuiMarcha;
	}

	@Override
	public boolean equals(Object obj) {
		Bicicleta other = (Bicicleta) obj;
		return super.equals(other) && this.tipo.equals(other.tipo) && this.material.equals(other.material);
	}

	@Override
	public int hashCode() {
		return super.hashCode() + tipo.hashCode() + material.hashCode();
	}
}
