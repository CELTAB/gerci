package main.java.br.org.celtab.gerci.model;

/**
 * 
 * @author Emerson Luiz dos Santos <emersonsts@gmail.com>
 *
 */
public class PontoTransformar extends Ponto {

	public PontoTransformar(String id, Double norte, Double este, Double h,
			Double h2) {
		
		super(id, norte, este, h, h2, new SistemaCoordenadas());
	}

	public PontoTransformar() {
	}
}
