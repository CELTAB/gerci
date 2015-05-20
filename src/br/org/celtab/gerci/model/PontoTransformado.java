package br.org.celtab.gerci.model;

import java.util.ArrayList;

public class PontoTransformado extends Ponto {


	private PontoTransformar origem;
	private ArrayList<PontoControle> ptosControleVizinhos;
	private Complex alfa;
	private Complex beta;
	private Complex gamma;
	
	public PontoTransformado(String id, Double norte, Double este, Double h,
			Double h2, SistemaCoordenadas sistemaCoordenadas) {
		super(id, norte, este, h, h2, sistemaCoordenadas);
		// TODO Auto-generated constructor stub
	}
	
	public PontoTransformado(){
		
	}
	
	public PontoTransformar getOrigem() {
		return origem;
	}
	public void setOrigem(PontoTransformar origem) {
		this.origem = origem;
	}
	public ArrayList<PontoControle> getPtosControleVizinhos() {
		return ptosControleVizinhos;
	}
	public void setPtosControleVizinhos(ArrayList<PontoControle> ptosControleVizinhos) {
		this.ptosControleVizinhos = ptosControleVizinhos;
	}
	public Complex getAlfa() {
		return alfa;
	}
	public void setAlfa(Complex alfa) {
		this.alfa = alfa;
	}
	public Complex getBeta() {
		return beta;
	}
	public void setBeta(Complex beta) {
		this.beta = beta;
	}
	public Complex getGamma() {
		return gamma;
	}
	public void setGamma(Complex gamma) {
		this.gamma = gamma;
	}
	
	
}
