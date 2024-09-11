package com.example.Excercise2;
import java.util.List;
import java.util.ArrayList;

class Radar {
    public List<Double> analyzeSignal(Signal signal, int n) {
        List<Double> analyzedSignal = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            double result = 1 - (double) i / 15;
            analyzedSignal.add(result);
        }
        return analyzedSignal;
    }
}