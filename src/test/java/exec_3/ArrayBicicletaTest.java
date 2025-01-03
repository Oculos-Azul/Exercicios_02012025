package exec_3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import arrays.ArrayBicicleta;
import model.Bicicleta;
import model.Model;

class ArrayBicicletaTest {
    private final ArrayBicicleta array = new ArrayBicicleta(10);
    private final Bicicleta bicicleta = new Bicicleta(3, "Mountain Bike", "Alumínio", true);
    private final Model model = new Model(2);
    private final ArrayBicicleta arrayOrd = new ArrayBicicleta(3);
    private final ArrayBicicleta ascArray = new ArrayBicicleta(3);
    private final ArrayBicicleta descArray = new ArrayBicicleta(3);

    @Test
    void shouldOrderAsc() {
        arrayOrd.insert(new Bicicleta(3, "Tipo3", "Material3", false));
        arrayOrd.insert(new Bicicleta(1, "Tipo1", "Material1", true));
        arrayOrd.insert(new Bicicleta(2, "Tipo2", "Material2", false));

        ascArray.insert(new Bicicleta(1, "Tipo1", "Material1", true));
        ascArray.insert(new Bicicleta(2, "Tipo2", "Material2", false));
        ascArray.insert(new Bicicleta(3, "Tipo3", "Material3", false));

        arrayOrd.orderByIdAsc();

        assertArrayEquals(ascArray.getItems(), arrayOrd.getItems());
    }

    @Test
    void testGetTipo() {
        assertEquals("Mountain Bike", bicicleta.getTipo());
    }

    @Test
    void testSetTipo() {
        bicicleta.setTipo("Speed");
        assertEquals("Speed", bicicleta.getTipo());
    }

    @Test
    void testGetMaterial() {
        assertEquals("Alumínio", bicicleta.getMaterial());
    }

    @Test
    void testSetMaterial() {
        bicicleta.setMaterial("Carbono");
        assertEquals("Carbono", bicicleta.getMaterial());
    }

    @Test
    void testGetPossuiMarcha() {
        assertTrue(bicicleta.isPossuiMarcha());
    }

    @Test
    void testSetPossuiMarcha() {
        bicicleta.setPossuiMarcha(false);
        assertFalse(bicicleta.isPossuiMarcha());
    }

    @Test
    void shouldOrderDesc() {
        arrayOrd.insert(new Bicicleta(3, "Tipo3", "Material3", false));
        arrayOrd.insert(new Bicicleta(1, "Tipo1", "Material1", true));
        arrayOrd.insert(new Bicicleta(2, "Tipo2", "Material2", false));

        descArray.insert(new Bicicleta(3, "Tipo3", "Material3", false));
        descArray.insert(new Bicicleta(2, "Tipo2", "Material2", false));
        descArray.insert(new Bicicleta(1, "Tipo1", "Material1", true));

        arrayOrd.orderByIdDesc();

        assertArrayEquals(descArray.getItems(), arrayOrd.getItems());
    }

    @Test
    void shouldInsertBicicleta() {
        array.insert(bicicleta);
        assertNotNull(array.getItems()[0]);
    }

    @Test
    void shouldNotInsertIncorrectModel() {
        array.insert(model);
        assertNull(array.getItems()[0]);
    }

    @Test
    void shouldRemoveBicicleta() {
        array.insert(bicicleta);
        assertTrue(array.search(bicicleta));

        array.remove(bicicleta);
        assertNull(array.getItems()[0]);
        assertFalse(array.search(bicicleta));
    }

    @Test
    void shouldNotRemoveObjectNotPresentInArray() {
        assertFalse(array.remove(bicicleta));
    }

    @Test
    void shouldNotRemoveIncorrectModel() {
        assertFalse(array.remove(model));
    }

    @Test
    void shouldReturnTrueForBicicletaPresentInArray() {
        array.insert(bicicleta);
        assertTrue(array.search(bicicleta));
    }

    @Test
    void shouldReturnFalseForBicicletaNotPresentInArray() {
        assertFalse(array.search(bicicleta));
    }

    @Test
    void shouldReturnFalseWhenSearchingForIncorrectModelType() {
        assertFalse(array.search(model));
    }

    @Test
    void shouldReturnTrueForUpdatedBicicleta() {
        array.insert(bicicleta);
        assertTrue(array.update(0, new Bicicleta(22, "Speed", "Aço", false)));
        assertNotEquals(bicicleta, array.getItems()[0]);
    }

    @Test
    void shouldReturnFalseWhenUpdatingBicicletaNotPresentOnArray() {
        assertFalse(array.update(0, bicicleta));
    }

    @Test
    void shouldReturnFalseWhenUpdatingIncorrectModelType() {
        assertFalse(array.update(0, model));
    }
}
