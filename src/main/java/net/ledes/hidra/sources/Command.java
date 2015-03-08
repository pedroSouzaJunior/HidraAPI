package net.ledes.hidra.sources;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 * <b>Java class that contains the methods to store handling.</b>
 *
 * @author <b><i>Danielli - Pedro</i></b>
 * @version 1.0
 */
public class Command {

    private Hidra hidra;

    public Hidra getHidra() {
        return hidra;
    }

    public void setHidra(Hidra hidra) {
        this.hidra = hidra;
    }

    /**
     * Method responsible for instantiation and initialization repository<br/>
     * Receives the parameters the directory that will be instantiated as a
     * repository, and the repository helper object to boot git.
     * <br/>
     * Método responsável pela instanciação e inicialização de repositório.<br/>
     * Recebe os parâmetros o diretório que será instanciado como um
     * repositório, e o objeto auxiliar git para inicializar um repositório.
     *
     * @param directory
     * @param Auxiliary
     */
    public void inicializar(File directory) throws GitAPIException {

        hidra = new Hidra(directory);
        hidra.setLocalPath(directory.getAbsolutePath());

    }

    /**
     * Method responsible for adding files in a repository.<br/> Receives as
     * parameters a file. Check whether the current repository this started<br/>
     * Then it is verified the existence of the file that was passed as a
     * parameter.<br/> It is then checked the extension of the file, if the type
     * .bpmn the file will be added to the repository.<br/> Otherwise One
     * exception is thrown stating that the file type is not available.
     * <br/>
     * Método responsável por adicionar arquivos em um repositório.<br/> recebe
     * como Parâmetros um arquivo. Verifica-se se o repositório atual esta
     * inicializado
     * <br/><br/>
     * Em seguida, verifica-se a existência do arquivo que foi passado como um
     * parâmetro.<br/> Em seguida, é verificada a extensão do arquivo, se o tipo
     * de arquivo é .bpmn será adicionado ao repositório.<br/> Caso contrário,
     * uma exceção é lançada afirmando que o tipo de arquivo não está
     * compativel.
     * <br/>
     *
     * @param fileName
     * @return boolean
     */
    public boolean adicionar(String fileName) {
        File file;
        String extension[];

        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {
            file = new File(hidra.getLocalPath() + "/" + fileName);
            if (!file.exists()) {
                System.err.println("file does not exist");
                return false;
            } else {
                extension = fileName.split("\\.");
                if (!extension[1].equals("bpmn")) {
                    System.err.println("Incorrect file type: "
                            + extension[1]);
                    return false;
                } else {
                    try {
                        hidra.getGit().add().addFilepattern(fileName).call();
                        hidra.setAdded(hidra.getGit().status().call()
                                .getAdded());
                    } catch (GitAPIException e) {
                        System.out.println(e.getMessage());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method responsible for removing repository files. Receives as parameters
     * a file. Check whether the current repository this started, then it is
     * verified the existence of the file that was passed as a parameter, then
     * the file is removed and deleted from the repository. Otherwise One
     * exception is thrown stating that the file does not exist. At the end a
     * success message is informed
     * <br/><br/>
     * Método responsável pela remoção de arquivos do repositório. Recebe como
     * parâmetros um arquivo. Verifica-se se o repositório atual foi
     * inicializado, então é verificado a existência do arquivo que foi passado
     * como um parâmetro, em seguida, o arquivo é removido e excluído do
     * repositório. Caso contrário uma exceção é lançada informando que o
     * arquivo não existe. No final uma mensagem de sucesso é informada
     *
     * @param filename
     * @return boolean
     */
    public boolean remove(String filename) {
        File file;

        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {

            file = new File(hidra.getLocalPath() + "/" + filename);

            if (!file.exists()) {
                System.err.println("file does not exist");
            } else {
                try {
                    hidra.getGit().rm().addFilepattern(filename).call();
                    hidra.setRemoved(this.hidra.getGit().status().call()
                            .getUntracked());
                } catch (GitAPIException e) {
                    System.out.println(e.getMessage());
                }

                if (this.hidra.getRemoved() != null) {
                    System.out.println("Successfully excluded file");
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Method responsible for sending changes to the repository. Receives as
     * parameters a message to commit control. Check whether the current
     * repository this initialized, then the amendment submission process is
     * done. Otherwise One exception is thrown stating that the store did not
     * initialize correctly.
     * <br/><br/>
     * Método responsável por enviar alterações ao repositório. recebe como
     * Parâmetros uma mensagem para controle de commit. Verifica-se se o
     * repositório atual esta inicializado, então o processo de envio de
     * alteração é feito. Caso contrário, uma exceção é lançada afirmando que o
     * repositório não inicializar corretamente.
     *
     * @param message
     * @return boolean
     */
    public boolean commit(String message) {

        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {
            try {
                RevCommit commit = hidra.getGit().commit().setMessage(message)
                        .call();
                System.out.println(commit.getId().getName());
            } catch (GitAPIException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
        return false;
    }

    /**
     * Method responsible for creating a local copy of a remote repository.
     * Receives as parameters in the URL of the remote repository, and the disk
     * address in which to set the copy of the remote repository. After it is
     * checked whether the directory is empty, otherwise an exception will be
     * lançanda stating that the provided directory already contains
     * information.
     * <br/><br/>
     * Método responsável por criar uma cópia local de um repositório remoto.
     * Recebe como parâmetros a URL do repositório remoto, e o endereço me disco
     * para definir a cópia do repositório remoto. Depois é verificado se o
     * diretório está vazio, caso contrário, uma exceção será lançanda
     * informando que o diretório desde já contém informações.
     *
     * @param remotePath
     * @param directory
     * @return boolean
     * @throws java.io.IOException
     * @throws org.eclipse.jgit.api.errors.InvalidRemoteException
     * @throws org.eclipse.jgit.api.errors.TransportException
     */
    public boolean cloneW(String remotePath, File directory) throws IOException,
            InvalidRemoteException, TransportException, GitAPIException {

        if (directory.exists() && directory.listFiles().length != 0) {
            System.out.println("Repository not empty , Canceled Operation");
            return false;
        } else {
            Git result = Git.cloneRepository().setURI(remotePath)
                    .setDirectory(directory).call();

            try {
                System.out.println("Repository successfully cloned "
                        + result.getRepository().getDirectory());
            } finally {
                result.close();
            }
            return true;
        }
    }

    /**
     * Method responsible for showing the status of a repository. Checks whether
     * the repository this started. If so, informs a list of properties for the
     * repository as: Last added file, Conflict, File Modified, Removed File,
     * File not sent by commit
     * <br/><br/>
     * Método responsável por mostrar o status de um repositório. Verifica se o
     * repositório esta inicializado. Se assim for, informa uma lista de
     * propriedades referentes ao repositório como: Último arquivo adicionado,
     * Conflito, de Modificação do arquivo, arquivo removido, Arquivo não
     * enviado por commit
     *
     * @return
     */
    public String status() {
        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {
            try {
                hidra.setStatus(hidra.getGit().status().call());
                String showStatus = "Added: "
                        + this.hidra.getStatus().getAdded() + "\nChanged"
                        + this.hidra.getStatus().getChanged()
                        + "\nConflicting: "
                        + this.hidra.getStatus().getConflicting()
                        + "\nConflictingStageState: "
                        + this.hidra.getStatus().getConflictingStageState()
                        + "\nIgnoredNotInIndex: "
                        + this.hidra.getStatus().getIgnoredNotInIndex()
                        + "\nMissing: " + this.hidra.getStatus().getMissing()
                        + "\nModified: " + this.hidra.getStatus().getModified()
                        + "\nRemoved: " + this.hidra.getStatus().getRemoved()
                        + "\nUntracked: "
                        + this.hidra.getStatus().getUntracked()
                        + "\nUntrackedFolders: "
                        + this.hidra.getStatus().getUntrackedFolders()
                        + "\nUncommitted Changes"
                        + this.hidra.getStatus().getUncommittedChanges();

                return showStatus;
            } catch (NoWorkTreeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (GitAPIException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Method responsible for displaying a set of logs from a repository. Checks
     * whether the repository this started. If so, the log repository will be
     * informed.
     * <br/><br/>
     * Método responsável por exibir um conjunto de registros (Logs) de um
     * repositório. verifica-se se o repositório foi inicializado. Se assim for,
     * os logs do repositório serão informados.
     *
     * @return boolean
     */
    public String getLogs() {
        String logs = null;
        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {

            // Repository repository1 = git1.getRepository();
            //ObjectId head = repository1.resolve("HEAD"); //$NON-NLS-1$
            Iterable<RevCommit> log = null;
            try {
                log = hidra.getGit().log().call();
            } catch (NoHeadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (GitAPIException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            @SuppressWarnings("rawtypes")
            Iterator itr = log.iterator();

            while (itr.hasNext()) {
                // Object element = itr.next();
                RevCommit rev = (RevCommit) itr.next();
                // System.out.println(element);
                logs = "Author: " + rev.getAuthorIdent().getName()
                        + "\nMessage: " + rev.getFullMessage();
                /*
                 * System.out.println("Author: " +
                 * rev.getAuthorIdent().getName()); //$NON-NLS-1$
                 * System.out.println("Message: " + rev.getFullMessage());
                 * //$NON-NLS-1$ System.out.println();
                 */
                return logs;
            }

        }
        return logs;
    }

    /**
     * Method responsible for displaying all branches of a repository. Checks
     * whether the repository this started. If so, the Hidra base object is used
     * to obtain a list of all the branchs associated with the repository.
     * <br/><br/>
     * Método responsável por exibir todos os ramos de um repositório.
     * verifica-se se o repositório foi inicializado. Se assim for, o objeto de
     * base Hidra é utilizada para obter uma lista de todas as ramificações
     * associadas com o repositório.
     *
     * @return boolean
     */
    @WebMethod
    public String showBranch() {
        String branches = null;
        if (hidra == null) {
            System.err.println("Repository uninitialized");
        } else {
            List<org.eclipse.jgit.lib.Ref> call = null;
            try {
                call = new Git(hidra.getGit().getRepository()).branchList()
                        .call();
            } catch (GitAPIException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // repensar mostrar ou não o id do branch
            for (org.eclipse.jgit.lib.Ref ref : call) {
                branches = "Branch: " + ref.getName();
            }
        }
        hidra.getGit().getRepository().close();
        return branches;
    }

    /**
     * Method responsible for the creation of a new branch in the repository.
     * Checks whether the repository this started. If so, the Hidra base object
     * will be used for instantiation of a new Branch. The final name given to
     * the new branch is returned.
     * <br/><br/>
     * Método responsável pela criação de uma nova branch (ramo) no repositório.
     * Verifica se o repositório foi inicializado. Se assim for, o Hidra objeto
     * de base serão utilizados para a instanciação de uma novo branch (ramo). a
     * final nome dado ao novo ramo é retornado.
     *
     * @param nameBranch
     * @return branch
     */
    public String createBranch(String nameBranch) {
        String branch = null;
        if (hidra == null) {
            System.err.println("Repositorio nao inicializado");
        } else {
            try {
                hidra.getGit().branchCreate().setName(nameBranch).call();

            } catch (RefAlreadyExistsException e1) {

                e1.printStackTrace();
            } catch (RefNotFoundException e1) {

                e1.printStackTrace();
            } catch (InvalidRefNameException e1) {

                e1.printStackTrace();
            } catch (GitAPIException e1) {

                e1.printStackTrace();
            }

            List<org.eclipse.jgit.lib.Ref> call = null;
            try {
                call = new Git(hidra.getGit().getRepository()).branchList()
                        .call();
            } catch (GitAPIException e) {

                e.printStackTrace();
            }

            for (org.eclipse.jgit.lib.Ref ref : call) {
                branch = "Branch Created: " + " " + ref.getName();

            }
            hidra.getGit().getRepository().close();

            return branch;
        }
        return branch;
    }
}
