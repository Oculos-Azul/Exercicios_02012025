package exec_3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arrays.ArrayPessoa;
import model.Model;
import model.Pessoa;

class ArrayPessoaTest {
    private final ArrayPessoa array = new ArrayPessoa(10);
    private final Pessoa pessoa = new Pessoa(3, "foo");
    private final Model model = new Model(2);

    @Test
    void shouldInsertPerson() {
        array.insert(0, pessoa);
        assertNotNull(array.getItems()[0]);
    }

    @Test
    void shouldNotInsertIncorrectModel() {
        array.insert(0, model);
        assertNull(array.getItems()[0]);
    }

    @Test
    void shouldRemovePerson() {
        array.insert(0, pessoa);
        assertTrue(array.search(pessoa));

        array.remove(pessoa);
        assertNull(array.getItems()[0]);
        assertFalse(array.search(pessoa));
    }

    @Test
    void shouldNotRemoveObjectNotPresentInArray() {
        assertFalse(array.remove(pessoa));
    }

    @Test
    void shouldNotRemoveIncorrectModel() {
        assertFalse(array.remove(model));
    }

    @Test
    void shouldReturnTrueForModelPresentInArray() {
        array.insert(0, pessoa);
        assertTrue(array.search(pessoa));
    }

    @Test
    void shouldReturnFalseForModelNotPresentInArray() {
        assertFalse(array.search(pessoa));
    }

    @Test
    void shouldReturnFalseWhenSearchingForIncorrectModelType() {
        assertFalse(array.search(model));
    }

    @Test
    void shouldReturnTrueForUpdatedPerson() {
        array.insert(0, pessoa);
        assertTrue(array.update(0, new Pessoa(22, "name")));
        assertNotEquals(pessoa, array.getItems()[0]);
    }

    @Test
    void shouldReturnFalseWhenUpdatingModelNotPresentOnArray() {
        assertFalse(array.update(0, pessoa));
    }

    @Test
    void shouldReturnFalseWhenUpdatingIncorrectModelType() {
        assertFalse(array.update(0, model));
    }
}
