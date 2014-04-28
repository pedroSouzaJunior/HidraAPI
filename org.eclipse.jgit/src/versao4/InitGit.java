package versao4;

import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

/**
 * @author Urbieta Souza
 *
 * 
 */
public class InitGit {

	/**
	 *
	 */
	protected Git git;

	private Repository myRepo;

	/**
	 * caminho do repositorio local
	 */
	protected String localPath;

	/**
	 * @param localPath
	 * @throws IOException
	 */
	public InitGit(String localPath) throws IOException {
		super();

		this.localPath = localPath;
		this.myRepo = new FileRepository(localPath + "/.git"); //$NON-NLS-1$

		this.git = new Git(myRepo);
		System.out.print(myRepo);
		myRepo.getRepositoryState();
		// System.out.println("Created a new repository at " +
		// myRepo.getDirectory());

		// repository.close();
	}

}
