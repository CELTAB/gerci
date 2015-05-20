package br.org.celtab.gerci.model;

public class PontoTransformar extends Ponto {

	public PontoTransformar(String id, Double norte, Double este, Double h,
			Double h2) {
		
		super(id, norte, este, h, h2, new SistemaCoordenadas());
		// TODO Auto-generated constructor stub
	}

	public PontoTransformar() {
		// TODO Auto-generated constructor stub
	}
}
