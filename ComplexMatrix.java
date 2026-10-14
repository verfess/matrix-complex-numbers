import java.util.Scanner;

public class ComplexMatrix {
    private int rows;
    private int columns;
    private ComplexNumber[][] cells;

    public ComplexMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new ComplexNumber[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                this.cells[row][column] = new ComplexNumber(0, 0);
            }
        }
    }

    public ComplexMatrix(ComplexMatrix x) {
        this.rows = x.rows;
        this.columns = x.columns;
        this.cells = new ComplexNumber[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                this.cells[row][column] = new ComplexNumber(x.cells[row][column]);
            }
        }
    }

    public void set(int row, int column, ComplexNumber value) {cells[row][column] = value;}
    public int getRows() {return rows;}
    public int getColumns() {return columns;}

    public static ComplexMatrix plus(ComplexMatrix x, ComplexMatrix y) {
        ComplexMatrix result = new ComplexMatrix(x.rows, x.columns);
        for (int row = 0; row < x.rows; row++) {
            for (int column = 0; column < x.columns; column++) {
                result.set(row, column, ComplexNumber.plus(x.cells[row][column], y.cells[row][column]));
            }
        }
        return result;
    }
    public static ComplexMatrix minus(ComplexMatrix x, ComplexMatrix y) {
        ComplexMatrix result = new ComplexMatrix(x.rows, x.columns);
        for (int row = 0; row < x.rows; row++) {
            for (int column = 0; column < x.columns; column++) {
                result.set(row, column, ComplexNumber.minus(x.cells[row][column], y.cells[row][column]));
            }
        }
        return result;
    }

    public static ComplexMatrix multiply(ComplexMatrix x, ComplexMatrix y) {
        if (x.columns != y.rows) throw new IllegalArgumentException("Неподходящие размеры");
        ComplexMatrix result = new ComplexMatrix(x.rows, y.columns);
        for(int x_row = 0; x_row < x.rows; x_row++) {
            for(int y_column = 0; y_column < y.columns; y_column++) {
                ComplexNumber value_of_cell = new ComplexNumber(0, 0);
                for(int index = 0; index < x.columns; index++) {
                    value_of_cell = ComplexNumber.plus(value_of_cell, ComplexNumber.multiply(x.cells[x_row][index], y.cells[index][y_column]));
                }
                result.set(x_row, y_column, value_of_cell);
            }
        }
        return result;
    }

    public static ComplexMatrix divide(ComplexMatrix x, ComplexMatrix y) {
        return multiply(x, inverse(y));
    }

    public static ComplexMatrix transposition(ComplexMatrix x) {
        ComplexMatrix result = new ComplexMatrix(x.columns, x.rows);
        for (int row = 0; row < x.rows; row++) {
            for (int column = 0; column < x.columns; column++) {
                result.cells[column][row] = x.cells[row][column];
            }
        }
        return result;
    }

    public static ComplexNumber determinant(ComplexMatrix x) {
        if(x.rows!=x.columns) {throw new IllegalArgumentException("Матрица не квадратная");}
        ComplexNumber result = new ComplexNumber(1, 0);
        ComplexMatrix y = new ComplexMatrix(x);
        for (int column = 0; column < x.columns; column++) {
            boolean flag = false;
            for (int special = column; special < x.rows; special++) {
                if(!ComplexNumber.isZero(y.cells[special][column])) {
                    if (special != column) {
                        ComplexNumber[] row_change = y.cells[column];
                        y.cells[column] = y.cells[special];
                        y.cells[special] = row_change;
                        result = ComplexNumber.multiply(result, new ComplexNumber(-1, 0));
                    }
                    flag = true;
                    break;
                }
            }
            if(!flag) {result = new ComplexNumber(0, 0); break;}
            for (int row = column + 1; row < x.rows; row++) {
                if(!ComplexNumber.isZero(y.cells[row][column])) {
                    ComplexNumber divide_result = ComplexNumber.divide(y.cells[row][column], y.cells[column][column]);
                    for (int index = column; index < x.columns; index++) {
                        y.cells[row][index] = ComplexNumber.minus(y.cells[row][index], ComplexNumber.multiply(y.cells[column][index], divide_result));
                    }
                }
            }
            result = ComplexNumber.multiply(result, y.cells[column][column]);
        }
        return result;
    }

    public static ComplexMatrix inverse(ComplexMatrix x) {
        if(x.rows!=x.columns) {throw new IllegalArgumentException("Матрица не квадратная");}
        ComplexMatrix result = new ComplexMatrix(x.rows, x.columns);
        for (int row = 0; row < x.rows; row++) {
            for (int column = 0; column < x.columns; column++) {
                if(row == column) {result.cells[row][column] = new ComplexNumber(1, 0);}
            }
        }
        ComplexMatrix y = new ComplexMatrix(x);
        for (int column = 0; column < x.columns; column++) {
            boolean flag = false;
            for (int special = column; special < x.rows; special++) {
                if(!ComplexNumber.isZero(y.cells[special][column])) {
                    if (special != column) {
                        ComplexNumber[] row_change = y.cells[column];
                        y.cells[column] = y.cells[special];
                        y.cells[special] = row_change;
                        row_change = result.cells[column];
                        result.cells[column] = result.cells[special];
                        result.cells[special] = row_change;
                    }
                    flag = true;
                    break;
                }
            }
            if (!flag) throw new ArithmeticException("Матрица вырожденная");
            ComplexNumber special = y.cells[column][column];
            for (int index = 0; index < x.columns; index++) {
                y.cells[column][index] = ComplexNumber.divide(y.cells[column][index], special);
                result.cells[column][index] = ComplexNumber.divide(result.cells[column][index], special);
            }
            for (int row = 0; row < x.rows; row++) {
                if(row!=column && !ComplexNumber.isZero(y.cells[row][column])) {
                    special = y.cells[row][column];
                    for (int index = 0; index < x.columns; index++) {
                        y.cells[row][index] = ComplexNumber.minus(y.cells[row][index], ComplexNumber.multiply(special, y.cells[column][index]));
                        result.cells[row][index] = ComplexNumber.minus(result.cells[row][index], ComplexNumber.multiply(special, result.cells[column][index]));
                    }
                }
            }
        }

        return result;
    }

    public static ComplexMatrix readMatrix(Scanner sc, String order_text) {
        System.out.println("Введите для матрицы " + order_text);
        int rows;
        while(true) {
            System.out.print("Кол-во строк: ");
            try {
                rows = Integer.parseInt(sc.nextLine().trim());
                if(rows<=0) throw new NumberFormatException();
                break;

            } catch (NumberFormatException e) {
                System.out.println("Нужно целое положительное число.");
            }
        }
        int columns;
        while(true) {
            System.out.print("Кол-во столбцов: ");
            try {
                columns = Integer.parseInt(sc.nextLine().trim());
                if(columns<=0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Нужно целое положительное число.");
            }
        }
        ComplexMatrix result = new ComplexMatrix(rows, columns);
        System.out.println("Введите действительную и мнимую часть через пробел.");
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                while(true) {
                System.out.print("[" + row + "][" + column  + "]= ");
                String[] split_numbers = sc.nextLine().trim().split(" ");
                if (split_numbers.length != 2) {
                    System.out.println("Нужны два числа через пробел (действительная и мнимая часть).");
                    continue;
                }
                try {
                    double real = Double.parseDouble(split_numbers[0]);
                    double imaginary = Double.parseDouble(split_numbers[1]);
                    result.set(row, column, new ComplexNumber(real, imaginary));
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Нужны два числа через пробел (действительная и мнимая часть).");
                    }
                }
            }
        }

        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (column > 0) {result.append("\t");}
                result.append(cells[row][column]);
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}
