public class Usuario {
    private String id;
    private String nome;
    private String email;
    private String dataNascimento;
    private String cpf;
    private String senha;

    public Usuario(String id, String nome, String email, String dataNascimento, String cpf, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public String transformar() {
        return String.join(",", id, nome, email, dataNascimento, cpf, senha);
    }
}
