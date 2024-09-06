public class ClassExample {
    // Thuộc tính
    int modelYear;
    String modelName;

    // Constructor
    public ClassExample(int year, String name) {
        modelYear = year;
        modelName = name;
    }

    public static void main(String[] args) {
        // Tạo đối tượng từ lớp Car
        ClassExample myCar = new ClassExample(1969, "Mustang");
        System.out.println(myCar.modelYear + " " + myCar.modelName);
    }
}
