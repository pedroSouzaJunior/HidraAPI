package command;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

//import javax.swing.JFrame;
//import javax.swing.JOptionPane;



//import javax.swing.JFrame;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;

/**
 * @author danielli
 *
 */
public class MainTeste {



	/**
	 *
	 */
	public static String localizacao;

	/**
	 * Repositorio a ser inicializado
	 *
	 */
	public static InitGit i;

	/**
	 * @param args
	 * @throws IOException
	 * @throws GitAPIException
	 * @throws NoFilepatternException
	 */

	@SuppressWarnings({ "nls", "unused" })
	public static void main(String[] args) throws IOException,
			NoFilepatternException, GitAPIException {

		/*
		 * JGit newRepo = new JGit();
		 *
		 *
		 *
		 * newRepo.init(); newRepo.testCreate(); newRepo.testAdd();
		 * newRepo.testCommit(); newRepo.testRemoveFile(); newRepo.testClone();
		 *
		 * newRepo.status();
		 */

		// Cria objeto:
		// new Interface();

		int opcao;
		do {
			opcao = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(""
					+ "1 - Init.\n" + "2 - Create new Repository.\n"
					+ "3 - Add file.\n" + "4 - Remove file.\n"
					+ "5 - Commit.\n" + "6 - Clone Repository.\n"
					+ "7 - Show Status.\n" + "8 - Sair."));
			switch (opcao) {
			case 1:
				JFrame frame = new JFrame("");
				ChoosePath panel = new ChoosePath();
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
				frame.getContentPane().add(panel, "Center"); //$NON-NLS-1$
				frame.setSize(panel.getPreferredSize());
				frame.setVisible(true);
				new InitGit(panel.getPathDirectory());

				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			default:
				JOptionPane
						.showMessageDialog(null,
								"A opcao invalida.\n Escolha uma opcao invalida para continuar.");

	           }
		} while (opcao != 8);
	}
}

