package com.example.Excercise2;
import java.util.List;

interface Signal {
    double getAmplitude(int n);
}

class DiscreteSignal implements Signal {
    private List<Double> xk;  // Danh sách giá trị x(k)

    public DiscreteSignal(List<Double> xk) {
        this.xk = xk;
    }

    @Override
    public double getAmplitude(int n) {
        double result = 0;
        for (int k = 0; k < xk.size(); k++) {
            result += xk.get(k) * delta(n - k);
        }
        return result;
    }

    private int delta(int n) {
        return n == 0 ? 1 : 0;
    }
}