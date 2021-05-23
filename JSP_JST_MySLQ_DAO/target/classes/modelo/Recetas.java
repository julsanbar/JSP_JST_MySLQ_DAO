package modelo;

public class Recetas {

	private String titulo;
	private String harina;
	private Double cantidadHarina;
	private String liquidos;
	private Double cantidadLevadura;
	private Double cantidadAzucar;
	private Double cantidadSal;
	private String preparacion;
	
	public Recetas() {
		super();
	}

	public Recetas(String titulo, String harina, Double cantidadHarina, String liquidos, Double cantidadLevadura,
			Double cantidadAzucar, Double cantidadSal, String preparacion) {
		super();
		this.titulo = titulo;
		this.harina = harina;
		this.cantidadHarina = cantidadHarina;
		this.liquidos = liquidos;
		this.cantidadLevadura = cantidadLevadura;
		this.cantidadAzucar = cantidadAzucar;
		this.cantidadSal = cantidadSal;
		this.preparacion = preparacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getHarina() {
		return harina;
	}

	public void setHarina(String harina) {
		this.harina = harina;
	}

	public Double getCantidadHarina() {
		return cantidadHarina;
	}

	public void setCantidadHarina(Double cantidadHarina) {
		this.cantidadHarina = cantidadHarina;
	}

	public String getLiquidos() {
		return liquidos;
	}

	public void setLiquidos(String liquidos) {
		this.liquidos = liquidos;
	}

	public Double getCantidadLevadura() {
		return cantidadLevadura;
	}

	public void setCantidadLevadura(Double cantidadLevadura) {
		this.cantidadLevadura = cantidadLevadura;
	}

	public Double getCantidadAzucar() {
		return cantidadAzucar;
	}

	public void setCantidadAzucar(Double cantidadAzucar) {
		this.cantidadAzucar = cantidadAzucar;
	}

	public Double getCantidadSal() {
		return cantidadSal;
	}

	public void setCantidadSal(Double cantidadSal) {
		this.cantidadSal = cantidadSal;
	}

	public String getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	@Override
	public String toString() {
		return "Recetas [titulo=" + titulo + ", harina=" + harina + ", cantidadHarina=" + cantidadHarina + ", liquidos="
				+ liquidos + ", cantidadLevadura=" + cantidadLevadura + ", cantidadAzucar=" + cantidadAzucar
				+ ", cantidadSal=" + cantidadSal + ", preparacion=" + preparacion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Recetas other = (Recetas) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	
}
