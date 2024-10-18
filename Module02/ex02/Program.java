import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.Scanner;

class Program{

    static void exit(String err){
        System.err.println(err);
        System.exit(-1);
    }

    static Path getCheckPath(String [] args) {
        String [] current_folder = args[0].split("=", 2);
        if (current_folder.length != 2 || !current_folder[1].startsWith("/"))
            exit("Argument must be an absolute path");

        Path path = Paths.get(current_folder[1]);
        if (!Files.isDirectory(path))
            exit("Path isn't a directory");
        if (!Files.isReadable(path))
            exit("Can't read what's inside the directory ");
        if (!Files.isWritable(path))
            exit("Can't write inside the directory");
    
        System.out.println(current_folder[1]);
        return path;
    }

    static void ls(Path current_path){
        
        try{
            Stream<Path> paths = Files.list(current_path);
            paths.forEach(path -> {
                try{
                    System.out.printf("%s %d KB\n",path.getFileName(), Files.size(path) / 1000);
                }
                catch (IOException e){
                    paths.close();
                    exit("An IOExcetpion !");
                }
            });
            paths.close();
        }
        catch (IOException e){
            exit("An IOExcetpion !");
        }
    }


    static void mv(Path current_path, Scanner scanner){
        String src = scanner.next();
        String dest = scanner.next();
    
        Path srcPath = current_path.resolve(src);  // Append src to current_path
        Path destPath = current_path.resolve(dest); // Append dest to current_path

        try {
            // Move the source to the destination
            Files.move(srcPath, destPath);
        } catch (IOException e) {
            System.out.println("Error moving file: " + e.getMessage());
        }
    
    }

    static Path cd(Path current_path, Scanner scanner){
        String new_path = scanner.next();

        Path newPath = current_path.resolve(new_path).normalize();


        if (!Files.isDirectory(newPath))
        {
            System.err.println("Path isn't a directory");
            return current_path;
        }
        if (!Files.isReadable(newPath))
        {
            System.err.println("Can't read what's inside the directory ");
            return current_path;
        }    
        if (!Files.isWritable(newPath))
        {
            System.err.println("Can't write inside the directory");
            return current_path;
        }
        


        return newPath.normalize();
    }
    public static void main (String [] args) throws IOException {

        if (args.length != 1)
            exit("Invalid args!");
        Path current_path = getCheckPath(args);


        try (Scanner scanner = new Scanner(System.in))
        {
            while (true){
                
                System.out.printf("-> ");
                String cmd = scanner.next();

                switch (cmd) {
                    case "ls":
                        ls(current_path);
                        break;
                    case "mv":
                        mv(current_path, scanner);
                        break;
                    case "cd":
                        current_path =  cd(current_path, scanner);
                        break;
                    case "exit":
                        System.exit(0);
                        break;

                    default:
                        System.err.println("Invalid cmd!");
                        break;
                }

            }

        }
    }


}