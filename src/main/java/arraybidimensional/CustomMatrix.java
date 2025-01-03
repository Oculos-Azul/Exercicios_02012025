package arraybidimensional;

import exceptions.InvalidArrayPositionException;
import exceptions.InvalidTypeException;
import utils.LoggerUtils;

public class CustomMatrix {

    private final Character[] characters;
    private final Integer[] numbers;

    public CustomMatrix(Character[] characters, Integer[] numbers) {
        this.characters = characters.clone();
        this.numbers = numbers.clone();
    }

    public void addCharacter(int index, Object value) {
        setValue(index, value, true);
    }

    public void addNumber(int index, Object value) {
        setValue(index, value, false);
    }

    public Character getCharacter(int index) {
        return getValue(index, true);
    }

    public Integer getNumber(int index) {
        return getValue(index, false);
    }

    public void removeCharacter(int index) {
        removeValue(index, true);
    }

    public void removeNumber(int index) {
        removeValue(index, false);
    }

    private <T> void setValue(int index, Object value, boolean isCharacter) {
        try {
            if (isCharacter) {
                characters[index] = (Character) value;
                LoggerUtils.logInfo("Caractere inserido na posição " + index + ": " + value);
            } else {
                numbers[index] = (Integer) value;
                LoggerUtils.logInfo("Número inserido na posição " + index + ": " + value);
            }
        } catch (ClassCastException e) {
            LoggerUtils.logError("Erro ao inserir valor no índice " + index + ": Tipo de valor inválido. " + e.getMessage());
            throw new InvalidTypeException("Tipo de valor inválido no índice " + index + ": " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            LoggerUtils.logError("Erro ao inserir valor no índice " + index + ": Índice fora dos limites. " + e.getMessage());
            throw new InvalidArrayPositionException("Índice fora dos limites em " + index + ": " + e.getMessage());
        }
    }

    private <T> T getValue(int index, boolean isCharacter) {
        try {
            T value;
            if (isCharacter) {
                value = (T) characters[index];
            } else {
                value = (T) numbers[index];
            }
            LoggerUtils.logDebug("Valor recuperado na posição " + index + ": " + value);
            return value;
        } catch (ArrayIndexOutOfBoundsException e) {
            LoggerUtils.logError("Erro ao recuperar valor no índice " + index + ": Índice fora dos limites. " + e.getMessage());
            throw new InvalidArrayPositionException("Índice fora dos limites ao recuperar valor no índice " + index + ": " + e.getMessage());
        }
    }

    private <T> void removeValue(int index, boolean isCharacter) {
        try {
            if (isCharacter) {
                characters[index] = null;
                LoggerUtils.logInfo("Caractere removido na posição " + index);
            } else {
                numbers[index] = null;
                LoggerUtils.logInfo("Número removido na posição " + index);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LoggerUtils.logError("Erro ao remover valor no índice " + index + ": Índice fora dos limites. " + e.getMessage());
            throw new InvalidArrayPositionException("Índice fora dos limites ao remover valor no índice " + index + ": " + e.getMessage());
        }
    }
}
