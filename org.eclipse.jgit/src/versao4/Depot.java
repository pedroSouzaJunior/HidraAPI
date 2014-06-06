package versao4;

/**
 * @author Urbieta Souza
 *
 */

//import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
//import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.CannotDeleteCurrentBranchException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NotMergedException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
//import org.eclipse.jgit.diff.DiffFormatter;
//import org.eclipse.jgit.diff.EditList;
//import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;

//import org.junit.Test;

/**
 * @author Danielli Urbieta e Pedro Souza Junior
 *
 *
 *         classe Depot
 */
public class Depot {

	// atributos
	private String localPath;

	private String remotePath;

	private Repository localRepository;

	private Git git;

	private Repository repository;

	private Set added;

	private Set removed;

	private Status status;


	/**
	 * construtor da classe
	 *
	 * @param localPath
	 * @param remotePath
	 */
	public Depot(String localPath, String remotePath) {

		this.localPath = localPath;
		this.remotePath = remotePath;
	}

	/**
	 * @param localPath
	 */
	public Depot(String localPath) {
		super();
		this.localPath = localPath;
	}

	/**
	 *
	 */
	public Depot() {
		super();
	}

	/* ================ metodos geters e seters ===================== */

	/**
	 * @return the localPath
	 */
	public String getLocalPath() {
		return this.localPath;
	}

	/**
	 * @param localPath
	 *            the localPath to set
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	/**
	 * @return the remotePath
	 */
	public String getRemotePath() {
		return this.remotePath;
	}

	/**
	 * @param remotePath
	 *            the remotePath to set
	 */
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	/**
	 * @return the localRepository
	 */
	public Repository getLocalRepository() {
		return this.localRepository;
	}

	/**
	 * @param localRepository
	 *            the localRepository to set
	 */
	public void setLocalRepository(Repository localRepository) {
		this.localRepository = localRepository;
	}

	/**
	 * @return the git
	 */
	public Git getGit() {
		return this.git;
	}

	/**
	 * @param git
	 *            the git to set
	 */
	public void setGit(Git git) {
		this.git = git;
	}

	/**
	 * @return the repository
	 */
	public Repository getrepository() {
		return this.repository;
	}

	/**
	 * @param repository
	 *            the repository to set
	 */
	public void setrepository(Repository repository) {
		this.repository = repository;
	}


	/**
	 * @return Set
	 */
	public Set getAdded() {
		return this.added;
	}

	/**
	 * @param added
	 */
	public void setAdded(Set added) {
		this.added = added;
	}

	/**
	 * @return removido
	 */
	public Set getRemoved() {
		return removed;
	}

	/**
	 * @param removed
	 */
	public void setRemoved(Set removed) {
		this.removed = removed;
	}

	/*
	 * =========================METHODS====================================
	 * inicio dos metodos da classe JGit metodos da classe:
	 *
	 * init(); create(); add(); commit(); removeFile(); clone(); status();
	 * ====================================================================
	 */

	/**
	 *
	 */
	/*
	 * @Test public void openGitRepo() { assertTrue(gitDir.exists());
	 * assertNotNull(git); }
	 */
	/**
	 * @param localPath
	 * @throws IOException
	 *             metodo init usado para inicialização de um novo repositorio
	 */
	// @SuppressWarnings({ "nls", "hiding" })
	/*
	 * public void init(String localPath) throws IOException {
	 *
	 * setrepository(new FileRepository(localPath + "/.git")); this.git = new
	 * Git(repository); System.out.print(repository);
	 * repository.getRepositoryState(); // setLocalPath(localPath); }
	 */

