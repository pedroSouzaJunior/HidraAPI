package command;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.errors.NoWorkTreeException;

/**
 * @author Urbieta Rocha
 *
 *         Classe responsável por adicionar um novo arquivo ao repositório
 *         local, o novo arquivo passa a ser monitorado e pode ser 'commitado'
 *
 */
public class AddGit extends Command {

	/***/
	// protected Git git;

	/** Arquivo que será adicionado ao Repositório */
	protected File myFile;

	/** Responsável de 'setar' o status do Git */
	protected Set Added;

	/**
	 * @param myGit
	 * @param myFile
	 * @throws GitAPIException
	 * @throws NoWorkTreeException
	 * @throws NoFilepatternException
	 * @throws IOException
	 */
	public AddGit(Git myGit, File myFile) throws NoWorkTreeException,
			GitAPIException, NoFilepatternException, IOException {
		super();
		git = myGit;
		this.myFile = myFile;
		git.add().addFilepattern("file").call(); //$NON-NLS-1$
		Added = (git.status().call().getAdded());

	}


}

