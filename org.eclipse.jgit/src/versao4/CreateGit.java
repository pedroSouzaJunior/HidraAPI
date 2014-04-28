package versao4;

import java.io.IOException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

/**
 * @author urbieta souza Classe responsável pela criação de um repositório local
 *         vazio Git Recebe como parametro o caminho do repositŕoio
 *
 */
public class CreateGit {

	private String localpath;

	private Repository myRepo;

	/**
	 * @param localpath
	 * @throws IOException
	 */
	public CreateGit(String localpath) throws IOException {
		super();
		this.setLocalpath(localpath);
		myRepo = (new FileRepository(localpath + "/.git")); //$NON-NLS-1$
		myRepo.create();

	}

	/**
	 * @return localpath
	 */
	public String getLocalpath() {
		return localpath;
	}

	/**
	 * @param localpath
	 */
	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}

}
