package MidtermEX1;

// Cài đặt một lớp cụ thể cài đặt interface Signal
public class DiscreteSignal implements Signal {
    private double amplitude;
    private double frequency;
    private double period;
    private double wavelength;

    @Override
    public double getAmplitude() {
        return amplitude;
    }

    @Override
    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    @Override
    public double getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    @Override
    public double getPeriod() {
        return period;
    }

    @Override
    public void setPeriod(double period) {
        this.period = period;
    }

    @Override
    public double getWavelength() {
        return wavelength;
    }

    @Override
    public void setWavelength(double wavelength) {
        this.wavelength = wavelength;
    }

    // Ví dụ thêm một phương thức để hiển thị thông tin của tín hiệu
    public void displaySignalInfo() {
        System.out.println("Amplitude: " + amplitude);
        System.out.println("Frequency: " + frequency);
        System.out.println("Period: " + period);
        System.out.println("Wavelength: " + wavelength);
    }
}

