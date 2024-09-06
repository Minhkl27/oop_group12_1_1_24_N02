/*1. */
public class DiscreteSignal {
    private int[] signalValues;

    public DiscreteSignal(int[] values) {
        this.signalValues = values;
    }

     /*  Hàm để tính toán giá trị của x(n) dựa trên hàm delta */
    public int calculateSignal(int n) {
        int result = 0;
        for (int k = 0; k < signalValues.length; k++) {
            if (n - k == 0) {
                result += signalValues[k];
            }
        }
        return result;
    }
}

/*2.  */
public class Radar {      /*khai báo lớp Radar */
    public double calculateSignal(int n) {
        if (n >= 0 && n <= 15) {            
            return 1 - (n / 15.0);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Radar radar = new Radar();
        System.out.println("X(4) = " + radar.calculateSignal(4));  /*  In ra kết quả cho n = 4*/
    }
}