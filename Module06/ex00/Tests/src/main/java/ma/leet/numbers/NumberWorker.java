package ma.leet.numbers;



public class NumberWorker{



    public boolean isPrime(int number) {

        if (number < 0 || number % 2 == 0)
            return false;

        for (int i = 2; i < number / 2; i++){

            if (number % i == 0)
                return false;
        }

        return true;
    }




    public int digitsSum(int number) {
        int res = 0;

        if (number  < 0)
            number *=  -1;


        while (number > 0){

            res += number % 10;
            number /= 10;
        }    

        return res;
    }

}