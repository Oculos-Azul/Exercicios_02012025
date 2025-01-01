package arrays;

import model.Model;
import model.Pessoa;

public class ArrayPessoa extends MyArray {
    public ArrayPessoa(int size) {
        super(size);
    }

    @Override
    public void insert(int index, Model model) {
        if (instanceofPessoa(model)) {
            super.insert(index, model);
        }
    }

    @Override
    public boolean remove(Model model) {
        if (instanceofPessoa(model)) {
            return super.remove(model);
        }
        return false;
    }

    @Override
    public boolean search(Model model) {
        if (instanceofPessoa(model)) {
            return super.search(model);
        }
        return false;
    }

    @Override
    public boolean update(int index, Model model) {
        if (instanceofPessoa(model)) {
            return super.update(index, model);
        }
        return false;
    }

    private boolean instanceofPessoa(Model model) {
        return model instanceof Pessoa;
    }
}
