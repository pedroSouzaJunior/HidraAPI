package net.ledes.hidra.services;

import java.io.File;
import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.ledes.hidra.sources.Command;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 * <b>Java class that contains the implementation of Web services belonging to
 * iterface IHidra.</b>
 *
 * @author <br><i>Danielli - Pedro</i></br>
 * @version 1.0
 */
@WebService(serviceName = "HidraControl/Command")
public class HidraServices {

    Command commands = new Command();

    /**
     * Web service responsible for instantiation and initialization repository.
     * Web service responsible for instantiation and initialization of a
     * repository, which receives as parameter the local path to the
     * instantiation of the new repository. With the local path is generated a
     * directory that will pass the boot process by the method of Control
     * Repository layer with the helper object.
     *
     * @see Command
     * @param localPath
     * @return ret
     */
    @WebMethod
    public boolean start(@WebParam(name = "localPath") String localPath) {
        boolean ret = false;
        File directory = new File(localPath);

        try {
            Git auxiliary = Git.init().setDirectory(directory).call();
            commands.inicializar(directory, auxiliary);
            ret = true;
        } catch (GitAPIException e) {
            System.out.println(e.getMessage());
        }

        return ret;
    }

    /**
     * Web service responsible for adding files in a repository. Receives as
     * parameter to the name of the file to be added to the repository. Is used
     * the object commands to perform the addition of new file through the
     * Repository Control layer.
     *
     * @param fileName
     * @return ret
     */
    @WebMethod
    public boolean addOn(@WebParam(name = "fileName") String fileName) {
        boolean ret = false;

        try {
            ret = commands.adicionar(fileName);

        } catch (Exception e) {
            System.err.println("Error adding file");
        }
        return ret;

    }

    /**
     * Web service responsible for removing repository files. Receives as
     * parameter to the name of the file to be deleted from the repository. It
     * uses the commands to effect the removal of the object file repository
     * through the control layer.
     *
     * Recebe como paramêtro o nome do arquivo a ser removido do repositório.
     * Utiliza-se o objeto commands para efetuar a remoção do arquivo através da
     * camada de Controle de Repositório.
     *
     * @param filename
     * @return boolean
     */
    @WebMethod
    public boolean remove(@WebParam(name = "filename") String filename) {
        boolean ret = false;

        try {
            commands.remove(filename);
            ret = true;
        } catch (Exception e) {
            System.err.println("Error deleting file");
        }
        return ret;

    }

    /**
     * Web service responsible for sending changes to the repository. Receives
     * as parameter to a control message. Is used the object commands to make
     * the change by sending Repository Control layer.
     *
     * Recebe como paramêtro uma mensagem de controle. Utiliza-se o objeto
     * commands para efetuar o envio de alterações através da camada de Controle
     * de Repositório.
     *
     * @param message
     * @return boolean
     */
    @WebMethod
    public boolean commit(@WebParam(name = "message") String message) {
        boolean ret = false;

        try {
            commands.commit(message);
            ret = true;
        } catch (Exception e) {
            System.err.println("Error during commit process");
        }
        return ret;

    }

    /**
     * Web service responsible for creating a local copy of a remote repository.
     * Receives as parameter the local path to creating a directory, and the URI
     * of the remote repository that will be copied to the new directory, the
     * commands object is used to perform the cloning operation in Repository
     * Control layer
     *
     * Recebe como parametro o caminho local para criação de um diretório, e a
     * URI do repositório remoto que será copiado para o novo diretório, o
     * objeto commands é utilizado para efetuar a operação de clonagem na camada
     * de Controle de Repositório
     *
     * @param remotePath
     * @param localPath
     * @return boolean
     */
    @WebMethod
    public boolean clone(@WebParam(name = "remotePath") String remotePath, @WebParam(name = "localPath") String localPath) {
        boolean ret = false;
        File directory = new File(localPath);

        try {
            ret = commands.cloneW(remotePath, directory);
        } catch (IOException e) {
            System.err.println("Error during cloning process");
        } catch (GitAPIException e) {
            System.err.println("Error during cloning process");
        }
        return ret;
    }

    /**
     * Web service responsible for showing the status of a repository. Invokes
     * status control method through the commands object Repository control
     * layer
     *
     * Invoca método de controle de status por meio do objeto commands da camada
     * de Controle de Repositório
     *
     * @return boolean
     */
    @WebMethod
    public boolean status() {
        boolean ret = false;

        try {
            System.out.println(commands.status());
            ret = true;
        } catch (Exception e) {

        }
        return ret;
    }

    /**
     * Web service responsible for displaying a set of logs from a repository.
     * Invokes repository log control method through the commands object
     * Repository control layer
     *
     * Invoca método de controle de Log de repositório por meio do objeto
     * commands da camada de Controle de Repositório
     *
     * @return boolean
     */
    @WebMethod
    public boolean Logs() {
        boolean ret = false;

        try {
            System.out.println(commands.getLogs());
            ret = true;
        } catch (Exception e) {
            System.err.println("Error during Logs process");
        }
        return ret;

    }

    /**
     * Web service responsible for displaying all branches of a repository.
     * Invoke method to display all Branches belonging to the repository through
     * the commands object Repository control layer
     *
     * Invocar método para exibir todos os Branches pertencentes ao repositório
     * por meio do objeto commands da camada de Controle de Repositório
     *
     * @return boolean
     */
    @WebMethod
    public boolean Branch() {
        boolean ret = false;

        try {
            System.out.println(commands.showBranch());
            ret = true;
        } catch (Exception e) {
            System.err.println("Error during Branch process");
        }

        return ret;

    }

    /**
     * Web service responsible for the creation of a new branch in the
     * repository.Receives as parameter a name that will be assigned to the new
     * branch that will be created through the commands object that controls the
     * repository Control layer.
     *
     * Recebe como parametro um nome que será atribuido ao novo branch que será
     * criado por meio do objeto commands que controla a camada de Controle de
     * Repositorio.
     *
     * @param nameBranch
     * @return boolean
     */
    @WebMethod
    public boolean createBranch(@WebParam(name = "nameBranch") String nameBranch) {
        boolean ret = false;

        try {
            System.out.println(commands.createBranch(nameBranch));
            ret = true;
        } catch (Exception e) {
            System.err.println("Error During branch Creation Process");
        }
        return ret;
    }

}