	/**
	 * @param gitworkDir
	 */
	public void init(String gitworkDir) {
		File dir = new File(gitworkDir);
		InitCommand initCommand = Git.init();
		initCommand.setDirectory(dir);


		try {
			this.git = initCommand.call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param gitworkDir
	 * @return git
	 */
	public Git init1(String gitworkDir) {
		File dir = new File(gitworkDir);
		InitCommand initCommand = Git.init();
		initCommand.setDirectory(dir);

		try {
			this.git = initCommand.call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return git;

	}

	/**
	 * @param localPath
	 * @throws IOException
	 * @metodo para criação de um novo repositorio
	 */
	@SuppressWarnings({ "nls", "hiding" })
	public void create(String localPath) throws IOException {


		setrepository(new FileRepository(localPath + "/.git"));
		repository.create();
	}

	/**
	 * @param file
	 * @throws GitAPIException
	 * @throws NoFilepatternException
	 * @throws IOException
	 *             metodo para adicionar um novo arquivo ao repositorio arquivo
	 *             passa a ser monitorado pelo repositorio Recebe o nome do
	 *             arquivo que deve ser adicionado
	 */

	public void add(String file) throws NoFilepatternException,
			GitAPIException,
			IOException {

		git.add().addFilepattern(file).call();
		setAdded(git.status().call().getAdded());
		JOptionPane.showMessageDialog(null, added);
	}

	/**
	 * @param message
	 * @throws GitAPIException
	 */

	public void commit(String message) throws GitAPIException {

		RevCommit commit = git.commit().setMessage(message).call();
		JOptionPane.showMessageDialog(null, commit.getId().getName());
	}

	/**
	 * @param file
	 * @throws GitAPIException
	 * @throws NoWorkTreeException
	 *
	 */

	public void removeFile(String file) throws NoWorkTreeException,
			GitAPIException {
		this.git.rm().addFilepattern(file).call();
		this.setRemoved(git.status().call().getUntracked());
		if (this.getRemoved() != null)
			JOptionPane.showMessageDialog(null,
					"File " + file + "successfully removed "); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @param copy
	 *
	 *
	 * @param remotePath1
	 *
	 * @throws GitAPIException
	 * @throws TransportException
	 * @throws InvalidRemoteException
	 *
	 *             Metodo que clona um repositorio Recebe como parametro duas
	 *             Strings, "localPath" que é aonde ficará o repositorio clonado
	 *             e "remotePath" que é o repositorio a ser clonado
	 * @throws IOException
	 */

	public void clone(String copy, String remotePath1)
			throws InvalidRemoteException, TransportException,
 GitAPIException,
			IOException {

		File file = new File(copy);
		Git.cloneRepository().setURI(remotePath1).setDirectory(file).call();

		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		Repository repository1 = builder.setGitDir(file).readEnvironment()
				.findGitDir()
				.build();

		JOptionPane.showMessageDialog(null,
				"Having repository: " + repository1.getDirectory()); //$NON-NLS-1$

		repository1.close();


	}

	/**
	 * @throws GitAPIException
	 * @throws NoWorkTreeException
	 *             Classe equivalente ao Git Status
	 */
	@SuppressWarnings("nls")
	public void status() throws NoWorkTreeException, GitAPIException {
		status = git.status().call();

		JOptionPane.showMessageDialog(
				null,
				"Added: " + status.getAdded() + "\nChanged"
						+ status.getChanged() + "\nConflicting: "
						+ status.getConflicting() + "\nConflictingStageState: "
						+ status.getConflictingStageState()
						+ "\nIgnoredNotInIndex: "
						+ status.getIgnoredNotInIndex() + "\nMissing: "
						+ status.getMissing() + "\nModified: "
						+ status.getModified() + "\nRemoved: "
						+ status.getRemoved() + "\nUntracked: "
						+ status.getUntracked() + "\nUntrackedFolders: "
						+ status.getUntrackedFolders()
						+ "\nUncommitted Changes"
						+ status.getUncommittedChanges());

		// JOptionPane.showInternalMessageDialog(null, "s", title, messageType);

	}

	/**
	 *
	 * @param localPath1
	 * @throws NoHeadException
	 * @throws GitAPIException
	 * @throws RevisionSyntaxException
	 * @throws AmbiguousObjectException
	 * @throws IOException
	 */
	public void getLogs(String localPath1) throws NoHeadException,
			GitAPIException, RevisionSyntaxException, AmbiguousObjectException,
			IOException {

		Git git1 = this.init1(localPath1);
		// Repository repository1 = git1.getRepository();
		//ObjectId head = repository1.resolve("HEAD"); //$NON-NLS-1$
		Iterable<RevCommit> log1 = git1.log().all().call();
		Iterator itr = log1.iterator();


		while (itr.hasNext()) {
			Object element = itr.next();
			RevCommit rev = (RevCommit) itr.next();
			System.out.println(element);
			System.out.println("Author: " + rev.getAuthorIdent().getName()); //$NON-NLS-1$
			System.out.println("Message: " + rev.getFullMessage()); //$NON-NLS-1$
			System.out.println();
		}


	}

	/*
	 * ========================Criar outro branch para poder testar public void
	 * Diff(String localPath1) { Git git1 = this.init1(localPath1); try {
	 * //<DiffEntry> listDiff = git1.diff().call();
	 *
	 *
	 * } catch (GitAPIException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 *
	 * }
	 */

	/**
	 * @param localPath1
	 */
	@SuppressWarnings("null")
	public void showBranch(String localPath1) {

		Git git1 = this.init1(localPath1);
		Repository repository1 = git1.getRepository();
		List<org.eclipse.jgit.lib.Ref> call = null;
		try {
			call = new Git(repository1).branchList().call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// repensar mostrar ou não o id do branch
		for (org.eclipse.jgit.lib.Ref ref : call) {
			System.out
.println("Branch: " + ref + " " + ref.getName()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		repository1.close();
	}

	/**
	 * @param localPath1
	 * @param nameBranch
	 */

	@SuppressWarnings({ "nls", "null" })
	public void createBranch(String localPath1, String nameBranch) {
		Git git1 = this.init1(localPath1);
		Repository repository1 = git1.getRepository();
		try {
			git1.branchCreate().setName(nameBranch).call();
		} catch (RefAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RefNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidRefNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GitAPIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<org.eclipse.jgit.lib.Ref> call = null;
		try {
			call = new Git(repository1).branchList().call();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// repensar mostrar ou não o id do branch
		for (org.eclipse.jgit.lib.Ref ref : call) {
			System.out.println("Branch Created: " + ref + " " + ref.getName());

		}
		repository1.close();

	}

	/**
	 * @param localPath1
	 * @param nameBranch
	 */
	public void deleteBranch(String localPath1, String nameBranch) {
		Git git1 = this.init1(localPath1);

		try {
			git1.branchDelete().setBranchNames(nameBranch).call();
		} catch (NotMergedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotDeleteCurrentBranchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @param file1
	 *
	 * @param file2
	 *
	 * @return toString
	 *
	 * public String getDiff(String file1, String file2) { OutputStream out =
	 * new ByteArrayOutputStream(); try { RawText rt1 = new RawText(new
	 * File(file1)); RawText rt2 = new RawText(new File(file2)); EditList
	 * diffList = new EditList(); diffList.addAll(differ.diff(COMP, rt1, rt2));
	 * new DiffFormatter(out).format(diffList, rt1, rt2); } catch (IOException
	 * e) { e.printStackTrace(); } return out.toString(); }
	 */
}