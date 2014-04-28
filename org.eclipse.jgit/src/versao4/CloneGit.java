package versao4;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

/**
 * @author Urbieta Souza
 *
 */
public class CloneGit {

	/** caminho do repositorio a ser clonado */
	protected String remotePath;

	/** caminho do repositorio local onde ficará o repositorio clonado */
	protected String localPath;

	/**
	 * @param remotePath
	 * @param localPath
	 * @throws GitAPIException
	 * @throws TransportException
	 * @throws InvalidRemoteException
	 */
	public CloneGit(String remotePath, String localPath)
			throws InvalidRemoteException, TransportException, GitAPIException {
		super();
		this.remotePath = remotePath;
		this.localPath = localPath;
		Git.cloneRepository()
.setURI(remotePath)
				.setDirectory(new File(localPath)).call();

	}

}
