package net.ledes.hidra.services;

import java.io.File;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.ledes.hidra.sources.Command;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

@WebService(serviceName = "HidraControl/Command")
public class HidraServices {

	Command commands = new Command();

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

	@WebMethod
	public boolean addOn(@WebParam(name = "fileName") String fileName){
		boolean ret = false;
		
		try {
			commands.adicionar(fileName);
			ret = true;
		} catch (Exception e) {
			System.err.println("Error adding file");
		}
		return ret;
		
	}
	
	@WebMethod
	public boolean remove(@WebParam(name = "filename") String filename){
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
	public boolean commit(@WebParam(name = "message") String message){
		boolean ret = false;
		
		try {
			commands.commit(message);
			ret = true;
		} catch (Exception e) {
			System.err.println("Error during commit process");
		}
		return ret;
		
	}
}
