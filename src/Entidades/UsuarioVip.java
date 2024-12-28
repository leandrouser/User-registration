package Entidades;

public class UsuarioVip extends Usuario{


    private String beneficios;

    public UsuarioVip(String nome, String cpf, String dataNascimento, String beneficios) {
        super(nome, cpf, dataNascimento);
        this.beneficios = beneficios;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

}
