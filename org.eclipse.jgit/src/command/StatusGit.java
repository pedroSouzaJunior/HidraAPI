package command;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;

/**
 * @author Souza Urbieta
 *
 */
public class StatusGit extends Command {
	/** */
	// protected Git git;

	/** Recebe o Status do repositório */
	protected Status status;

	/**
	 * @param myGit
	 * @throws NoWorkTreeException
	 * @throws GitAPIException
	 */
	@SuppressWarnings("nls")
	public StatusGit(Git myGit) throws NoWorkTreeException, GitAPIException {
		super();
		git = myGit;
		status = git.status().call();

		System.out.println("Added: " + status.getAdded());
		System.out.println("Changed: " + status.getChanged());
		System.out.println("Conflicting: " + status.getConflicting());
		System.out.println("ConflictingStageState: "
				+ status.getConflictingStageState());
		System.out.println("IgnoredNotInIndex: "
				+ status.getIgnoredNotInIndex());
		System.out.println("Missing: " + status.getMissing());
		System.out.println("Modified: " + status.getModified());
		System.out.println("Removed: " + status.getRemoved());
		System.out.println("Untracked: " + status.getUntracked());
		System.out.println("UntrackedFolders: " + status.getUntrackedFolders());

	}



}
