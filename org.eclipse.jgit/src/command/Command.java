package command;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

/**
 * @author Urbieta Souza Superclasse dos comandos basicos do Git
 *
 */
public class Command {

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
	 * @return localPath
	 */
	public String getLocalPath() {
		return localPath;
	}


	/**
	 * @param git
	 * @param localPath
	 * @param repository
	 * @param remotePath
	 */
	public Command(Git git, String localPath, Repository repository,
			String remotePath) {
		super();
		Command.git = git;
		Command.localPath = localPath;
		Command.remotePath = remotePath;
		Command.repository = repository;
	}

	/**
	 * @param localPath2
	 */
	public Command(String localPath2) {

		// Command.git = git2;
		Command.localPath = localPath2;
		// Command.repository = repository2;
	}


}
