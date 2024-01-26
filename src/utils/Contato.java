package utils;
import java.util.List;

public class Contato {

    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;

    public Contato(Long id, String nome, String sobreNome, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public String getSobreNome() {
        return sobreNome;
    }


    public List<Telefone> getTelefones() {
        return telefones;
    }

}
