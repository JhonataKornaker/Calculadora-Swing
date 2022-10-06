package br.com.jhonata.calc.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.jhonata.calc.modelo.Memoria;
import br.com.jhonata.calc.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador {

//Label é a caixa de texto quando for digitado o numero ira aparecer
	private final JLabel label;
	
	public Display() {
/*Nesse codigo abaixo diz q a classe Display esta interessado em saber sempre que
 * tem um valor modificado*/
		Memoria.getInstancia().adicionarObservador(this);
		
		setBackground(new Color(46,49,50));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());
		label.setForeground(Color.WHITE);
		
//new Font (nomeDaFonte, estiloFonte, tamanhoFonte)
		label.setFont(new Font("courier", Font.PLAIN, 30));
		
//Aliamento dos numeros para direita
//new FlowLayout(FlowLayout. alinhamento, horizontal , vertical)
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		
		add(label);
	}

/*Esse metodo esta recebendo o valor digitado que passou pela interface e colocando no label(na tela)*/
	@Override
	public void valorAlterado(String novoValor) {
/*Sempre que tiver uma mudança na classe memoria sempre notificado quem esta interessado que é a classe display*/
		label.setText(novoValor);
	}
	
}
