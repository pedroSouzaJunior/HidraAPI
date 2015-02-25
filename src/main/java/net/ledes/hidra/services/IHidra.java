package net.ledes.hidra.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * <b>Interface responsible for Web Services repository control methods.</b>
 *
 * @author <i>Danielli - Pedro</i>
 * @version 1.0
 */
public interface IHidra {

    /**
     * Web service responsible for instantiation and initialization repository.
     *
     * @param localPath
     * @return boolean
     */
    @WebMethod
    public boolean start(@WebParam(name = "localPath") String localPath);

    /**
     * Web service responsible for adding files in a repository.
     *
     * @param fileName
     * @return boolean
     */
    @WebMethod
    public boolean addOn(@WebParam(name = "fileName") String fileName);

    /**
     * Web service responsible for removing repository files.
     *
     * @param filename
     * @return boolean
     */
    @WebMethod
    public boolean remove(@WebParam(name = "filename") String filename);

    /**
     * Web service responsible for sending changes to the repository.
     *
     * @param message
     * @return boolean
     */
    @WebMethod
    public boolean commit(@WebParam(name = "message") String message);

    /**
     * Web service responsible for creating a local copy of a remote repository.
     *
     * @param remotePath
     * @param localPath
     * @return boolean
     */
    @WebMethod
    public boolean clone(@WebParam(name = "remotePath") String remotePath, @WebParam(name = "localPath") String localPath);

    /**
     * Web service responsible for showing the status of a repository.
     *
     * @return boolean
     */
    @WebMethod
    public boolean status();

    /**
     * Web service responsible for displaying a set of logs from a repository.
     *
     * @return boolean
     */
    @WebMethod
    public boolean Logs();

    /**
     * Web service responsible for displaying all branches of a repository.
     *
     * @return boolean
     */
    @WebMethod
    public boolean Branch();

    /**
     * Web service responsible for the creation of a new branch in the repository.
     *@param nameBranch 
     * @return boolean
     */
    @WebMethod
    public boolean createBranch(@WebParam(name = "nameBranch") String nameBranch);
}
