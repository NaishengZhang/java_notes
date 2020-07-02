public class Fob {
    public static void main(String[] args) {
        Fob fob = new Fob();
        System.out.println(fob.calculate(4));
    }

    private int calculate(int n) {
        if (n == 0) {
            return 1;
        }
        int count = 0;
        while (n > 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
