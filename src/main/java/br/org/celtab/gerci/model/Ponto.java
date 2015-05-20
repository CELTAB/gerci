package main.java.br.org.celtab.gerci.model;

/**
 * 
 * @author Emerson Luiz dos Santos <emersonsts@gmail.com>
 *
 */
public class Ponto {

	private String id;
	private Double norte;
	private Double este;
	private Double h;
	private Double h2;
	private SistemaCoordenadas sistemaCoordenadas;
	
	public Ponto(String id, Double norte, Double este, Double h, Double h2,
		SistemaCoordenadas sistemaCoordenadas) {
		this.id = id;
		this.norte = norte;
		this.este = este;
		this.h = h;
		this.h2 = h2;
		this.sistemaCoordenadas = sistemaCoordenadas;
	}
	
	public Ponto(){
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getNorte() {
		return norte;
	}
	public void setNorte(Double norte) {
		this.norte = norte;
	}
	public Double getEste() {
		return este;
	}
	public void setEste(Double este) {
		this.este = este;
	}
	public Double getH() {
		return h;
	}
	public void setH(Double h) {
		this.h = h;
	}
	public Double getH2() {
		return h2;
	}
	public void setH2(Double h2) {
		this.h2 = h2;
	}
	public SistemaCoordenadas getSistemaCoordenadas() {
		return sistemaCoordenadas;
	}
	public void setSistemaCoordenadas(SistemaCoordenadas sistemaCoordenadas) {
		this.sistemaCoordenadas = sistemaCoordenadas;
	}

	@Override
	public String toString() {
		return "Ponto [id=" + id + ", norte=" + norte + ", este=" + este
				+ ", h=" + h + ", h2=" + h2 + ", sistemaCoordenadas="
				+ sistemaCoordenadas.toString() + "]";
	}
	
}
