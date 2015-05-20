package br.org.celtab.gerci.controllers;

import java.util.ArrayList;

import br.org.celtab.gerci.model.Complex;
import br.org.celtab.gerci.model.PontoControle;
import br.org.celtab.gerci.model.PontoTransformado;
import br.org.celtab.gerci.model.PontoTransformar;
import br.org.celtab.gerci.model.Transformacao;

public class ColTransformacaoCoordenadas {
	
	public static ArrayList<PontoTransformado> serviceTransformacao(Transformacao transformacao){
	
		Complex centroGravidadeS1 = ColTransformacaoCoordenadas.calculaCentroGravidade(transformacao.getPontosControleS1());
		Complex centroGravidadeS2 = ColTransformacaoCoordenadas.calculaCentroGravidade(transformacao.getPontosControleS2());
				
		ArrayList<PontoTransformado> pontosTransformados = new ArrayList<PontoTransformado>();

		for (PontoTransformar pt : transformacao.getPontosTransformar()) {

			PontoTransformado pto = new PontoTransformado();
			pto.setId(pt.getId());
			pto.setOrigem(pt);
			pto.setSistemaCoordenadas(transformacao.getSistemaCoordenadasPara());
			pto.setPtosControleVizinhos(ColTransformacaoCoordenadas.busca3VizinhosProximos(pt, transformacao.getPontosControleS1(), transformacao.getPontosControleS2()));

			pto = ColTransformacaoCoordenadas.calculaTransformacaoPonto(pto, centroGravidadeS1, centroGravidadeS2);

			pontosTransformados.add(pto);
			
		}
		
		return pontosTransformados;
		
	}

	
	public static PontoTransformado calculaTransformacaoPonto(PontoTransformado pto, Complex centroGravidadeS1, Complex centroGravidadeS2) {
		
		Complex ptOrigem   = new Complex(pto.getOrigem().getEste(), pto.getOrigem().getNorte());
		
		Complex alfa   = ColTransformacaoCoordenadas.calculaAlfa(pto, centroGravidadeS1, centroGravidadeS2);
        Complex beta   = ColTransformacaoCoordenadas.calculaBeta(pto, centroGravidadeS1, centroGravidadeS2);
        Complex gamma  = ColTransformacaoCoordenadas.calculaGamma(pto, centroGravidadeS1, centroGravidadeS2);
        
		Complex res = new Complex();
		Complex tmpOrig = Complex.subtracao(ptOrigem,centroGravidadeS1);
		
		double r = 0.0D;
		if (gamma.getImaginary() == 0) {
			r = gamma.getReal();
			res = Complex.divisao((Complex.soma(Complex.multiplicacao(alfa,tmpOrig),beta)),(r + 1D));
		} else {
			res = Complex.divisao(Complex.soma(Complex.multiplicacao(alfa,tmpOrig),beta),Complex.soma(Complex.multiplicacao(gamma,tmpOrig),new Complex(1,0)));
		}
			
		Complex resultado = Complex.soma(res,centroGravidadeS2);
		
		pto.setNorte(resultado.getImaginary());
		pto.setEste(resultado.getReal());
		pto.setAlfa(alfa);
		pto.setBeta(beta);
		pto.setGamma(gamma);
		
		return pto;
		
	}

