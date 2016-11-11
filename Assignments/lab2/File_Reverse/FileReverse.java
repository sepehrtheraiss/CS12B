//---------------------------------------------
//Name: Sepehr Raissian 
//Cruzid: sraissia
//class: 12M/B
//Due date: 10/7/16
//lab#2
//FileReverse.java
// This program will take in a file and output the each word in reverse and eliminate any white space and instead print the words on the next line using a recusrive function called writebackward
//---------------------------------------------
import java.io.*;
import java.util.Scanner;
public class FileReverse {
    public static void main(String[] args) throws IOException{
        int lineNumber = 0;
        
        //check number of command line arguments is at least 2
        if(args.length < 2){
            System.out.println("Usage: FileCopy <input file> <output file>");
            System.exit(1);
        }//end if
        // open files
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        
        // read lines from in, extract and print tokens from each line
        while(in.hasNextLine()){
            lineNumber++;
            
            // trim leading and trailing spaces, then add one trailing space so
            // split works on blank lines
            String line = in.nextLine().trim() + " ";
            
            //split line around white space
            String[] token = line.split("\\s+");
            
            //print out tokens
            int n = token.length;
            for(int i=0;i<n;i++){
                out.println(writeBackward(token[i]));
            }//end for
        }//end while
        // close files
        in.close();
        out.close();
    }
     static String writeBackward(String s){
        if(s.length() > 0){
            return writeBackward(s.substring(1)) + s.charAt(0);
        }
        else return "";
    }
}
