package exec_3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
    private final Pessoa pessoa = new Pessoa(3, "foo", 18, "eudoido@tamo.com.br");
    private final Model model = new Model(2);
    private final ArrayPessoa arrayOrd = new ArrayPessoa(3);
    private final ArrayPessoa ascArray = new ArrayPessoa(3);
    private final ArrayPessoa descArray = new ArrayPessoa(3);

    @Test
    void shouldOrderAsc() {
        arrayOrd.insert(new Pessoa(3, "Pessoa 3", 25, "pessoa3@email.com"));
        arrayOrd.insert(new Pessoa(1, "Pessoa 1", 30, "pessoa1@email.com"));
        arrayOrd.insert(new Pessoa(2, "Pessoa 2", 22, "pessoa2@email.com"));

        ascArray.insert(new Pessoa(1, "Pessoa 1", 30, "pessoa1@email.com"));
        ascArray.insert(new Pessoa(2, "Pessoa 2", 22, "pessoa2@email.com"));
        ascArray.insert(new Pessoa(3, "Pessoa 3", 25, "pessoa3@email.com"));

        arrayOrd.orderByIdAsc();

        assertArrayEquals(ascArray.getItems(), arrayOrd.getItems());
    }

    @Test
    void shouldOrderDesc() {
        arrayOrd.insert(new Pessoa(3, "Pessoa 3", 25, "pessoa3@email.com"));
        arrayOrd.insert(new Pessoa(1, "Pessoa 1", 30, "pessoa1@email.com"));
        arrayOrd.insert(new Pessoa(2, "Pessoa 2", 22, "pessoa2@email.com"));

        descArray.insert(new Pessoa(3, "Pessoa 3", 25, "pessoa3@email.com"));
        descArray.insert(new Pessoa(2, "Pessoa 2", 22, "pessoa2@email.com"));
        descArray.insert(new Pessoa(1, "Pessoa 1", 30, "pessoa1@email.com"));

        arrayOrd.orderByIdDesc();

        assertArrayEquals(descArray.getItems(), arrayOrd.getItems());
    }

    @Test
    void shouldInsertPerson() {
        array.insert(pessoa);
        assertNotNull(array.getItems()[0]);
    }

    @Test
    void shouldNotInsertIncorrectModel() {
        array.insert(model);
        assertNull(array.getItems()[0]);
    }

    @Test
    void shouldRemovePerson() {
        array.insert(pessoa);
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
        array.insert(pessoa);
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
        array.insert(pessoa);
        assertTrue(array.update(0, new Pessoa(22, "name", 28, "name@email.com")));
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
