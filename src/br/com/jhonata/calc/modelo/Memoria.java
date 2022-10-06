package br.com.jhonata.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum TipoComando {
		ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA;
	};
	
/*Construtores privados nao podem ser instanciado em outra classe*/
	
/*Com essa variavel instancia voce consegue ter acesso a classe pelo getters*/	
	private static final Memoria instancia = new Memoria();
	
	private String textoAtual = "";
	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String textoBuffer = "";
	
	
/*Essa lista ira armazenar todos os observadores que cadastrar aqui*/
	private List<MemoriaObservador> observadores = new ArrayList<>();
	
	private Memoria() {	
	}
	public static Memoria getInstancia() {
		return instancia;
	}
	public void adicionarObservador(MemoriaObservador o) {
		observadores.add(o);
	}
	public String getTextoAtual() {
/*Se o texto for igual a zero mostra o prorpio texto*/
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
/*Sempre que digitar um comando na calculadora sera pego pelo esse metodo
 * Esta pegando o valor digitado do botao que veio da classe Memoria, atualizando a variavel textoAtual
 * que esta jogando na lista do tipo MemoriaObservador(interface)*/
	public void processarComando (String valor) {
		
		TipoComando tipoComando = detectarTipoComando(valor);
//		System.out.println(tipoComando);
		
		if(tipoComando == null) {
			return;
		} else if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		} else if(tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
			textoAtual = substituir ? valor : textoAtual + valor;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
/*Vai salvar os numeros que foram digitados na tela antes de apertar uma operacao*/
			textoBuffer = textoAtual;
			ultimaOperacao = tipoComando;
		}
		
/*Vai pegar o valor do variavel textoAtual  e passa para os observadores*/
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
		
	}
	
private String obterResultadoOperacao() {
	if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
		return textoAtual;
	}
/*Vai converter a VIRGULA por PONTO para fazer as opercões matematicas*/	
	double numeroBuffer = Double.parseDouble(textoBuffer.replace(",","."));
	double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
	double resultado = 0;
	
	if(ultimaOperacao == TipoComando.SOMA) {
		resultado = numeroBuffer + numeroAtual;
	} else if(ultimaOperacao == TipoComando.SUB) {
		resultado = numeroBuffer - numeroAtual;
	} else if(ultimaOperacao == TipoComando.MULT) {
		resultado = numeroBuffer * numeroAtual;
	} else if(ultimaOperacao == TipoComando.DIV) {
		resultado = numeroBuffer / numeroAtual;
	}
/*Convertendo o resulta que é um numero para uma String*/
	
	String resultadoString = Double.toString(resultado).replace(".", ",");
	
/*REtirando a vigula de numeros inteiro EX: 10,0*/
	boolean inteiro = resultadoString.endsWith(",0");
	return inteiro ? resultadoString.replace(",0", "") : resultadoString;
	
}
/*Vai pegar o tipo de texto(valor) que foi digitado no teclado*/
	private TipoComando detectarTipoComando(String valor) {
		
/*Não ter varios zero no lado esquerdo*/
	if(textoAtual.isEmpty() && valor == "0") {
		return null;
	}
	
	try {
/*Tenta converter o que foi digitado na calculadora se pode ser um número*/		
		Integer.parseInt(valor);
		return TipoComando.NUMERO;
	} catch (NumberFormatException e) {
		//Quando não for um número...
		if ("AC".equals(valor)) {
			return TipoComando.ZERAR;
		} else if("/".equals(valor)) {
			return TipoComando.DIV;
		} else if("*".equals(valor)) {
			return TipoComando.MULT;
		} else if("-".equals(valor)) {
			return TipoComando.SUB;
		} else if("+".equals(valor)) {
			return TipoComando.SOMA;
		} else if("=".equals(valor)) {
			return TipoComando.IGUAL;
		} else if(",".equals(valor) 
				&& !textoAtual.contains(",")) { //Se no textoAtual conter uma virgula ele nao entra para o comando debaixo
			return TipoComando.VIRGULA;
		}
	}
	return null;
}
}
