package command;


import java.io.File;
import java.io.IOException;

//import javax.swing.JFrame;
//import javax.swing.JOptionPane;



//import javax.swing.JFrame;






import javax.swing.JOptionPane;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
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

	// private static InitGit i2;

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

		File f = new File("/home/pedro/teste/arquivo1");
		f.createNewFile();

		int opcao;
		do {
			opcao = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(""
					+ "1 - Init.\n" + "2 - Create new Repository.\n"
					+ "3 - Add file.\n" + "4 - Remove file.\n"
					+ "5 - Commit.\n" + "6 - Clone Repository.\n"
					+ "7 - Show Status.\n" + "8 - Sair."));
			switch (opcao) {
			case 1:
				String localPath = null;
				while (localPath == null || localPath.equals(""))
					localPath = JOptionPane
							.showInputDialog("Por favor digite o caminho da pasta");


				i = new InitGit(localPath);

				break;
			case 2:
				localPath = "/home/pedro/teste2";
				CreateGit objCreate = new CreateGit(localPath);
				break;
			case 3:

				AddGit objAdd = new AddGit(i.getGit(), "file.xml");
				break;
			case 4:
				localPath = "/home/pedro/teste";

				RemoveFileGit objRemove = new RemoveFileGit(i.getGit(),
						localPath, "arquivo");
				break;
			case 5:
				CommitGit objCommit = new CommitGit(i.getGit(), "commited");
				break;
			case 6:
				localPath = "/home/pedro/teste2";
				String remotePath = "https://github.com/DanielliUrbieta/TCC.git";

				CloneGit objClone = new CloneGit(remotePath, localPath);
				break;
			case 7:

				StatusGit objStatus = new StatusGit(i.getGit());
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

