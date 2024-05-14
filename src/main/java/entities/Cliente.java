package entities;

public class Cliente {

    private String nome;

    public String getNome() {
        return nome;
    }

    public Cliente() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
