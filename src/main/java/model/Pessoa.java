package model;

public class Pessoa extends Model {
    String name;

    public Pessoa(int id, String name) {
        super(id);
        this.name = name;
    }
}
