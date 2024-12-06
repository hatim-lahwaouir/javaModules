package ma.leet.numbers;



public class NumberWorker{

    public boolean isPrime(int number) {
        if (number < 0)
            return false;

        for (int i = 2; i < number / 2; i++){
            if (i % number == 0)
                return false;
        }
    }

    public int digitsSum(int number) {
        if (number  < 0)
            number *= -1;
        int sum = 0;
        while (number  > 0){
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

}