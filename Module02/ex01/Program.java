import java.io.BufferedReader;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.lang.Math;

class Program{


    static Map<String, Integer> frequencyOfWords(BufferedReader reader) throws IOException{
        Map<String, Integer> wordsFrequency =  new TreeMap<String, Integer>();
        StringBuilder word = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null){
            int i = 0;
            int index;
            boolean last = false;
            while (true){
                index = line.indexOf(' ', i);
                if (index == -1)
                {
                    last = true;
                    index = line.length();
                }
                while (i < index)
                {
                    word.append(line.charAt(i));
                    i++;
                }
                String w = word.toString();
                if (w.length() != 0)
                {
                    wordsFrequency.get(w);
                    wordsFrequency.put(w, wordsFrequency.getOrDefault(w, 0) + 1);
                }
                word.setLength(0);
                if (last)
                    break;
                i++;
            }
        }

        return wordsFrequency;
    }

    static void create_vect(Map<String, Integer> mp, Set<String> words)
    {
        for (String word : words){
            if (mp.getOrDefault(word, null) == null)
                mp.put(word, 0);
        }
    }

    static int numerator(Map<String, Integer> first, Map<String, Integer> second, Set<String> words)
    {
        System.out.printf("Numerator A. B = (");
        boolean fOp = false;
        int res = 0;
        for (String word : words){
            int f = first.getOrDefault(word, 0);
            int s = second.getOrDefault(word, 0);
            if (fOp){
                System.out.printf(" + ");
            }

            res += f * s;

            System.out.printf("%d * %d", f, s);
            fOp = true;
        }
        System.out.printf(") = %d\n", res);

        return res;
    }

    static  int denominator(Map<String, Integer> vect){

        boolean fOp = false;
        int result = 0;
        System.out.printf("sqrt(");
        for (String element  : vect.keySet()){
            if(fOp){
                System.out.printf(" + ");
            }
            int value = vect.get(element);
            result +=  value * value;
            System.out.printf("%d * %d", value, value);
            fOp = true;
        }
        System.out.printf(")");
        return result;
    }
    public static void main(String [] args)throws IOException {
        if (args.length != 2)
        {
            System.err.println("Invalid arguments!");
            System.exit(-1);
        }
        BufferedReader FirstFile = new BufferedReader(new FileReader(args[0]));
        BufferedReader SecondFile = new BufferedReader(new FileReader(args[1]));
        Set <String> words = new TreeSet<String>();

        Map<String, Integer> fristFileWords =  frequencyOfWords(FirstFile);
        Map<String, Integer> secondFileWords =  frequencyOfWords(SecondFile);
        
        words.addAll(fristFileWords.keySet());
        words.addAll(secondFileWords.keySet());
 
        create_vect(secondFileWords, words);
        create_vect(fristFileWords, words);
        int num = numerator(fristFileWords, secondFileWords, words);
        System.out.printf("Denominator ||A|| * ||B|| = ");
        int f = denominator(fristFileWords);
        System.out.printf(" * ");
        int s = denominator(secondFileWords);

        double sqrtf = Math.sqrt((double)f);
        double sqrts = Math.sqrt((double)s);
        System.out.printf(" = sqrt(%d) * sqrt(%d) = %.2f * %.2f = %.2f\n", f, s,sqrtf, sqrts, sqrtf * sqrts);

        System.out.printf("similarity = %d / %.2f = %.2f\n", num, sqrtf * sqrts, (double)num / (sqrtf * sqrts));
    }



}