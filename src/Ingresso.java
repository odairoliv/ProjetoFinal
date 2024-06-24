public class Ingresso extends Usuario {
    private static int proximoCodigo = 1;
    private int codigo;
    private String filme;
    private String assento;
    private double preco;

    public Ingresso(String id, String nome, String email, String dataNascimento, String cpf, String senha, String filme, String assento, double preco) {
        super(id, nome, email, dataNascimento, cpf, senha);
        this.codigo = proximoCodigo++;
        this.filme = filme;
        this.assento = assento;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return getCodigo() + ";" + getId() + ";" + getNome() + ";" + getEmail() + ";" + getDataNascimento() + ";" + getCpf() + ";" + getFilme() + ";" + getAssento() + ";" + getPreco();
    }
}