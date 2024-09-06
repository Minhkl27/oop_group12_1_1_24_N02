// 1. Tạo giao diện Signal
public interface Signal {
    
    double getAmplitude(); /* Biên độ của tín hiệu*/
    double getFrequency(); /* Tính thường xuyên (tần số) của tín hiệu*/
    double getPeriod();    /* Chu kỳ của tín hiệu*/
    double getWavelength(); /* Bước sóng của tín hiệu*/
}

// 2. Tạo lớp DiscreteSignal kế thừa giao diện Signal
public class DiscreteSignal implements Signal {
    private double amplitude;
    private double frequency;
    private double period;
    private double wavelength;
    private int[] values;

    public DiscreteSignal(double amplitude, double frequency, double period, double wavelength, int[] values) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.period = period;
        this.wavelength = wavelength;
        this.values=values;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getPeriod() {
        return period;
    }

    public double getWavelength() {
        return wavelength;
    }

    public double getValues() {
        return values;
    }

    public void displaySignal() {    /*phương thức hiển thị giá trị tín hiệu rời rạc*/
        System.out.println("Tín hiệu rời rạc:");
        for (int i = 0; i < values.length; i++) {
            System.out.println("x(" + i + ") = " + values[i]);
        }
    }
}
    

public class DiscreteSignal implements Signal {
    private double amplitude; /*Biên độ của tín hiệu*/
    private double frequency; /* Tần số của tín hiệu*/
    private int[] values;     /* Mảng các giá trị tín hiệu rời rạc*/

    /* Constructor để khởi tạo tín hiệu rời rạc với biên độ, tần số và các giá trị*/
    public DiscreteSignal(double amplitude, double frequency, int[] values) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.values = values;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public double getFrequency() {
        return frequency;
    }

    /* Phương thức trả về mảng các giá trị tín hiệu rời rạc*/
    public int[] getValues() {
        return values;
    }

    /*Phương thức hiển thị giá trị tín hiệu rời rạc*/
    public void displaySignal() {
        System.out.println("Tín hiệu rời rạc:");
        for (int i = 0; i < values.length; i++) {
            System.out.println("x(" + i + ") = " + values[i]);
        }
    }
}

/*3. */
public class ContinuousSignal implements Signal {
    private double amplitude;  /*Biên độ của tín hiệu liên tục*/
    private double frequency;  /* Tần số của tín hiệu liên tục*/
    private double phase;      /* Pha của tín hiệu liên tục*/

    /*Constructor để khởi tạo tín hiệu liên tục với biên độ, tần số và pha*/
    public ContinuousSignal(double amplitude, double frequency, double phase) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = phase;
    }
   
    public double getAmplitude() {
        return amplitude;
    }

    public double getFrequency() {
        return frequency;
    }
/*phương thức trả về pha của tín hiệu */
    public double getPhase() {
        return phase;
    }

    /* Phương thức để tính giá trị tín hiệu liên tục tại một thời điểm t*/
    public double calculateSignal(double t) {
        return amplitude * Math.sin(2 * Math.PI * frequency * t + phase);
    }

    /* Phương thức hiển thị thông tin tín hiệu*/
    public void displaySignal(double startTime, double endTime, double step) {
        System.out.println("Tín hiệu liên tục:");
        for (double t = startTime; t <= endTime; t += step) {
            System.out.printf("x(%.2f) = %.3f\n", t, calculateSignal(t));
        }
    }
}