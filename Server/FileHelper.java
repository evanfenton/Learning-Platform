package Server;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import SharedDataObjects.Assignment;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.Submission;

import java.io.*;
import java.sql.Struct;

/**
 * Helps server receive files
 * @author Evan Mcphee
 *
 */
public class FileHelper {
	/**
	 * path to send file in sever.
	 */
    private String serverfilepath;

    /**
     * Ctor, sets the path on the server for all files
     */
    public FileHelper(){
        serverfilepath = "D:\\Users\\Gibson\\Desktop\\Project\\"; //Change this to your file path used to save server files
    }

    /**
     * writes assignment file to serverfilepath location
     * @param input
     * @param message
     */
    public void uploadFile(byte[] input, String message){
        String [] splitmessage = message.split("str-1splitter");
        System.out.println(splitmessage[0]);
        System.out.println(splitmessage[1]);
        String [] fileinfo = splitmessage[1].split("\\.(?=[^\\.]+$)");
        String fileName = fileinfo[0];
        String fileExtension = ".".concat(fileinfo[1]);
        File newFile = new File(serverfilepath + fileName + fileExtension);
        try{
            if(!newFile.exists())
                newFile.createNewFile();
            FileOutputStream writer = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(writer);
            bos.write(input);
            bos.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * writes submission file to serverfilepath location
     * @param input
     * @param message
     */
    public void uploadSubmission(byte [] input, String message){
        String [] splitmessage = message.split("str-1splitter");
        System.out.println(splitmessage[0]);
        System.out.println(splitmessage[1]);
        String [] fileinfo = splitmessage[1].split("\\.(?=[^\\.]+$)");
        String fileName = fileinfo[0];
        String fileExtension = ".".concat(fileinfo[1]);
        File newFile = new File(serverfilepath + fileName + fileExtension);
        try{
            if(!newFile.exists())
                newFile.createNewFile();
            FileOutputStream writer = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(writer);
            bos.write(input);
            bos.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * gets the file associated with the passed Assignment argument and returns it inside a server message
     * @param assignment
     * @return
     */
    public ServerMessage getAssignmentFile(Assignment assignment){
        File filetosend = new File(serverfilepath+assignment.getTitle()+assignment.getPath());
        long length = filetosend.length();
        byte[] content = new byte[(int) length]; // Converting Long to Int
        try {
            FileInputStream fis = new FileInputStream(filetosend);
            BufferedInputStream bos = new BufferedInputStream(fis);
            bos.read(content, 0, (int)length);
        } catch (FileNotFoundException g) {
            g.printStackTrace();
        } catch(IOException f){
            f.printStackTrace();
        }
        ServerMessage returnmessage = new ServerMessage(content,assignment.getTitle()+assignment.getPath());
        return returnmessage;
    }

    /**
     * gets the file associated with the passed Submission argument and returns it in a server message
     * @param submission
     * @return
     */
	public ServerMessage getSubmissionFile(Submission submission) {
		File filetosend = new File(serverfilepath+ submission.getTitle()+submission.getPath());
        long length = filetosend.length();
        byte[] content = new byte[(int) length]; // Converting Long to Int
        try {
            FileInputStream fis = new FileInputStream(filetosend);
            BufferedInputStream bos = new BufferedInputStream(fis);
            bos.read(content, 0, (int)length);
        } catch (FileNotFoundException g) {
            g.printStackTrace();
        } catch(IOException f){
            f.printStackTrace();
        }
        ServerMessage returnmessage = new ServerMessage(content,submission.getTitle()+submission.getPath());
        return returnmessage;
	}
}
