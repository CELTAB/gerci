var app = angular.module('mainModule',['ui.bootstrap'])
	.config( [
	    '$compileProvider',
	    function( $compileProvider ){   
	        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto):|data:application\/octet-stream/);
	    }
]);

app.controller('mainController', function($scope, $http, $modal){
	
	$scope.pontosControleS1 = [];
	$scope.pontosControleS2 = [];
	$scope.pontosTransformar = [];
	$scope.pontosTransformados = [];
	$scope.sistemaCoordenadasS1 = new Object;
	$scope.sistemaCoordenadasS2 = new Object;
	$scope.textData = "";
	$scope.textFile = "PontosTransformados.txt";
	$scope.tblPontosControle = false;
	$scope.tblPontosTransformar = false;
	$scope.tblControleTransforma = false;
		
	$scope.upload_pontosControle = function (element) {
		 var reader = new FileReader();
		 
		  reader.onload = function(e) {
		      $scope.$apply(function() {
		          processaPontosControle($scope, $modal, reader.result);
		      });
		  };
		           
         var pontosControle = element.files[0];
         reader.readAsText(pontosControle);
	}

	$scope.upload_pontosTransformar = function (element) {
		 var reader = new FileReader();
		 
		  reader.onload = function(e) {
		      $scope.$apply(function() {
		          processaPontosTransformar($scope, $http, $modal, reader.result);
		      });
		  };
		           
         var pontosTransformar = element.files[0];
         reader.readAsText(pontosTransformar);
	}
	
	$scope.sobre = function(){
		openModal('sobre','zeeeeeeeeeeeeeee',$modal);
	}
	
	$scope.ajuda = function(){
		openModal('ajuda','ze2',$modal);
	}
	
});

function processaPontosControle($scope, $modal, result){
	
	var sistemaCoordenadasS1 = $scope.sistemaCoordenadasS1 = new Object;
	var sistemaCoordenadasS2 = $scope.sistemaCoordenadasS2 = new Object;
	var pontosControleS1 = $scope.pontosControleS1 = [];
	var pontosControleS2 = $scope.pontosControleS2 = [];
	$scope.pontosTransformar = [];
	$scope.pontosTransformados = [];
	
	try{
	
		var string_pontosControle = result.split(/[\t]*[\s]*[\r]*\n/);
		
		var linha0 = string_pontosControle[0].split(/[\t]+/);
		
		if(linha0.length != 5 || linha0[0] != "ID"){
			throw new Error("Verifique se o padrão do cabeçalho está no formato correto.");
		}
		
		sistemaCoordenadasS1.codigo = 0;
		sistemaCoordenadasS1.descricao = linha0[1].split("-")[1];
		sistemaCoordenadasS1.semiEixoMaior = 0;
		sistemaCoordenadasS1.semiEixoMenor = 0;	
		
		sistemaCoordenadasS2.codigo = 0;
		sistemaCoordenadasS2.descricao = linha0[4].split("-")[1];
		sistemaCoordenadasS2.semiEixoMaior = 0;
		sistemaCoordenadasS2.semiEixoMenor = 0;
	
		for (i = 1; i < string_pontosControle.length; i++) {
	
		   var linha = string_pontosControle[i].split(/[\t]+/);
	
		   if (linha[1] !== undefined){
			   var pCS1 = new Object;
			   pCS1.id = linha[0];
			   pCS1.sistemaCoordenadas = sistemaCoordenadasS1;
		
			   var pCS2 = new Object;
			   pCS2.id = linha[0];
			   pCS2.sistemaCoordenadas = sistemaCoordenadasS2;
		
			   if (linha.length < 9){
					pCS1.norte = linha[1].replace(",",".");
					pCS1.este = linha[2].replace(",",".");
					pCS1.h = 0;
					pCS1.h2 = 0;
											
					pCS2.norte = linha[3].replace(",",".");
					pCS2.este = linha[4].replace(",",".");
					pCS2.h = 0;
					pCS2.h2 = 0;
			   }else{
					pCS1.norte = linha[1].replace(",",".");
					pCS1.este = linha[2].replace(",",".");
					pCS1.h = linha[3];
					pCS1.h2 = linha[4];
											
					pCS2.norte = linha[5].replace(",",".");
					pCS2.este = linha[6].replace(",",".");
					pCS2.h = linha[7];
					pCS2.h2 = linha[8];
			   }
			   
			   if(pCS1.id != "" || pCS2.id != ""){
			    	pontosControleS1.push(pCS1);
					pontosControleS2.push(pCS2);
			    }
		   }
		}
		
		verificaPontosControle(pontosControleS1);
		verificaPontosControle(pontosControleS2);
		$scope.tblPontosControle = true;
        $scope.tblControleTransforma = true;
        
	}catch (e) {
		pontosControleS1 = [];
		pontosControleS2 = [];
		clearFileInputField('uploadFilePontosControle');
		openModal('error', e.message, $modal); 
	}
}

