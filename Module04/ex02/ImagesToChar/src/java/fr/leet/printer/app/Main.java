package fr.leet.printer.app;
import fr.leet.printer.logic.Logic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




// jcomander 
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.JCommander;




@Parameters(separators = "=")
class Main{
  
    @Parameter(names = "--white")
        String white;
  
    @Parameter(names = "--black")
        String black;


    static void exit(String err){
        System.err.println(err);
        System.exit(-1);
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

    public static void main(String [] argv){
        if (argv.length != 2){
            exit("Invalid args!\nExample  --white=RED --black=GREEN\n");
        }

        Main main = new Main();
        try{
            JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);

        }
        catch(Exception e){
            System.out.println(e);
        }
        main.run();

    }
    public void run() {
        String imagePath = handelImage("target/resources/image.bmp");


        Logic.print(this.white, this.black, imagePath);
    }
}