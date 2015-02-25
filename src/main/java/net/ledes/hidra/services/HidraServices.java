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
