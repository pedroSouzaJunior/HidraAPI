package command;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

//import java.io.File;

/**
 * @author Classe responsavel por escolher o caminho do repositorio local
 *
 */
public class ChoosePath extends JPanel implements ActionListener {
	/**
	 * variavel que fui obriga a por, me parece que esse
	 */
	private static final long serialVersionUID = 1L;

	JButton go;

	JFileChooser chooser;

	String choosertitle;

	private String pathDirectory;

	/**
	 * Construtor
	 */
	public ChoosePath() {
		go = new JButton("Do it"); //$NON-NLS-1$
		go.addActionListener(this);
		add(go);
	}

	public void actionPerformed(ActionEvent e) {

		chooser = new JFileChooser();
		//chooser.setCurrentDirectory(new java.io.File(".")); //$NON-NLS-1$
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//
		// disable the "All files" option.
		//
		chooser.setAcceptAllFileFilterUsed(false);
		//
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			//System.out.println("getCurrentDirectory(): " //$NON-NLS-1$
			// + chooser.getCurrentDirectory());

			setPathDirectory(chooser.getCurrentDirectory().getPath());

			//System.out.println("getSelectedFile() : " //$NON-NLS-1$
			// + chooser.getSelectedFile());
		} else {
			System.out.println("No Selection "); //$NON-NLS-1$
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	/**
	 * @return pathDirectory
	 */
	public String getPathDirectory() {
		return pathDirectory;
	}

	/**
	 * @param pathDirectory
	 */
	public void setPathDirectory(String pathDirectory) {
		this.pathDirectory = pathDirectory;
	}

}