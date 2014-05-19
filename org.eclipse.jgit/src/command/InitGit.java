package command;

import java.io.IOException;

import org.eclipse.jgit.api.Git;

import org.eclipse.jgit.internal.storage.file.FileRepository;

/**
 * @author Urbieta Souza
 *
 *
 */
public class InitGit extends Command {


	/**
	 * @param localPath
	 * @throws IOException
	 */
	public InitGit(String localPath) throws IOException {
		super(localPath);


		// this.localPath = localPath;
		repository = new FileRepository(localPath + "/.git"); //$NON-NLS-1$

		git = new Git(repository);
		// System.out.print(repository);
		System.out.println(repository.getRepositoryState());
		System.out
				.println("Created a new repository at " + repository.getDirectory()); //$NON-NLS-1$

		repository.close();

	}

	/**
	 * @return git
	 */
	public Git getGit() {

		return git;
	}

}
