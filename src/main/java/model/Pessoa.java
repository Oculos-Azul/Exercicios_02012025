package model;

import java.util.Objects;

public class Pessoa extends Model {
    String name;

    public Pessoa(int id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        Pessoa pessoa = (Pessoa) obj;

        return super.getId() == pessoa.getId();
    }
}