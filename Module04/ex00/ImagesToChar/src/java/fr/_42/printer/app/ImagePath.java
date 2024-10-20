package fr._42.printer.app;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ImagePath{
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


    public static void main(String [] args){
        if (args.length != 1){
            exit("Invalid args!");
        }

        String path = getInfo(args, "--ImagePath=");
        Path imagePath = Paths.get(path).toAbsolutePath();


        if (!Files.exists(imagePath))
            exit("Image doesn't exists " + path);
        
        
        if (Files.isDirectory(imagePath))
            exit("this Path is for a directory " + path);
        if (!Files.isReadable(imagePath))
            exit("this Image can't be read " + path);


        try (FileWriter fw = new FileWriter("ImagePath.txt")) {
            fw.write("ImagePath=" + imagePath.toString() + "\n");
        } catch (IOException e) {
            exit("Failed to create File  ImagePath.txt");
        }

    }

}