function processaPontosTransformar($scope, $http, $modal, result){
	
	var pontosTransformar = $scope.pontosTransformar = [];
	
	try{
	
		var string_transformar = result.split(/[\t]*[\s]*[\r]*\n/);
		
		var linha0 = string_transformar[0].split(/[\t]+/);
		
		if(linha0.length != 3 || linha0[0] != "ID"){
			throw new Error("Verifique se o padrão do cabeçalho está no formato correto.");
		}
		
		for (i = 1; i < string_transformar.length; i++) {
	
		   var linha = string_transformar[i].split(/[\t]+/);

		   if (linha[1] !== undefined){
			   
			   var pTS1 = new Object;
			   pTS1.id = linha[0];
			   pTS1.sistemaCoordenadas = $scope.sistemaCoordenadasS1;
			   
			   if (linha.length < 5){
					pTS1.norte = linha[1].replace(",",".");
					pTS1.este = linha[2].replace(",",".");
					pTS1.h = 0;
					pTS1.h2 = 0;
			   }else{
					pTS1.norte = linha[1].replace(",",".");
					pTS1.este = linha[2].replace(",",".");
					pTS1.h = linha[3];
					pTS1.h2 = linha[4];
			   }
		
			   if(pTS1.id != ""){
					pontosTransformar.push(pTS1);
			   }
		   }
		}
		
        transforma($scope, $http, $modal); 
		$scope.tblPontosTransformar = true;
    	$scope.tblControleTransforma = false;
    	  
	}catch (e) {
		pontosTransformar = [];
		clearFileInputField('uploadFilePontosTransformar');
		openModal('error', e.message, $modal); 
	}	
}

function transforma($scope, $http, $modal){
	var transformacao = new Object;

	transformacao.pontosControleS1 = $scope.pontosControleS1;
	transformacao.pontosControleS2 = $scope.pontosControleS2;
	transformacao.pontosTransformar = $scope.pontosTransformar;
	transformacao.sistemaCoordenadasDe = $scope.sistemaCoordenadasS1;
	transformacao.sistemaCoordenadasPara = $scope.sistemaCoordenadasS2;
		
	$http.post('service/transformacao/transforma', angular.toJson(transformacao)).
	  success(function(data, status, headers, config) {
		  $scope.pontosTransformados = data;
		  verificaPontosTransformados(data);
	      exportarTXT($scope);
	  }).
	  error(function(data, status, headers, config) {
		  openModal('error', 'Problemas ao acessar o serviço de transformação.', $modal);
	  });
}

function verificaPontosControle(pontos){
	pontos.forEach(function(ponto, index){
		for (i=index+1; i < pontos.length; i++){
			if ((ponto.id == pontos[i].id) || (ponto.norte == pontos[i].norte && ponto.este == pontos[i].este)){
				throw new Error("O Ponto com ID ("+pontos[i].id+") Linha ("+(i+2)+") está duplicado.");
			}
		}
	});	
}

function verificaPontosTransformados(pontos){
	
	pontos.forEach(function(ponto, index){
		for (i=index+1; i < pontos.length; i++){
			if ((ponto.origem.id == pontos[i].origem.id) || (ponto.origem.norte == pontos[i].origem.norte && ponto.origem.este == pontos[i].origem.este)){
				ponto.origem.duplicate = true;
				pontos[i].origem.duplicate = true;
			}
		}
	});	
	
}

function openModal(type, message, $modal){
	var template;
	var size;
	switch (type) {
	case "error":
		template = "error.html";
		break;
	case "ajuda":
		template = "ajuda.html";	
		size = 'lg';
		break;
	case "sobre":
		template = "sobre.html";
		size = 'lg';
		break;
	default:
		break;
	}
	
	$modal.open({
		animation: true, 
		templateUrl: template,
		size: size,
		controller: function($scope, $modalInstance){
			$scope.message = message;
			$scope.close = function() {
                $modalInstance.dismiss('cancel');
            };
		}
	});
}

function clearFileInputField(tagId) {
    document.getElementById(tagId).innerHTML = document.getElementById(tagId).innerHTML;
}

function exportarTXT($scope){

	var pontosTransformados = $scope.pontosTransformados;
	var str = 'ID\tN\tE\r\n';

    for (var i = 0; i < pontosTransformados.length; i++) {
    	var dup = (pontosTransformados[i].origem.duplicate == true) ? "*":"";
        var line = dup+pontosTransformados[i].id+"\t"+pontosTransformados[i].norte.toFixed(3).replace(".",",")+"\t"+pontosTransformados[i].este.toFixed(3).replace(".",",");
        str += line + '\r\n';
    }

    var date = new Date();
    var dateString = date.getDate()+"-"+(date.getMonth() + 1)+"-"+date.getFullYear()+"-"+date.getHours()+"-"+date.getMinutes();

    $scope.textData = "data:application/octet-stream;base64," + btoa(str);
    $scope.textFile = "PontosTransformados-"+dateString+".txt";
   
}