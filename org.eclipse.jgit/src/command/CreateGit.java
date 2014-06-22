package command;

import java.io.IOException;
import org.eclipse.jgit.internal.storage.file.FileRepository;

/**
 * @author urbieta souza Classe responsável pela criação de um repositório local
 *         Git vazio. Recebe como parametro o caminho do repositŕoio.
 *
 */
public class CreateGit extends Command {

	// private String localpath;

	// private Repository myRepo;

	/**
	 * @param localpath
	 * @throws IOException
	 */

	public CreateGit(String localpath) throws IOException {
		super(localPath);
		repository = (new FileRepository(localPath + "/.git")); //$NON-NLS-1$
		repository.create();

	}


}
