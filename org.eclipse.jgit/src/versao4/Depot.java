package versao4;

/**
 * @author Urbieta Souza
 *
 */

import java.io.IOException;
import java.io.File;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
//import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
//import org.eclipse.jgit.api.errors.TransportException;

import org.eclipse.jgit.api.errors.TransportException;


//import org.eclipse.jgit.transport.PushResult;

import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.internal.storage.file.*;

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
	public Set getRemovido() {
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
		System.out.print(added);
	}

	/**
	 * @param message
	 * @throws GitAPIException
	 */

	public void commit(String message) throws GitAPIException {

		RevCommit commit = git.commit().setMessage(message).call();
		System.out.println(commit.getId().getName());
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
	}

	/**
	 * @param copy
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

		System.out.println("Having repository: " + repository1.getDirectory()); //$NON-NLS-1$

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