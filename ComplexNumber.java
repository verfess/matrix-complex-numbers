public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber(ComplexNumber x) {
        this.real = x.real;
        this.imaginary = x.imaginary;
    }

    public double getReal() {return real;}
    public double getImaginary() {return imaginary;}

    public static ComplexNumber plus(ComplexNumber x, ComplexNumber y) {
        ComplexNumber result = new ComplexNumber(x.real + y.real, x.imaginary + y.imaginary);
        return result;
    }
    public static ComplexNumber minus(ComplexNumber x, ComplexNumber y) {
        ComplexNumber result = new ComplexNumber(x.real - y.real, x.imaginary - y.imaginary);
        return result;
    }

    public static ComplexNumber multiply(ComplexNumber x, ComplexNumber y) {
        ComplexNumber result = new ComplexNumber(x.real * y.real - x.imaginary * y.imaginary, x.imaginary * y.real + x.real * y.imaginary);
        return result;
    }

    public static ComplexNumber divide(ComplexNumber x, ComplexNumber y) {
        if(isZero(y)) {throw new ArithmeticException("Деление на ноль");}
        ComplexNumber result = new ComplexNumber((x.real * y.real + x.imaginary * y.imaginary)/(Math.pow(y.real,2) + Math.pow(y.imaginary,2)), (x.imaginary * y.real - x.real * y.imaginary)/(Math.pow(y.real,2) + Math.pow(y.imaginary,2)));
        return result;
    }

    public static boolean isZero(ComplexNumber x) {
        return x.real == 0 && x.imaginary == 0;
    }
}