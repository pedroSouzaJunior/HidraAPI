package versao4;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;

/**
 * @author danielli
 *
 */
public class MainTeste {

	/**
	 * @param args
	 * @throws IOException
	 * @throws GitAPIException
	 * @throws NoFilepatternException
	 */
	public static void main(String[] args) throws IOException,
			NoFilepatternException, GitAPIException {

		JGit newRepo = new JGit();


		newRepo.init();
		// newRepo.testCreate();
		// newRepo.testAdd();
		// newRepo.testCommit();
		// newRepo.testRemoveFile();
		newRepo.testClone();

		// newRepo.status();




}

}