	public static Complex calculaCentroGravidade(ArrayList<PontoControle> pontosControle) {

		double xi = 0.0;
		double yi = 0.0;
		for (int i = 0; i < pontosControle.size(); i++) {
			
			PontoControle pc = pontosControle.get(i);
					
			xi += pc.getEste();
			yi += pc.getNorte();
			
		}
		
		Complex tmp = new Complex(xi / new Double(pontosControle.size()), yi / new Double(pontosControle.size()));
		
		
		return tmp;
	}

	
	public static ArrayList<PontoControle> busca3VizinhosProximos(PontoTransformar ptoTransformar, ArrayList<PontoControle> pontosControleS1, 
																ArrayList<PontoControle> pontosControleS2) {

		int posVizinho1 = -1;
		int posVizinho2 = -1;
		int posVizinho3 = -1;
		
		double distanciaVizinho1 = Double.POSITIVE_INFINITY;
		double distanciaVizinho2 = Double.POSITIVE_INFINITY;
		double distanciaVizinho3 = Double.POSITIVE_INFINITY;
		
		double xPtoTransformar = ptoTransformar.getNorte();
		double yPtoTransformar = ptoTransformar.getEste();
		
		for(int index = 0; index < pontosControleS1.size(); index++) {
			
			PontoControle pc = pontosControleS1.get(index);
			
			double xPtoControle = pc.getNorte();
			double yPtoControle = pc.getEste();
			
			double dx = Math.abs(xPtoTransformar - xPtoControle);
			double dy = Math.abs(yPtoTransformar - yPtoControle);
			
			double distancia = Math.sqrt(dx*dx+dy*dy);
			
			
			
			if(distancia < distanciaVizinho1) {
				
				distanciaVizinho3 = distanciaVizinho2;
				posVizinho3 = posVizinho2;
				
				distanciaVizinho2 = distanciaVizinho1;
				posVizinho2 = posVizinho1;
				
				distanciaVizinho1 = distancia;
				posVizinho1 = index;
							
			} else if(distancia < distanciaVizinho2) {
				
				distanciaVizinho3 = distanciaVizinho2;
				posVizinho3 = posVizinho2;
				
				distanciaVizinho2 = distancia;
				posVizinho2 = index;
				
			} else if(distancia < distanciaVizinho3) {
				distanciaVizinho3 = distancia;
				posVizinho3 = index;
				
			}
			
		}
		
		ArrayList<PontoControle> vizinhos = new ArrayList<PontoControle>();
		vizinhos.add(pontosControleS1.get(posVizinho1));
		vizinhos.add(pontosControleS2.get(posVizinho1));
		vizinhos.add(pontosControleS1.get(posVizinho2));
		vizinhos.add(pontosControleS2.get(posVizinho2));
		vizinhos.add(pontosControleS1.get(posVizinho3));
		vizinhos.add(pontosControleS2.get(posVizinho3));
		
		return vizinhos;
		
	}
	
/*
     private static double calculaDistancia(Ponto a, Ponto b) {
    	  
    	 Complex zi = new Complex(a.getEste(),a.getNorte());
    	 Complex zj = new Complex(b.getEste(),b.getNorte());
    	 
         double dist = Math.round(
        		 			Math.sqrt(
        		 					((zi.getReal() - zj.getReal()) * (zi.getReal() - zj.getReal())) + 
        		 					((zi.getImaginary() - zj.getImaginary()) * (zi.getImaginary() - zj.getImaginary()))
        		 			)
        		 	   	);
         
         return dist; 
     }

     private static double calculaTriangulo(Ponto verticeA, Ponto verticeB, Ponto verticeOrigem) { 

         double a = calculaDistancia(verticeOrigem, verticeA);
         double b = calculaDistancia(verticeOrigem, verticeB);
         double c = calculaDistancia(verticeB, verticeA);
         double ang = Math.acos((((a * a) + (b * b)) - (c * c)) / (2D * (a * b)));
         
         ang = ang * 180D / Math.PI;
         
         return ang ;
     }
*/

