package main.java.br.org.celtab.gerci.model;

import java.util.ArrayList;

/**
 * 
 * @author Emerson Luiz dos Santos <emersonsts@gmail.com>
 *
 */
public class Transformacao {

	private ArrayList<PontoControle> pontosControleS1 = new ArrayList<PontoControle>();
	private ArrayList<PontoControle> pontosControleS2 = new ArrayList<PontoControle>();
	private ArrayList<PontoTransformar> pontosTransformar = new ArrayList<PontoTransformar>();
	private SistemaCoordenadas sistemaCoordenadasDe = new SistemaCoordenadas();
	private SistemaCoordenadas sistemaCoordenadasPara = new SistemaCoordenadas();
	
	public ArrayList<PontoControle> getPontosControleS1() {
		return pontosControleS1;
	}
	
	public void setPontosControleS1(ArrayList<PontoControle> pontosControleS1) {
		this.pontosControleS1 = pontosControleS1;
	}
	
	public ArrayList<PontoControle> getPontosControleS2() {
		return pontosControleS2;
	}
	
	public void setPontosControleS2(ArrayList<PontoControle> pontosControleS2) {
		this.pontosControleS2 = pontosControleS2;
	}
	
	public ArrayList<PontoTransformar> getPontosTransformar() {
		return pontosTransformar;
	}
	
	public void setPontosTransformar(ArrayList<PontoTransformar> pontosTransformar) {
		this.pontosTransformar = pontosTransformar;
	}

	public SistemaCoordenadas getSistemaCoordenadasDe() {
		return sistemaCoordenadasDe;
	}

	public void setSistemaCoordenadasDe(SistemaCoordenadas sistemaCoordenadasDe) {
		this.sistemaCoordenadasDe = sistemaCoordenadasDe;
	}

	public SistemaCoordenadas getSistemaCoordenadasPara() {
		return sistemaCoordenadasPara;
	}

	public void setSistemaCoordenadasPara(SistemaCoordenadas sistemaCoordenadasPara) {
		this.sistemaCoordenadasPara = sistemaCoordenadasPara;
	}
	
}
