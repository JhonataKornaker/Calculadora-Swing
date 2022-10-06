package br.com.jhonata.calc.visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.jhonata.calc.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener {

	private final Color COR_CINZA_ESCURO = new Color(68,68,68);
	private final Color	COR_CINZA_CLARO = new Color(99,99,99);
	private final Color COR_LARANJA = new Color(242,163,60);
	
	public Teclado() {
	
/* No GridBagLayout cada coluna ou linha da matriz pode ter um tamanho e comportamento 
 * diferente, assim como cada célula também possuirá as suas variações.*/
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);

/*Para ocupar todo o container tendo o mesmo peso, ou seja espaço*/
		c.weightx = 1;
		c.weighty = 1;
		
/*Preenche os espaços em branco dos botoes*/	
		c.fill = GridBagConstraints.BOTH;
		
		//Linha 1
		c.gridwidth = 3;
		adicionarBotao("AC", COR_CINZA_ESCURO, c, 0, 0 );
		c.gridwidth = 1;
		adicionarBotao("/", COR_LARANJA, c, 3, 0 );
		
		//Linha 2
		adicionarBotao("7", COR_CINZA_ESCURO, c, 0, 1 );
		adicionarBotao("8", COR_CINZA_ESCURO, c, 1, 1 );
		adicionarBotao("9", COR_CINZA_ESCURO, c, 2, 1 );
		adicionarBotao("*", COR_LARANJA, c, 3, 1 );
		
		//Linha 3
		adicionarBotao("4", COR_CINZA_ESCURO, c, 0, 2 );
		adicionarBotao("5", COR_CINZA_ESCURO, c, 1, 2 );
		adicionarBotao("6", COR_CINZA_ESCURO, c, 2, 2 );
		adicionarBotao("-", COR_LARANJA, c, 3, 2 );
				
		//Linha 4
		adicionarBotao("1", COR_CINZA_ESCURO, c, 0, 3 );
		adicionarBotao("2", COR_CINZA_ESCURO, c, 1, 3 );
		adicionarBotao("3", COR_CINZA_ESCURO, c, 2, 3 );
		adicionarBotao("+", COR_LARANJA, c, 3, 3 );
				
		//Linha 5
/*Aumenta a largura em 2 botoes, mas tem que volta depois se nao tudo fica maior*/
		c.gridwidth = 2;
		adicionarBotao("0", COR_CINZA_ESCURO, c, 0, 4 );
		c.gridwidth = 1;
		adicionarBotao(",", COR_CINZA_ESCURO, c, 2, 4 );
		adicionarBotao("=", COR_LARANJA, c, 3, 4 );
	}
	
/*Criando metodo para adicionar botao com Grid*/
	private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		add(botao, c);
	}

/*Esse metodo esta pegando as informações do botões pressionados e jogando na classe Memoria no metodo processarComando()*/	
@Override
public void actionPerformed(ActionEvent e) {
/*Precisa ser feito um cast JButton pois o metodo getSource retorna um Object*/
	if(e.getSource() instanceof JButton) {
	JButton botao = (JButton) e.getSource();
	Memoria.getInstancia().processarComando(botao.getText());
	}
}
}
