package exec_1;

import org.junit.jupiter.api.Test;
import arraybidimensional.CustomMatrix;
import static org.junit.jupiter.api.Assertions.*;
import exceptions.InvalidTypeException;
import exceptions.InvalidArrayPositionException;

public class CustomMatrixTest {

    @Test
    public void testSuccessConstructor() {
        CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
        assertNotNull(matrix);
    }

    @Test
    public void testFailConstructorInvalidType() {
        InvalidTypeException exception = assertThrows(InvalidTypeException.class, () -> {
            new CustomMatrix(new Character[3], new Integer[3]);
        });
        assertEquals(InvalidTypeException.class, exception.getClass());
    }

    @Test
    public void testSuccessInsertCharacter() {
        CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
        matrix.addCharacter(1, 'A');
        assertEquals('A', matrix.getCharacter(1));
    }

    @Test
    public void testSuccessInsertNumber() {
        CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
        matrix.addNumber(1, 10);
        assertEquals(10, matrix.getNumber(1));
    }

    @Test
    public void testFailInsertInvalidType() {
        InvalidTypeException exception = assertThrows(InvalidTypeException.class, () -> {
            CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
            matrix.addCharacter(1, "invalid");
        });
        assertEquals(InvalidTypeException.class, exception.getClass());
    }

    @Test
    public void testFailInsertInvalidPosition() {
        InvalidArrayPositionException exception = assertThrows(InvalidArrayPositionException.class, () -> {
            CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
            matrix.addNumber(5, 10);
        });
        assertEquals(InvalidArrayPositionException.class, exception.getClass());
    }

    @Test
    public void testSuccessGetCharacter() {
        CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
        matrix.addCharacter(1, 'A');
        assertEquals('A', matrix.getCharacter(1));
    }

    @Test
    public void testSuccessGetNumber() {
        CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
        matrix.addNumber(1, 10);
        assertEquals(10, matrix.getNumber(1));
    }

    @Test
    public void testFailGetInvalidType() {
        InvalidTypeException exception = assertThrows(InvalidTypeException.class, () -> {
            CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
            matrix.getCharacter(1);
        });
        assertEquals(InvalidTypeException.class, exception.getClass());
    }

    @Test
    public void testFailGetInvalidPosition() {
        InvalidArrayPositionException exception = assertThrows(InvalidArrayPositionException.class, () -> {
            CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
            matrix.getNumber(5);
        });
        assertEquals(InvalidArrayPositionException.class, exception.getClass());
    }

    @Test
    public void testSuccessRemoveCharacter() {
        CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
        matrix.addCharacter(1, 'A');
        matrix.removeCharacter(1);
        assertNull(matrix.getCharacter(1));
    }

    @Test
    public void testSuccessRemoveNumber() {
        CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
        matrix.addNumber(1, 10);
        matrix.removeNumber(1);
        assertNull(matrix.getNumber(1));
    }

    @Test
    public void testFailRemoveInvalidType() {
        InvalidTypeException exception = assertThrows(InvalidTypeException.class, () -> {
            CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
            matrix.removeCharacter(1);
        });
        assertEquals(InvalidTypeException.class, exception.getClass());
    }

    @Test
    public void testFailRemoveInvalidPosition() {
        InvalidArrayPositionException exception = assertThrows(InvalidArrayPositionException.class, () -> {
            CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
            matrix.removeCharacter(5);
        });
        assertEquals(InvalidArrayPositionException.class, exception.getClass());
    }

    @Test
    public void testSuccessContainsCharacter() {
        CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
        matrix.addCharacter(1, 'A');
        assertTrue(matrix.getCharacter(1) == 'A');
    }

    @Test
    public void testSuccessContainsNumber() {
        CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
        matrix.addNumber(1, 10);
        assertTrue(matrix.getNumber(1) == 10);
    }

    @Test
    public void testFailContainsInvalidPosition() {
        InvalidArrayPositionException exception = assertThrows(InvalidArrayPositionException.class, () -> {
            CustomMatrix matrix = new CustomMatrix(new Character[3], new Integer[3]);
            matrix.getCharacter(5);
        });
        assertEquals(InvalidArrayPositionException.class, exception.getClass());
    }
}
