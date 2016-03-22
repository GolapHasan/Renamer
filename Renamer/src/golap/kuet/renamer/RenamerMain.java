package golap.kuet.renamer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


//This is second commit
//Golap Hasan by 23-Mar-2016

public class RenamerMain  extends JFrame
{
	String choosertitle;
	File fileList[];
	
	boolean FILESELECTED;
	File previousDirectory = null;
	JFileChooser fileChooser;
	public RenamerMain() {
		// TODO Auto-generated constructor stub
		
		super();
	    Container pane = getContentPane();
	    //pane.setLayout(new FlowLayout(FlowLayout.CENTER));
	    pane.setLayout(new GridLayout(3, 20, 30, 30));
	    
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		Font font = new Font("", Font.PLAIN, 20);
		
		
		
		//Choose files for renaming
		
        chooseFiles = new JButton("Choose Files");
        //chooseFiles.setFont(font);
        chooseFiles.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Please select file(s)");
				fileChooser.setCurrentDirectory(previousDirectory);
				
				//File f = new File("Desktop");
				//fileChooser.setCurrentDirectory(f);
				fileChooser.setMultiSelectionEnabled(true);
				int returnValue = fileChooser.showOpenDialog(chooseFiles);
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		        	
		        	File directory[] = fileChooser.getSelectedFiles();
		        	System.out.println(directory[0].getName());
		        	fileList = directory;
		        	
		        	previousDirectory = directory[0];
		        	
		        	FILESELECTED = true;
		        	
		        	//File fileList[] = directory.listFiles();
		    		
		        	for(int i=0; i<directory.length; i++){		    			
		    			System.out.println("Absolute path: " + directory[i].getAbsolutePath());
		    			
		    			String absoluteFilePath = directory[i].getAbsolutePath();
		    			
		    			System.out.println("File name: " + directory[i].getName());
		    			
		    			Filename fileInfo = new Filename(directory[i].getAbsolutePath(), '/', '.');
		    			
		    			System.out.println("File extension: " + fileInfo.extension());
		    			
		    			String parentDir = absoluteFilePath.substring(0,
		    					                absoluteFilePath.lastIndexOf(File.separator));
		    			
		    			System.out.println("path: " + parentDir);
		    			
		    			
		    			/*renaming the file*/
		    			/*File oldName = new File(directory[i].getAbsolutePath());
					      File newName = new File(fileInfo.path());
					      if(oldName.renameTo(newName)) {
					         System.out.println("renamed");
					      } else {
					         System.out.println("Error");
					      }*/
		        	}
		        }
		        else
		        {
		        	File fileList[] = fileChooser.getSelectedFiles();
		    		for(int i=0; i<fileList.length; i++)
		    			System.out.println(fileList[i]);
		        }
		        
