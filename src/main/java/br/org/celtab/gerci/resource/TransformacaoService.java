package main.java.br.org.celtab.gerci.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.br.org.celtab.gerci.controllers.ColTransformacaoCoordenadas;
import main.java.br.org.celtab.gerci.model.Transformacao;

/**
 * 
 * @author Mohamad Abu Ali <arabian@brasnet.org>
 * @author Thiago R. M. Bitencourt <thiago.mbitencourt@gmail.com>
 * 
 * Classe que responde pelo serviço de transformação, através do caminho /service/tranformacao. 
 * Este serviço é chamado através de um método POST do protocolo http e recebe como parâmetro todas as informaçãoes necessárias para a transformação como: 
 * - Sistema de origem e de destino;
 * - Pontos de controle do sistema de origem;
 * - Pontos de controle do sistema de destino;
 * - Pontos a serem transformados.
 * 
 * As informações recebidas por parâmetro chegam em uma mensagem no formato JSON, e são mapeados para um objeto da classe modelo Transformacao.
 * Se o processamento e a transformação dos pontos ocorrer corretamente, irá retornar uma mensagem com estatus de sucesso (200) e um JSON com os novos valores para os pontos transformados.
 */
@Path("/transformacao")
public class TransformacaoService {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) // Mapeamento de um JSON para um objeto da classe Transformacao
	public Response getTransformacao(Transformacao transformacao) {
		
		// Chama o método de transformação, passando como parâmetro as informações recebidas
		return Response.status(200).entity(ColTransformacaoCoordenadas.serviceTransformacao(transformacao)).build();
	}
}