	public static Complex calculaAlfa(PontoTransformado ptoTransformado, Complex centroGravidadeS1, Complex centroGravidadeS2) {
		
		ArrayList<PontoControle> vizinhos = ptoTransformado.getPtosControleVizinhos();
		
		Complex ptoControleS1Vizinho1 = new Complex(vizinhos.get(0).getEste(), vizinhos.get(0).getNorte());
		Complex ptoControleS2Vizinho1 = new Complex(vizinhos.get(1).getEste(), vizinhos.get(1).getNorte());
		
		Complex ptoControleS1Vizinho2 = new Complex(vizinhos.get(2).getEste(), vizinhos.get(2).getNorte());
		Complex ptoControleS2Vizinho2 = new Complex(vizinhos.get(3).getEste(), vizinhos.get(3).getNorte());
		
		Complex ptoControleS1Vizinho3 = new Complex(vizinhos.get(4).getEste(), vizinhos.get(4).getNorte());
		Complex ptoControleS2Vizinho3 = new Complex(vizinhos.get(5).getEste(), vizinhos.get(5).getNorte());
		
		Complex wA = Complex.subtracao(ptoControleS2Vizinho1, centroGravidadeS2);
		Complex wB = Complex.subtracao(ptoControleS2Vizinho2, centroGravidadeS2);
		Complex wC = Complex.subtracao(ptoControleS2Vizinho3, centroGravidadeS2);
		Complex zA = Complex.subtracao(ptoControleS1Vizinho1, centroGravidadeS1);
		Complex zB = Complex.subtracao(ptoControleS1Vizinho2, centroGravidadeS1);
		Complex zC = Complex.subtracao(ptoControleS1Vizinho3, centroGravidadeS1);
			
		Complex a =
			Complex.divisao( // /
					(
						Complex.subtracao( // -
							Complex.soma( // +
								Complex.subtracao( // -
									Complex.soma( // +
										Complex.subtracao( // -
										Complex.multiplicacao(Complex.multiplicacao(zB, wB),wC), // (zB * wB * wC)
											Complex.multiplicacao(Complex.multiplicacao(wB, zB),wA) // (wB * zB * wA)
										), Complex.multiplicacao(Complex.multiplicacao(wC, zC),wA) // (wC * zC * wA)
									), Complex.multiplicacao(Complex.multiplicacao(wC, zA),wA) // (wC * zA * wA)
								), Complex.multiplicacao(Complex.multiplicacao(wB, zA),wA) // (wB * zA * wA)
							), Complex.multiplicacao(Complex.multiplicacao(wB, zC),wC) // (wB * zC * wC)
						)
					)
					,
					Complex.soma( // +
						Complex.soma( // +
							Complex.subtracao( // -
								Complex.subtracao( // -
									Complex.soma( // +
										Complex.multiplicacao(Complex.multiplicacao(Complex.subtracao(zB), zC),wC), // (-zB * zC * wC)
										Complex.multiplicacao(Complex.multiplicacao(zB, zA),wA) // (zB * zA * wA)
									), Complex.multiplicacao(Complex.multiplicacao(zC, zA),wA) // (zC * zA * wA)
								), Complex.multiplicacao(Complex.multiplicacao(zA, zB),wB) // (zA * zB * wB)
							), Complex.multiplicacao(Complex.multiplicacao(zA, zC),wC) // (zA * zC * wC)
						), Complex.multiplicacao(Complex.multiplicacao(zC, zB),wB) // (zC * zB * wB)
					)		
				);
		
		return a;

	}

	public static Complex calculaBeta(PontoTransformado ptoTransformado, Complex centroGravidadeS1, Complex centroGravidadeS2) {
		
		ArrayList<PontoControle> vizinhos = ptoTransformado.getPtosControleVizinhos();
		
		Complex ptoControleS1Vizinho1 = new Complex(vizinhos.get(0).getEste(), vizinhos.get(0).getNorte());
		Complex ptoControleS2Vizinho1 = new Complex(vizinhos.get(1).getEste(), vizinhos.get(1).getNorte());
		
		Complex ptoControleS1Vizinho2 = new Complex(vizinhos.get(2).getEste(), vizinhos.get(2).getNorte());
		Complex ptoControleS2Vizinho2 = new Complex(vizinhos.get(3).getEste(), vizinhos.get(3).getNorte());
		
		Complex ptoControleS1Vizinho3 = new Complex(vizinhos.get(4).getEste(), vizinhos.get(4).getNorte());
		Complex ptoControleS2Vizinho3 = new Complex(vizinhos.get(5).getEste(), vizinhos.get(5).getNorte());
		
		Complex wA = Complex.subtracao(ptoControleS2Vizinho1, centroGravidadeS2);
		Complex wB = Complex.subtracao(ptoControleS2Vizinho2, centroGravidadeS2);
		Complex wC = Complex.subtracao(ptoControleS2Vizinho3, centroGravidadeS2);
		Complex zA = Complex.subtracao(ptoControleS1Vizinho1, centroGravidadeS1);
		Complex zB = Complex.subtracao(ptoControleS1Vizinho2, centroGravidadeS1);
		Complex zC = Complex.subtracao(ptoControleS1Vizinho3, centroGravidadeS1);
		
		Complex b  =
			
			Complex.divisao( // /
				(
					Complex.subtracao( // -
						Complex.soma( // +
							Complex.soma( // +
								Complex.subtracao( // -
									Complex.subtracao( // -
										Complex.multiplicacao(Complex.multiplicacao(Complex.multiplicacao(zB, wB),zC),wA),//(zB * wB * zC * wA)
										Complex.multiplicacao(Complex.multiplicacao(Complex.multiplicacao(wB, zC),zA),wA) //(wB * zC * zA * wA)
									),Complex.multiplicacao(Complex.multiplicacao(Complex.multiplicacao(zC, wC),zB),wA) // (zC * wC * zB * wA)
								),Complex.multiplicacao(Complex.multiplicacao(Complex.multiplicacao(zC, wC),zA),wB) // (zC * wC * zA * wB)
							),Complex.multiplicacao(Complex.multiplicacao(Complex.multiplicacao(wC, zB),zA),wA) // (wC * zB * zA * wA)
						),Complex.multiplicacao(Complex.multiplicacao(Complex.multiplicacao(zB, wB),zA),wC) // (zB * wB * zA * wC)
					)
				),
				(
					Complex.soma( // +
						Complex.soma( // +
							Complex.subtracao( // - 
								Complex.subtracao( // - 
									Complex.soma( // +
										Complex.multiplicacao(Complex.multiplicacao(Complex.subtracao(zB),zC),wC), // (-zB * zC * wC)
										Complex.multiplicacao(Complex.multiplicacao(zB,zA),wA) // (zB * zA * wA)
									),  Complex.multiplicacao(Complex.multiplicacao(zC,zA),wA) // (zC * zA * wA)
								), Complex.multiplicacao(Complex.multiplicacao(zA,zB),wB) // (zA * zB * wB)
							), Complex.multiplicacao(Complex.multiplicacao(zA,zC),wC) // (zA * zC * wC)
						),Complex.multiplicacao(Complex.multiplicacao(zC,zB),wB) // (zC * zB * wB)
					)		
				)
			);
		
		return b;

	}

