package command;

import java.awt.Container;
import java.awt.Color;


import java.awt.FlowLayout;

//import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JLabel;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;


//import javax.swing.JTextField;
/**
 * @author USER
 *
 */
public class Interface extends JFrame {
	/**
		 *
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Componentes devem estar no contexto da instância, para que possam ser
	 * acessados em todos os métodos não-estáticos da classe
	 */
	// private JTextArea texto = new JTextArea();

	/**
	 *
	 */
	public Interface() {
		// Define o título da janela
		super("Bem-vindo ao programa sem nome"); //$NON-NLS-1$
		// this.montaJanela();
		Container c = getContentPane();
		c.setBackground(Color.white);
		c.setLayout(new FlowLayout());
		setSize(250, 220);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		/*
		 * add(new JMenuBar()); add(new JMenu("Comandos Gits")); //$NON-NLS-1$
		 * add(new JMenuItem("Init")); //$NON-NLS-1$ add(new
		 * JMenuItem("Create new Repository")); //$NON-NLS-1$ add(new
		 * JMenuItem("Add File")); //$NON-NLS-1$ add(new
		 * JMenuItem("Remove File")); //$NON-NLS-1$ add(new
		 * JMenuItem("Commit")); //$NON-NLS-1$ add(new JMenuItem("Clone"));
		 * //$NON-NLS-1$ add(new JMenuItem("Show Status")); //$NON-NLS-1$
		 * add(new JMenuItem("Sair")); //$NON-NLS-1$
		 */

		add(new JLabel("Escolha a opcao que deseja efetuar:")); //$NON-NLS-1$
		add(new JButton("Init")); //$NON-NLS-1$
		add(new JButton("Create new Repository")); //$NON-NLS-1$
		add(new JButton("Add file")); //$NON-NLS-1$
		add(new JButton("Remove file"));//$NON-NLS-1$
		add(new JButton("Commit")); //$NON-NLS-1$
		add(new JButton("Clone")); //$NON-NLS-1$
		add(new JButton("Show Status"));//$NON-NLS-1$
		add(new JButton("Sair")); //$NON-NLS-1$

	}



}

