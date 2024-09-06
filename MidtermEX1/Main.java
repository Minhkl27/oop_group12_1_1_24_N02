package MidtermEX1;
    public class Main {
        public static void main(String[] args) {
            DiscreteSignal signal = new DiscreteSignal();
            signal.setAmplitude(5.0);
            signal.setFrequency(1000.0);
            signal.setPeriod(0.001);
            signal.setWavelength(0.3);
    
            signal.displaySignalInfo();
        }
    }

