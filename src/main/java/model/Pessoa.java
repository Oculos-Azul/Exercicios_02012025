package model;

public class Pessoa extends Model {
    private String name;
    private int age;
    private String email;

    public Pessoa(int id, String name, int age, String email) {
        super(id);
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Pessoa) {
            Pessoa other = (Pessoa) obj;
            return super.equals(other) && this.email.equals(other.email);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + email.hashCode();
    }
}
