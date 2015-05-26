package main.java.br.org.celtab.gerci.model;

import java.util.ArrayList;

/**
 * 
 * @author Mohamad Abu Ali <arabian@brasnet.org>
 * @author Thiago R. M. Bitencourt <thiago.mbitencourt@gmail.com>
 * Classe que representa os pontos a serem transformados, assim como os pontos de controle e as informações do sistema de coordenadas e origem e de destino.
 */
public class Transformacao {

	// Pontos de controle para o sistema de coordenadas de orgime
	private ArrayList<PontoControle> pontosControleS1 = new ArrayList<PontoControle>();
	// Pontos de controle para o sistema de coordenadas de destino
	private ArrayList<PontoControle> pontosControleS2 = new ArrayList<PontoControle>();
	// Pontos a serem transdormados de origem para destino (S1 --> S2)
	private ArrayList<PontoTransformar> pontosTransformar = new ArrayList<PontoTransformar>();
	// Sistema de coordenadas de orgiem
	private SistemaCoordenadas sistemaCoordenadasDe = new SistemaCoordenadas();
	// Sistema de coordenadas de destino
	private SistemaCoordenadas sistemaCoordenadasPara = new SistemaCoordenadas();
	
	// Retorna um array com os pontos de controle para o sistema de coordenadas de origem
	public ArrayList<PontoControle> getPontosControleS1() {
		return pontosControleS1;
	}
	
	// Define os pontos de controle para o sistema de origem.
	public void setPontosControleS1(ArrayList<PontoControle> pontosControleS1) {
		this.pontosControleS1 = pontosControleS1;
	}
	
	// Retorna um array com os pontos de controle para o sistema de coordenadas de destino
	public ArrayList<PontoControle> getPontosControleS2() {
		return pontosControleS2;
	}
	
	// Define os pontos de controle para o sistema de destino.
	public void setPontosControleS2(ArrayList<PontoControle> pontosControleS2) {
		this.pontosControleS2 = pontosControleS2;
	}
	
	// Retorna um array com os pontos a serem transformados
	public ArrayList<PontoTransformar> getPontosTransformar() {
		return pontosTransformar;
	}
	
	// Define os pontos a serem transformados
	public void setPontosTransformar(ArrayList<PontoTransformar> pontosTransformar) {
		this.pontosTransformar = pontosTransformar;
	}

	// Retorna o sistema de coordenadas de origem
	public SistemaCoordenadas getSistemaCoordenadasDe() {
		return sistemaCoordenadasDe;
	}

	// Define o sistema de coordenadas de origem
	public void setSistemaCoordenadasDe(SistemaCoordenadas sistemaCoordenadasDe) {
		this.sistemaCoordenadasDe = sistemaCoordenadasDe;
	}
	
	// Retorna o sistema de coordenadas de destino
	public SistemaCoordenadas getSistemaCoordenadasPara() {
		return sistemaCoordenadasPara;
	}

	//Define o sistema de coordenadas de destino
	public void setSistemaCoordenadasPara(SistemaCoordenadas sistemaCoordenadasPara) {
		this.sistemaCoordenadasPara = sistemaCoordenadasPara;
	}
	
}
