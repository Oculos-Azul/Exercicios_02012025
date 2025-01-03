package arraybidimensional;

import utils.LoggerUtils;

public class CharIntMatrix {
    private final Integer[][] matrix;
    private final char[] rows;

    public CharIntMatrix(Object rowsObj, Object columns) {
        try {
            this.rows = new char[(Integer) rowsObj];
            this.matrix = new Integer[rows.length][(Integer) columns];
        } catch (ClassCastException e) {
            LoggerUtils.logError("Erro de cast no construtor: " + e.getMessage());
            throw e;
        }
    }

    public void insert(Object rowObj, int col, Object valueObj) {
        try {
            char rowChar = (char) rowObj;
            int rowIndex = getRowIndex(rowChar);
            if (rowIndex != -1 && col >= 0 && col < matrix[rowIndex].length) {
                int value = (int) valueObj;
                matrix[rowIndex][col] = value;
                LoggerUtils.logInfo("Valor " + value + " inserido na posição [" + rowChar + "][" + col + "]");
            } else {
                LoggerUtils.logError("Índice inválido na inserção: Linha " + rowChar + ", Coluna " + col);
            }
        } catch (ClassCastException e) {
            LoggerUtils.logError("Erro de cast ao inserir valor: " + e.getMessage());
        }
    }

    public Object get(Object rowObj, int col) {
        try {
            char rowChar = (char) rowObj;
            int rowIndex = getRowIndex(rowChar);
            if (rowIndex != -1 && col >= 0 && col < matrix[rowIndex].length) {
                LoggerUtils.logDebug("Valor recuperado na posição [" + rowChar + "][" + col + "]: " + matrix[rowIndex][col]);
                return matrix[rowIndex][col];
            }
        } catch (ClassCastException e) {
            LoggerUtils.logError("Erro de cast ao recuperar valor: " + e.getMessage());
        }
        return null;
    }

    public boolean remove(Object rowObj, int col) {
        try {
            char rowChar = (char) rowObj;
            int rowIndex = getRowIndex(rowChar);
            if (rowIndex != -1 && col >= 0 && col < matrix[rowIndex].length) {
                matrix[rowIndex][col] = 0;
                LoggerUtils.logInfo("Valor na posição [" + rowChar + "][" + col + "] removido (configurado como 0)");
                return true;
            }
        } catch (ClassCastException e) {
            LoggerUtils.logError("Erro de cast ao remover valor: " + e.getMessage());
        }
        return false;
    }

    public boolean contains(Object rowObj, int col) {
        try {
            char rowChar = (char) rowObj;
            int rowIndex = getRowIndex(rowChar);
            boolean contains = rowIndex != -1 && col >= 0 && col < matrix[rowIndex].length && matrix[rowIndex][col] != 0;
            if (contains) {
                LoggerUtils.logInfo("Posição [" + rowChar + "][" + col + "] contém valor diferente de 0");
            } else {
                LoggerUtils.logInfo("Posição [" + rowChar + "][" + col + "] não contém valor diferente de 0");
            }
            return contains;
        } catch (ClassCastException e) {
            LoggerUtils.logError("Erro de cast ao verificar se contém valor: " + e.getMessage());
        }
        return false;
    }

    private int getRowIndex(char row) {
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == row) {
                return i;
            }
        }
        return -1;
    }

    public void printMatrix() {
        for (int i = 0; i < rows.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Row ").append(rows[i]).append(": ");
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            LoggerUtils.logDebug(sb.toString());
        }
    }
}