		        fileChooser.updateUI();
			}
		});
        
        
        toUpperCase = new JButton("To Upper Case (ABCDEF)");
        toUpperCase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!FILESELECTED)
				{
					JOptionPane.showMessageDialog(null, "No file is selected. Please select file(s) to rename.");
					return;
				}
				else
				{
					String fileNamesTemp = "";
					for(int i=0; i<fileList.length; i++)
					{
						fileNamesTemp += (i+1) + ". " + fileList[i].getName() + "\n";
						if(i==15)
						{
							fileNamesTemp += "..." + "\n";
							break;
						}
					}
					int selection = JOptionPane.showConfirmDialog(null, "Do you sure to Upper Case the following file(s):\n\n" + fileNamesTemp + "\n");

					if(selection != 0)
						return;
					
					//System.out.println("your renaming choice in uppercase: " + selection);
					//return;
				}
				boolean renameFlag = true;
				for(int i=0; i<fileList.length; i++)
				{
					Filename fileInfo = new Filename(fileList[i].getAbsolutePath(), '/', '.');
	    			
					File oldName = new File(fileList[i].getAbsoluteFile().toString());
					String name = fileList[i].getName();
					
					String absoluteFilePath = fileList[i].getAbsolutePath();
					String parentDir = absoluteFilePath.substring(0,
			                absoluteFilePath.lastIndexOf(File.separator));
	
				      File newName = new File(parentDir + "\\" + name.toUpperCase());
				      if(oldName.renameTo(newName)) {
				         System.out.println("renamed");
				         renameFlag = true;
				      } else {
				         System.out.println("Error");
				         renameFlag = false;
				      }
				      
				      oldName = newName = null;
				}	
				if(renameFlag)
				{
					JOptionPane.showMessageDialog(null, "Renamed successfully all the files you have selected!");
				}
				else JOptionPane.showMessageDialog(null, "Sorry sir! Rename all the files you have selected not successfully done!");
				
				FILESELECTED = false;
			}
		});
        
        toLowerCase = new JButton("To Lower Case (abcdef)");
        toLowerCase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(!FILESELECTED)
				{
					JOptionPane.showMessageDialog(null, "No file is selected. Please select file(s) to rename.");
					return;
				}
				else
				{
					String fileNamesTemp = "";
					for(int i=0; i<fileList.length; i++)
					{
						fileNamesTemp += (i+1) + ". " + fileList[i].getName() + "\n";
						if(i==15)
						{
							fileNamesTemp += "..." + "\n";
							break;
						}
					}
					int selection = JOptionPane.showConfirmDialog(null, "Do you sure to Lower Case the following file(s):\n\n" + fileNamesTemp + "\n");

					if(selection != 0)
						return;
				}
					
				
				boolean renameFlag = true;
				for(int i=0; i<fileList.length; i++)
				{
					Filename fileInfo = new Filename(fileList[i].getAbsolutePath(), '/', '.');
	    			
					File oldName = new File(fileList[i].getAbsoluteFile().toString());
					String name = fileList[i].getName();
					
					String absoluteFilePath = fileList[i].getAbsolutePath();
					String parentDir = absoluteFilePath.substring(0,
			                absoluteFilePath.lastIndexOf(File.separator));
	
				      File newName = new File(parentDir + "\\" + name.toLowerCase());
				      if(oldName.renameTo(newName)) {
				         System.out.println("renamed");
				         renameFlag = true;
				      } else {
				         System.out.println("Error");
				         renameFlag = false;
				      }
				}	
				if(renameFlag)
					JOptionPane.showMessageDialog(null, "Renamed successfully all the files you have selected!");
				else JOptionPane.showMessageDialog(null, "Sorry sir! Rename all the files you have selected not successfully done!");
				
				FILESELECTED = false;
			}
		});
        
        addPrefix = new JButton("Add Prefix (golap + name.jpg)");
        addPrefix.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(!FILESELECTED)
				{
					JOptionPane.showMessageDialog(null, "No file is selected. Please select file(s) to rename.");
					return;
				}
				else
				{
					String fileNamesTemp = "";
					for(int i=0; i<fileList.length; i++)
					{
						fileNamesTemp += (i+1) + ". " + fileList[i].getName() + "\n";
						if(i==15)
						{
							fileNamesTemp += "..." + "\n";
							break;
						}
					}
					int selection = JOptionPane.showConfirmDialog(null, "Do you sure to Lower Case the following file(s):\n\n" + fileNamesTemp + "\n");

					if(selection != 0)
						return;
				}
					
				
				boolean renameFlag = true;
				for(int i=0; i<fileList.length; i++)
				{
					Filename fileInfo = new Filename(fileList[i].getAbsolutePath(), '/', '.');
	    			
					File oldName = new File(fileList[i].getAbsoluteFile().toString());
					String name = fileList[i].getName();
					name = name.substring(0, name.length());
					
					name = name.substring(0, name.length()-4);
					
					String absoluteFilePath = fileList[i].getAbsolutePath();
					String parentDir = absoluteFilePath.substring(0,
			                absoluteFilePath.lastIndexOf(File.separator));
	
				      File newName = new File(parentDir + "\\" + name+".gif");
				      if(oldName.renameTo(newName)) {
				         System.out.println("renamed");
				         renameFlag = true;
				      } else {
				         System.out.println("Error");
				         renameFlag = false;
				      }
				}	
				if(renameFlag)
					JOptionPane.showMessageDialog(null, "Renamed successfully all the files you have selected!");
				else JOptionPane.showMessageDialog(null, "Sorry sir! Rename all the files you have selected not successfully done!");
				
				FILESELECTED = false;
			}
		});
        
       /* mainFrame = new JFrame("Renamer by Golap");
        mainFrame.setSize(500, 500);
        mainFrame.add(chooseFiles, BorderLayout.EAST);
        mainFrame.add(toLowerCase, BorderLayout.WEST);
        mainFrame.add(toUpperCase, BorderLayout.WEST);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);*/
        pane.add(chooseFiles);
        pane.add(toUpperCase);
        pane.add(toLowerCase);
        pane.add(addPrefix);
        
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RenamerMain ft = new RenamerMain();
        ft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ft.setTitle("Golap File Renamer");
        ft.setSize(400, 300);
        ft.setLocationRelativeTo(null);        
        ft.setVisible(true);
	}

	JFrame mainFrame;
	JButton chooseFiles, toUpperCase, toLowerCase, addPrefix;
	
	class Filename {
		  private String fullPath;
		  private char pathSeparator, extensionSeparator;

		  public Filename(String str, char sep, char ext) {
		    fullPath = str;
		    pathSeparator = sep;
		    extensionSeparator = ext;
		  }

		  public String extension() {
		    int dot = fullPath.lastIndexOf(extensionSeparator);
		    return fullPath.substring(dot + 1);
		  }

		  public String filename() { // gets filename without extension
		    int dot = fullPath.lastIndexOf(extensionSeparator);
		    int sep = fullPath.lastIndexOf(pathSeparator);
		    return fullPath.substring(sep + 1, dot);
		  }

		  public String path() {
		    int sep = fullPath.lastIndexOf(pathSeparator);
		    return fullPath.substring(0, sep);
		  }
		}
}


