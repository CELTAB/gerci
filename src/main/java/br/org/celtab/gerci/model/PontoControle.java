package main.java.br.org.celtab.gerci.model;

/**
 * 
 * @author Emerson Luiz dos Santos <emersonsts@gmail.com>
 *
 */
public class PontoControle extends Ponto {

	public PontoControle(String id, Double norte, Double este, Double h,
			Double h2, SistemaCoordenadas sistemaCoordenadas) {
		super(id, norte, este, h, h2, sistemaCoordenadas);
	}

	public PontoControle() {
	}
	
}
