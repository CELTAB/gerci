package br.org.celtab.gerci.model;

public class SistemaCoordenadas {

	private int codigo;
	private String descricao;
	private Double semiEixoMaior;
	private Double semiEixoMenor;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getSemiEixoMaior() {
		return semiEixoMaior;
	}
	public void setSemiEixoMaior(Double semiEixoMaior) {
		this.semiEixoMaior = semiEixoMaior;
	}
	public Double getSemiEixoMenor() {
		return semiEixoMenor;
	}
	public void setSemiEixoMenor(Double semiEixoMenor) {
		this.semiEixoMenor = semiEixoMenor;
	}
	@Override
	public String toString() {
		return this.getDescricao();
	}
	
}
