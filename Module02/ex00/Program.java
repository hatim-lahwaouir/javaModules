import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;





class Program{
    static boolean isHex(char c){

        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F');
    }

    static Map<String, String> readSignatures(){
        Map<String, String> SigToType = new HashMap<String, String>();

        try
        {
            FileInputStream signatures = new FileInputStream("signatures.txt");
            
            int data = 0;
            while (data != -1)
            {
                StringBuilder sig = new StringBuilder();
                while ((data = signatures.read()) != -1) { // read byte-by-byte
                    char ch = (char) data;
                    if (ch == ':')
                        break; // stop reading when a newline is encountered
                    if (ch == ' ')
                        continue;
                    sig.append(ch);
                }
                StringBuilder type = new StringBuilder();
                while ((data = signatures.read()) != -1) {
                    char ch = (char) data;
                    if (ch == '\n') {
                        break; // stop reading when a newline is encountered
                    }
                    type.append(ch);
                }
                
                String key = sig.toString().trim();
                String value = type.toString().trim();

                if (key.length() == 0 || value.length() == 0)
                    break;
                SigToType.put(key, value);
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        return SigToType;
    }
    static String getHex(int n){

        String hex = "0123456789ABCDEF";
        

        char f = hex.charAt(n % 16);
        n  = n / 16;
        char s = hex.charAt(n % 16);
        return String.valueOf(s) + String.valueOf(f);
    }
    static String getSingnature(FileInputStream file){
        
        int i = 0;
        int data;
        
        StringBuilder sig = new StringBuilder();
        try{
            while (i < 5 && (data = file.read()) != -1){
                sig.append(getHex(data));
                i++;
            }
        }
        catch(IOException e){

        }
        return sig.toString();
    }

    public static void main(String [] args){

        Map<String, String> sigToType = readSignatures();

        try (Scanner scanner = new Scanner(System.in)){
            
            while (true)
            {
                System.out.printf("-> ");
                String file_path = scanner.next();
                if (file_path.equals("42"))
                    break;
                // if (! file_path.startsWith("/"))
                //     continue;
                FileInputStream file = new FileInputStream(file_path);
                String sig = getSingnature(file);
                
                String type = sigToType.get(sig);
                System.out.printf("%s\n", sig);
                if (type == null)
                    continue;

                System.out.printf("PROCESSED -> %s\n", type);
            }

        }
        catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }

    }
}