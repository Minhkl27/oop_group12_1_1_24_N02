package cau2;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> xk = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);

        DiscreteSignal discreteSignal = new DiscreteSignal(xk);

        Radar radar = new Radar();
        List<Double> analyzedSignal = radar.analyzeSignal(discreteSignal, 20);
        System.out.println("Kết quả phân tích tín hiệu:");
        for (int i = 0; i < analyzedSignal.size(); i++) {
            System.out.println("x(" + i + ") = " + analyzedSignal.get(i));
        }
    }
}