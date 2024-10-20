package fr._42.printer.app;

import java.io.FileWriter;
import java.io.IOException;

class CharactersToDisplay{
    static void exit(String err){
        System.err.println(err);
        System.exit(-1);
    }

    static String getInfo(String [] args, String info){

        for (int i = 0; i < args.length; i++)
        {
                if (args[i].startsWith(info)) {
                String value = args[i].substring(info.length());
                if (value.length() != 1)
                    exit("Invalid argument " + value);
                return value;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        if (args.length != 2){
            exit("Invalid args!");
        }

        char CWC = getInfo(args, "--CWC=").charAt(0);
        char CBC = getInfo(args, "--CBC=").charAt(0);


        //  write these Character to a file 

        try (FileWriter fw = new FileWriter("CharactersToDisplay.txt")) {
            fw.write("CBC=" + CBC + "\n");
            fw.write("CWC=" + CWC + "\n");
        } catch (IOException e) {

            
        }
    }
}