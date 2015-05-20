package br.org.celtab.gerci.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.org.celtab.gerci.model.PontoControle;
import br.org.celtab.gerci.model.PontoTransformar;
import br.org.celtab.gerci.model.SistemaCoordenadas;
import br.org.celtab.gerci.model.Transformacao;

@Path("/test")
public class TestService {

	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(){
		
		Transformacao transformacao = new Transformacao();
		
		ArrayList<PontoControle> pontosControleS1 = new ArrayList<PontoControle>();
		ArrayList<PontoControle> pontosControleS2 = new ArrayList<PontoControle>();
		ArrayList<PontoTransformar> pontosTransformar = new ArrayList<PontoTransformar>();
		
		// Sistema de Coordenadas
		SistemaCoordenadas sistemaCoordenadasS1 = new SistemaCoordenadas();
		sistemaCoordenadasS1.setCodigo(0);
		sistemaCoordenadasS1.setDescricao("S1");
		sistemaCoordenadasS1.setSemiEixoMaior(0D);
		sistemaCoordenadasS1.setSemiEixoMenor(0D);
		
		SistemaCoordenadas sistemaCoordenadasS2 = new SistemaCoordenadas();
		sistemaCoordenadasS2.setCodigo(0);
		sistemaCoordenadasS2.setDescricao("S2");
		sistemaCoordenadasS2.setSemiEixoMaior(0D);
		sistemaCoordenadasS2.setSemiEixoMenor(0D);


		// Pontos de Cotrole S1
		pontosControleS1.add(new PontoControle("A", 7500100.000, 450100.000, 0D, 0D, sistemaCoordenadasS1));
		pontosControleS1.add(new PontoControle("B",	7500100.000, 450300.000, 0D, 0D, sistemaCoordenadasS1));
		pontosControleS1.add(new PontoControle("C",	7500100.000, 450500.000, 0D, 0D, sistemaCoordenadasS1));
//		pontosControleS1.add(new PontoControle("D",	7500300.000, 450100.000, 0D, 0D, sistemaCoordenadasS1));
//		pontosControleS1.add(new PontoControle("E",	7500300.000, 450300.000, 0D, 0D, sistemaCoordenadasS1));
//		pontosControleS1.add(new PontoControle("F",	7500300.000, 450500.000, 0D, 0D, sistemaCoordenadasS1));
//		pontosControleS1.add(new PontoControle("G",	7500500.000, 450100.000, 0D, 0D, sistemaCoordenadasS1));
//		pontosControleS1.add(new PontoControle("H",	7500500.000, 450300.000, 0D, 0D, sistemaCoordenadasS1));
//		pontosControleS1.add(new PontoControle("I",	7500500.000, 450500.000, 0D, 0D, sistemaCoordenadasS1));


		// Pontos de Cotrole S2	
		pontosControleS2.add(new PontoControle("A", 3000050.00, 100050.00, 0D, 0D, sistemaCoordenadasS2));
		pontosControleS2.add(new PontoControle("B", 3000050.00, 100030.00, 0D, 0D, sistemaCoordenadasS2));
		pontosControleS2.add(new PontoControle("C", 3000050.00, 100010.00, 0D, 0D, sistemaCoordenadasS2));
//		pontosControleS2.add(new PontoControle("D", 3000030.00, 100050.00, 0D, 0D, sistemaCoordenadasS2));
//		pontosControleS2.add(new PontoControle("E", 3000030.00, 100030.00, 0D, 0D, sistemaCoordenadasS2));
//		pontosControleS2.add(new PontoControle("F", 3000030.00, 100010.00, 0D, 0D, sistemaCoordenadasS2));
//		pontosControleS2.add(new PontoControle("G", 3000010.00, 100050.00, 0D, 0D, sistemaCoordenadasS2));
//		pontosControleS2.add(new PontoControle("H", 3000010.00, 100030.00, 0D, 0D, sistemaCoordenadasS2));
//		pontosControleS2.add(new PontoControle("I", 3000010.00, 100010.00, 0D, 0D, sistemaCoordenadasS2));
		
		
		// Pontos transformar
		pontosTransformar.add(new PontoTransformar("1", 7500200.000, 450200.000, 0D, 0D));
//		pontosTransformar.add(new PontoTransformar("2", 7500200.000, 450400.000, 0D, 0D));
//		pontosTransformar.add(new PontoTransformar("3", 7500400.000, 450200.000, 0D, 0D));
//		pontosTransformar.add(new PontoTransformar("4", 7500400.000, 450400.000, 0D, 0D));
				
		transformacao.setPontosControleS1(pontosControleS1);
		transformacao.setPontosControleS2(pontosControleS2);
		transformacao.setPontosTransformar(pontosTransformar);
		transformacao.setSistemaCoordenadasDe(sistemaCoordenadasS1);
		transformacao.setSistemaCoordenadasPara(sistemaCoordenadasS2);
		
		return Response.status(200).entity(transformacao).build();
	}
}
