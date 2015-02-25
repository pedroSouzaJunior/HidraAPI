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
 * @author <br><i>Danielli - Pedro</i></br>
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
     * Method responsible for instantiation and initialization repository
     * Receives the parameters the directory que will be instantiated as a
     * repository, and the repository helper object to boot git.
     *
     * @param directory
     * @param Auxiliary
     */
    public void inicializar(File directory, Git Auxiliary) {

        hidra = new Hidra(Auxiliary);
        hidra.setLocalPath(directory.getAbsolutePath());

    }

    /**
     * Method responsible for adding files in a repository. Receives as
     * parameters a file. Check whether the current repository this started<br>
     * Then it is verified the existence of the file that was passed as a
     * parameter. It is then checked the extension of the file, if the type
     * .bpmn the file will be added to the repository. Otherwise One exception
     * is thrown stating that the file type is not available.
     *
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
     * Web service responsible for the creation of a new branch in the
     * repository. Checks whether the repository this started. If so, the Hidra
     * base object will be used for instantiation of a new Branch. The final
     * name given to the new branch is returned.
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
