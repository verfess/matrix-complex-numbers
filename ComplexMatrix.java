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

    public void set(int row, int column, ComplexNumber value) {cells[row][column] = value;}
    public int getRows() {return rows;}
    public int getColumns() {return columns;}
}
