public class Program {

  public static int countDigits(int number){
    int res = 0;
    while (number > 0){
      res += number % 10;
      number /= 10;
    }
    return res;
  }

  public static void main(String[] args) {
    int number = 479598;

    int res = countDigits(number);
    System.out.println(res);
  }
}


