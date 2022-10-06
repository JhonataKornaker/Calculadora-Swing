package br.com.jhonata.calc.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame {

	public Calculadora() {
		
		organizarLayout();
		
		setVisible(true); //Tela aparecer
		setDefaultCloseOperation(EXIT_ON_CLOSE);//finalizar o processo assim que fecha a janela
		setSize(232, 322); //tamanho da janela;
		setLocationRelativeTo(null); //abrir a janela no centro
	}
	
	private void organizarLayout() {
//Borda Layout que define a tela em norte, sul, leste e oeste
//Metodo add foi recebido por herança do JFrame 
//Ele que adiciona na tela os componentes que foram criados em outras classes
		setLayout(new BorderLayout());
		
		Display display = new Display();
//DEfinindo o tamanho do container do display:
		display.setPreferredSize(new Dimension(233,60));
//Adicionar o componente no norte baseado no layuot que estamos usando
		add(display, BorderLayout.NORTH);
		
		Teclado teclado = new Teclado();
		add(teclado, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new Calculadora();
	}
}
