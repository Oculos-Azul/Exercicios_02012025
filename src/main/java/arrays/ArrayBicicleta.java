package arrays;

import model.Bicicleta;
import model.Model;

public class ArrayBicicleta extends MyArray {
    public ArrayBicicleta(int size) {
        super(size);
    }

    @Override
    public void insert(Model model) {
        if (instanceofBicicleta(model)) {
            super.insert(model);
        }
    }

    @Override
    public boolean remove(Model model) {
        if (instanceofBicicleta(model)) {
            return super.remove(model);
        }
        return false;
    }

    @Override
    public boolean search(Model model) {
        if (instanceofBicicleta(model)) {
            return super.search(model);
        }
        return false;
    }

    @Override
    public boolean update(int index, Model model) {
        if (instanceofBicicleta(model)) {
            return super.update(index, model);
        }
        return false;
    }

    private boolean instanceofBicicleta(Model model) {
        return model instanceof Bicicleta;
    }
}
