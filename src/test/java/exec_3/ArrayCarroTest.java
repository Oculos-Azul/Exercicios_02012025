package exec_3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arrays.ArrayCarro;
import model.Carro;
import model.Model;

class ArrayCarroTest {
    private final ArrayCarro array = new ArrayCarro(10);
    private final Carro carro = new Carro(3, "Fiat", "Uno", 2020);
    private final Model model = new Model(2);
    private final ArrayCarro arrayOrd = new ArrayCarro(3);
    private final ArrayCarro ascArray = new ArrayCarro(3);
    private final ArrayCarro descArray = new ArrayCarro(3);

    @Test
    void shouldOrderAsc() {
        arrayOrd.insert(new Carro(3, "Marca3", "Modelo3", 2023));
        arrayOrd.insert(new Carro(1, "Marca1", "Modelo1", 2021));
        arrayOrd.insert(new Carro(2, "Marca2", "Modelo2", 2022));

        ascArray.insert(new Carro(1, "Marca1", "Modelo1", 2021));
        ascArray.insert(new Carro(2, "Marca2", "Modelo2", 2022));
        ascArray.insert(new Carro(3, "Marca3", "Modelo3", 2023));

        arrayOrd.orderByIdAsc();

        assertArrayEquals(ascArray.getItems(), arrayOrd.getItems());
    }
    
    @Test
    void testGetMarca() {
        assertEquals("Fiat", carro.getMarca());
    }

    @Test
    void testSetMarca() {
        carro.setMarca("Volkswagen");
        assertEquals("Volkswagen", carro.getMarca());
    }

    @Test
    void testGetModelo() {
        assertEquals("Uno", carro.getModelo());
    }

    @Test
    void testSetModelo() {
        carro.setModelo("Gol");
        assertEquals("Gol", carro.getModelo());
    }

    @Test
    void testGetAno() {
        assertEquals(2020, carro.getAno());
    }

    @Test
    void testSetAno() {
        carro.setAno(2022);
        assertEquals(2022, carro.getAno());
    }

    @Test
    void shouldOrderDesc() {
        arrayOrd.insert(new Carro(3, "Marca3", "Modelo3", 2023));
        arrayOrd.insert(new Carro(1, "Marca1", "Modelo1", 2021));
        arrayOrd.insert(new Carro(2, "Marca2", "Modelo2", 2022));

        descArray.insert(new Carro(3, "Marca3", "Modelo3", 2023));
        descArray.insert(new Carro(2, "Marca2", "Modelo2", 2022));
        descArray.insert(new Carro(1, "Marca1", "Modelo1", 2021));

        arrayOrd.orderByIdDesc();

        assertArrayEquals(descArray.getItems(), arrayOrd.getItems());
    }

    @Test
    void shouldInsertCar() {
        array.insert(carro);
        assertNotNull(array.getItems()[0]);
    }

    @Test
    void shouldNotInsertIncorrectModel() {
        array.insert(model);
        assertNull(array.getItems()[0]);
    }

    @Test
    void shouldRemoveCar() {
        array.insert(carro);
        assertTrue(array.search(carro));

        array.remove(carro);
        assertNull(array.getItems()[0]);
        assertFalse(array.search(carro));
    }

    @Test
    void shouldNotRemoveObjectNotPresentInArray() {
        assertFalse(array.remove(carro));
    }

    @Test
    void shouldNotRemoveIncorrectModel() {
        assertFalse(array.remove(model));
    }

    @Test
    void shouldReturnTrueForCarPresentInArray() {
        array.insert(carro);
        assertTrue(array.search(carro));
    }

    @Test
    void shouldReturnFalseForCarNotPresentInArray() {
        assertFalse(array.search(carro));
    }

    @Test
    void shouldReturnFalseWhenSearchingForIncorrectModelType() {
        assertFalse(array.search(model));
    }

    @Test
    void shouldReturnTrueForUpdatedCar() {
        array.insert(carro);
        assertTrue(array.update(0, new Carro(22, "Volkswagen", "Golf", 2022)));
        assertNotEquals(carro, array.getItems()[0]);
    }

    @Test
    void shouldReturnFalseWhenUpdatingCarNotPresentOnArray() {
        assertFalse(array.update(0, carro));
    }

    @Test
    void shouldReturnFalseWhenUpdatingIncorrectModelType() {
        assertFalse(array.update(0, model));
    }
}
