package command;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

/**
 * @author Urbieta Souza
 *
 */
public class CloneGit extends Command {


	/**
	 * @param remotePath
	 * @param localPath
	 * @throws GitAPIException
	 * @throws TransportException
	 * @throws InvalidRemoteException
	 */
	public CloneGit(String remotePath, String localPath)
			throws InvalidRemoteException, TransportException, GitAPIException {
		super(localPath, remotePath);
		Command.remotePath = remotePath;
		Command.localPath = localPath;
		Git.cloneRepository()
.setURI(remotePath)
				.setDirectory(new File(localPath)).call();

	}

}
