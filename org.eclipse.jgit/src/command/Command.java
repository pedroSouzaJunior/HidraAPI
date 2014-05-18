package command;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

/**
 * @author Urbieta Souza Superclasse dos comandos basicos do Git
 *
 */
abstract class Command {

	/***/
	protected static Git git;

	/**
	 * Caminho do repositorio local
	 */
	protected static String localPath;

	/**
	 * Repositorio
	 */
	protected static Repository repository;

	/**
	 * Endereco do repositorio remoto
	 */
	protected static String remotePath;

	/**
	 * Nome do arquivo que pode ser adicionado ou removido
	 */
	protected String file;

	/**
	 * @return localPath
	 */
	public String getLocalPath() {
		return localPath;
	}


	/**
	 * @param localPath
	 * @param remotePath
	 */
	public Command(String localPath,
			String remotePath) {
		super();
		// Command.git = git;
		Command.localPath = localPath;
		Command.remotePath = remotePath;
		// Command.repository = repository;
	}

	/**
	 * @param git
	 */
	public Command(Git git) {
		Command.git = git;
	}

	/**
	 * @param localPath2
	 */
	public Command(String localPath2) {

		// Command.git = git2;
		Command.localPath = localPath2;
		// Command.repository = repository2;
	}

	/**
	 * Construto vazio
	 */
	public Command() {
	}

}