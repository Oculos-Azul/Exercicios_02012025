package arrays;

import model.Carro;
import model.Model;

public class ArrayCarro extends MyArray {
    public ArrayCarro(int size) {
        super(size);
    }

    @Override
    public void insert(Model model) {
        if (instanceofCarro(model)) {
            super.insert(model);
        }
    }

    @Override
    public boolean remove(Model model) {
        if (instanceofCarro(model)) {
            return super.remove(model);
        }
        return false;
    }

    @Override
    public boolean search(Model model) {
        if (instanceofCarro(model)) {
            return super.search(model);
        }
        return false;
    }

    @Override
    public boolean update(int index, Model model) {
        if (instanceofCarro(model)) {
            return super.update(index, model);
        }
        return false;
    }

    private boolean instanceofCarro(Model model) {
        return model instanceof Carro;
    }
}
