package command;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 * @author Urbieta Souza Classe responsável por 'commitar' um arquivo para o
 *         ramo do repositório
 * 
 */
public class CommitGit extends Command {

	/***/
	// protected Git git;

	private String message;

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param git
	 * @param message
	 * @throws GitAPIException
	 * @throws WrongRepositoryStateException
	 * @throws ConcurrentRefUpdateException
	 * @throws UnmergedPathsException
	 * @throws NoMessageException
	 * @throws NoHeadException
	 */
	public CommitGit(Git git, String message) throws NoHeadException,
			NoMessageException, UnmergedPathsException,
			ConcurrentRefUpdateException, WrongRepositoryStateException,
			GitAPIException {
		super(git);
		Command.git = git;
		RevCommit commit = git.commit().setMessage(message).call();
		System.out.println(commit.getId().getName());
	}

}
