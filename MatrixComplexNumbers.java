import java.util.Scanner;

public class MatrixComplexNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        System.out.println("Работа с матрицами комплексных чисел.");
        while(flag) {
            menu();
            String input = sc.nextLine().trim();
            if(input.equals("help")) {
                continue;
            }
            int number;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Неизвестная команда (help для повтора меню)");
                continue;
            }
            try {
                switch (number) {
                    case 0: {
                            System.out.println("Завершение работы");
                            flag = false;
                            break;
                    }
                    case 1: {
                        ComplexMatrix a = ComplexMatrix.readMatrix(sc, "A");
                        ComplexMatrix b = ComplexMatrix.readMatrix(sc, "B");
                        System.out.println("A + B =\n" + ComplexMatrix.plus(a, b));
                        break;
                    }
                    case 2: {
                        ComplexMatrix a = ComplexMatrix.readMatrix(sc, "A");
                        ComplexMatrix b = ComplexMatrix.readMatrix(sc, "B");
                        System.out.println("A - B =\n" + ComplexMatrix.minus(a, b));
                        break;
                    }
                    case 3: {
                        ComplexMatrix a = ComplexMatrix.readMatrix(sc, "A");
                        ComplexMatrix b = ComplexMatrix.readMatrix(sc, "B");
                        System.out.println("A * B =\n" + ComplexMatrix.multiply(a, b));
                        break;
                    }
                    case 4: {
                        ComplexMatrix a = ComplexMatrix.readMatrix(sc, "A");
                        ComplexMatrix b = ComplexMatrix.readMatrix(sc, "B");
                        System.out.println("A / B =\n" + ComplexMatrix.divide(a, b));
                        break;
                    }
                    case 5: {
                        ComplexMatrix a = ComplexMatrix.readMatrix(sc, "A");
                        System.out.println("T(A) =\n" + ComplexMatrix.transposition(a));
                        break;
                    }
                    case 6: {
                        ComplexMatrix a = ComplexMatrix.readMatrix(sc, "A");
                        System.out.println("|A| = " + ComplexMatrix.determinant(a));
                        break;
                    }
                    case 7: {
                        ComplexMatrix a = ComplexMatrix.readMatrix(sc, "A");
                        System.out.println("A^-1 = \n" + ComplexMatrix.inverse(a));
                        break;
                    }
                    default: {
                        System.out.println("Неизвестная команда (help для повтора меню)");
                    }
                }
            } catch (IllegalArgumentException| ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        sc.close();
    }

    public static void menu() {
        System.out.println(
            "Выберите соответствующее число (help для повтора меню):\n" + 
            "1) Сложить матрицы\n" + 
            "2) Вычесть матрицы\n" + 
            "3) Умножить матрицы\n" + 
            "4) Разделить матрицы\n" + 
            "5) Транспонировать матрицу\n" + 
            "6) Вычислить определитель\n" + 
            "7) Вычислить обратную матрицу\n" + 
            "0) Завершить работу");
    }
}
