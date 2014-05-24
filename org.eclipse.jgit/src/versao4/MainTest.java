package versao4;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;



/**
 * @author danielli
 *
 */
public class MainTest {

	/**
	 * @param args
	 * @throws IOException
	 * @throws GitAPIException
	 * @throws NoFilepatternException
	 */
	@SuppressWarnings("nls")
	public static void main(String[] args) throws IOException,
			NoFilepatternException, GitAPIException {

		Depot newRepo = new Depot();


		//newRepo.init(null);
		// newRepo.create();
		// newRepo.add();
		// newRepo.commit();
		// newRepo.removeFile();
		//newRepo.c
		//lone(localPath, remotePath);
		// newRepo.status();


		int opcao;
		do {
			opcao = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(""
					+ "1 - Init.\n" + "2 - Create new Repository.\n"
					+ "3 - Add file.\n" + "4 - Remove file.\n"
					+ "5 - Commit.\n" + "6 - Clone Repository.\n"
					+ "7 - Show Status.\n" + "8 - Sair."));
			String localPath = null;
			String filename = null;
			String message = null;
			String remotePath = null;
			switch (opcao) {
			case 1:

				while (localPath == null || localPath.equals(""))
					localPath = JOptionPane
							.showInputDialog("Digite o caminho da pasta");
				newRepo.init(localPath);

				break;
			case 2:

				while (localPath == null || localPath.equals(""))
					localPath = JOptionPane
							.showInputDialog("Digite o caminho do novo repositorio");
				newRepo.create(localPath);
				break;
			case 3:
				while (filename == null || filename.equals(""))
					filename = JOptionPane
							.showInputDialog("Digite o nome do arquivo a ser adicionado");
				newRepo.add(filename);
				break;
			case 4:
				while (filename == null || filename.equals(""))
					filename = JOptionPane
							.showInputDialog("Digite o caminho do arquivo a ser removido");
					newRepo.removeFile(filename);
				break;
			case 5:
				while (message == null || message.equals(""))
					message = JOptionPane
							.showInputDialog("Digite a mensagem do commit");
				newRepo.commit(message);
				break;
			case 6:

				while (localPath == null || localPath.equals(""))
					localPath = JOptionPane
							.showInputDialog("Entre com o endereco da pasta aonde o repositorio a ser clonado ficara");

				while(remotePath == null|| remotePath.equals(""))
					remotePath = JOptionPane
					.showInputDialog("Entre com o endereço do repositorio a ser clonado");
				newRepo.clone(localPath, remotePath);
				break;
			case 7:
				newRepo.status();
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