	public static Complex calculaGamma(PontoTransformado ptoTransformado, Complex centroGravidadeS1, Complex centroGravidadeS2) {
		
		ArrayList<PontoControle> vizinhos = ptoTransformado.getPtosControleVizinhos();
		
		Complex ptoControleS1Vizinho1 = new Complex(vizinhos.get(0).getEste(), vizinhos.get(0).getNorte());
		Complex ptoControleS2Vizinho1 = new Complex(vizinhos.get(1).getEste(), vizinhos.get(1).getNorte());
		
		Complex ptoControleS1Vizinho2 = new Complex(vizinhos.get(2).getEste(), vizinhos.get(2).getNorte());
		Complex ptoControleS2Vizinho2 = new Complex(vizinhos.get(3).getEste(), vizinhos.get(3).getNorte());
		
		Complex ptoControleS1Vizinho3 = new Complex(vizinhos.get(4).getEste(), vizinhos.get(4).getNorte());
		Complex ptoControleS2Vizinho3 = new Complex(vizinhos.get(5).getEste(), vizinhos.get(5).getNorte());
		
		Complex wA = Complex.subtracao(ptoControleS2Vizinho1, centroGravidadeS2);
		Complex wB = Complex.subtracao(ptoControleS2Vizinho2, centroGravidadeS2);
		Complex wC = Complex.subtracao(ptoControleS2Vizinho3, centroGravidadeS2);
		Complex zA = Complex.subtracao(ptoControleS1Vizinho1, centroGravidadeS1);
		Complex zB = Complex.subtracao(ptoControleS1Vizinho2, centroGravidadeS1);
		Complex zC = Complex.subtracao(ptoControleS1Vizinho3, centroGravidadeS1);
	
		Complex g  = 
				
			Complex.divisao( // /
					(
						Complex.subtracao( // -
							Complex.subtracao( // -
								Complex.soma( // +
									Complex.soma( // +
										Complex.subtracao( // -
											Complex.multiplicacao(zB, wC), // zB * wC
											Complex.multiplicacao(zB, wA) // zB * wA
										),Complex.multiplicacao(zC, wA) // zC * wA
									),Complex.multiplicacao(zA, wB) // zA * wB
								),Complex.multiplicacao(zA, wC) // zA * wC
							),Complex.multiplicacao(zC, wB) // zC * wB
						)
					),
					(
						Complex.soma( // +
							Complex.soma( // +
								Complex.subtracao( // -
									Complex.subtracao( // -
										Complex.soma( // +
											Complex.multiplicacao(Complex.multiplicacao(Complex.subtracao(zB), zC),wC), // -zB * zC * wC
											Complex.multiplicacao(Complex.multiplicacao(zB, zA),wA) // zB * zA * wA
										),Complex.multiplicacao(Complex.multiplicacao(zC, zA),wA) // zC * zA * wA
									),Complex.multiplicacao(Complex.multiplicacao(zA, zB),wB) // zA * zB * wB
								),Complex.multiplicacao(Complex.multiplicacao(zA, zC),wC) // zA * zC * wC
							),Complex.multiplicacao(Complex.multiplicacao(zC, zB),wB) // zC * zB * wB
						)
					)
			);
		
		return g;

	}
	 
}
