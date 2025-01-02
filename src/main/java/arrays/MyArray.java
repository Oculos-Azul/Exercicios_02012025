package arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.Model;

public class MyArray {
    private static final Logger logger = LoggerFactory.getLogger(MyArray.class);
    private Model[] items;

    public MyArray(int size) {
        this.items = new Model[size];
    }

    public void insert(int index, Model model) {
        items[index] = model;
    }

    public boolean remove(Model model) {
        int index = searchId(model);
        if (index != -1) {
            items[index] = null;
            return true;
        } else {
            logger.warn("Falha ao remover: Modelo '{}' não encontrado no array.", model);
            return false;
        }
    }

    public int searchId(Model model) {
        int count = 0;
        for (Model models : items) {
            if (models != null && model.equals(models)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    public boolean search(Model model) {
        for (Model models : items) {
            if (models != null && model.equals(models)) {
                return true;
            }
        }
        return false;
    }

    public void orderByIdAsc() {
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = 0; j < items.length - i - 1; j++) {
                if (items[j].getId() > items[j + 1].getId()) {
                    Model temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }

    public void orderByIdDesc() {
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = 0; j < items.length - i - 1; j++) {
                if (items[j].getId() < items[j + 1].getId()) {
                    Model temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }

    public boolean update(int index, Model model) {
        if (items[index] != null) {
            items[index] = model;
            return true;
        }
        return false;
    }

    public Model[] getItems() {
        return items;
    }
}