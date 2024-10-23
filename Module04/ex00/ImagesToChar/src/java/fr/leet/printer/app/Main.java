package fr.leet.printer.app;
import fr.leet.printer.logic.Logic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class Main{
    static void exit(String err){
        System.err.println(err);
        System.exit(-1);
    }

    static String getInfo(String [] args, String info){

        for (int i = 0; i < args.length; i++)
        {
                if (args[i].startsWith(info)) {
                String value = args[i].substring(info.length());
                if (value.length() == 0)
                    exit("Invalid argument  " + value);
                return value;
            }
        }
        return null;
    }


    static String handelImage(String path){
        Path imagePath = Paths.get(path).toAbsolutePath();


        if (!Files.exists(imagePath))
            exit("Image doesn't exists " + path);
        
        
        if (Files.isDirectory(imagePath))
            exit("this Path is for a directory " + path);
        if (!Files.isReadable(imagePath))
            exit("this Image can't be read " + path);
        
        return imagePath.toString();
    }

    public static void main(String [] args){
        if (args.length != 3){
            exit("Invalid args!\nExample --ImagePath=....  --CWC=.  --CBC=.\nOne Character to represent white and black color\n");
        }

        String CWC = getInfo(args, "--CWC=");
        String CBC = getInfo(args, "--CBC=");

        if (CWC.length() != 1 || CBC.length() != 1)
            exit("Invalid args!\nExample --ImagePath=....  --CWC=.  --CBC=.\nOne Character to represent white and black color\n");

        String pathFromArgs = getInfo(args, "--ImagePath=");
    
        String imagePath = handelImage(pathFromArgs);
    


        
        Logic.print(CWC.charAt(0), CBC.charAt(0), imagePath);
    }

}