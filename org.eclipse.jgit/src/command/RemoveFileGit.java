package command;


import java.util.Set;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;

/**
 * @author danielli
 *
 */
public class RemoveFileGit extends Command {

	/***/
	// protected Git git;

	/** Nome do arquivo a ser removido */
	// protected String file;

	/** Set status */
	protected Set removed;

	/**
	 * @param myGit
	 * @param localPath
	 * @param myFile
	 * @throws GitAPIException
	 * @throws NoFilepatternException
	 */
	public RemoveFileGit(Git myGit, String localPath, String myFile)
			throws NoFilepatternException,
			GitAPIException {
		super(localPath);

		// this.git = git;

		git = myGit;
		file = myFile;
		git.rm().addFilepattern(file).call();
		removed = git.status().call().getUntracked();
	}

}
