package versao4;


import java.util.Set;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;

/**
 * @author danielli
 *
 */
public class RemoveFileGit {

	/***/
	protected Git git;

	/** Nome do arquivo a ser removido */
	protected String file;

	/** Set status */
	protected Set removed;

	/**
	 * @param git
	 * @param file
	 * @throws GitAPIException
	 * @throws NoFilepatternException
	 */
	public RemoveFileGit(Git git, String file) throws NoFilepatternException,
			GitAPIException {
		super();
		this.git = git;
		this.file = file;
		this.git.rm().addFilepattern(file).call();
		this.removed = git.status().call().getUntracked();
	}

}
