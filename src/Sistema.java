import java.util.ArrayList;

public class Sistema {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Ingresso> ingressos;
    private Usuario usuarioAtual;

    public Sistema() {
        this.usuarios = Arquivo.carregarUsuarios();
        this.ingressos = Arquivo.carregarIngressos();
        this.usuarioAtual = null;
    }

    public void iniciar() {
        
        while (true) {
            System.out.println(
                "\n--- Acessar Sistema ---\n" +
                "1. Entrar\n" +
                "2. Cadastrar\n" + 
                "0. Sair"
            );
            System.out.print("Digite sua opção: ");

            int opcao = Console.lerInt();

            switch (opcao) {
                case 1:
                    entrar();
                    break;
                case 2:
                    cadastrar();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void entrar(){
        System.out.println("\n--- Fomulário de Acesso ---");
        System.out.print("E-mail: ");
        String email = Console.lerString();
        System.out.print("Senha: ");
        String senha = Console.lerString();
    
        boolean usuarioEncontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                usuarioEncontrado = true;
                if (usuario.getSenha().equals(senha)) {
                    usuarioAtual = usuario;
                    menuPrincipal();
                    return;
                } else {
                    System.out.println("\nE-mail ou senha incorretos.");
                    return;
                }
            }
        }
        if (!usuarioEncontrado) {
            System.out.println("\nUsuário não encontrado.");
        }
    }

    private void cadastrar(){
        System.out.println("\n--- Fomulário de Cadastro ---");
        System.out.print("Nome Completo: ");
        String nome = Console.lerString();
        System.out.print("E-mail: ");
        String email = Console.lerString();
        System.out.print("Data de Nascimento: ");
        String dataNascimento = Console.lerString();
        System.out.print("CPF: ");
        String cpf = Console.lerString();
        System.out.print("Senha: ");
        String senha = Console.lerString();

        String id = gerarIdUnico();
        Usuario novoUsuario = new Usuario(id, nome, email, dataNascimento, cpf, senha);
        usuarios.add(novoUsuario);
        Arquivo.salvarUsuarios(usuarios);
        System.out.println("\nUsuário cadastrado com sucesso!");
    }

    private String gerarIdUnico() {
        return "U" + (usuarios.size() + 1);
    }

    private void menuPrincipal(){
        while (true) {
            System.out.println(
                "\n--- Menu Principal ---\n" + 
                "1. Opções em cartaz\n"+
                "2. Realizar checkin do ingresso\n"+
                "3. Bomboniere\n"+
                "0. Sair"
            );
            int opcao = Console.lerInt();

            switch (opcao) {
                case 1:
                    opcoesEmCartaz();
                    break;
                case 2:
                    realizarCheckin();
                    break;
                case 3:
                    bomboniere();
                    break;
                case 0:
                    usuarioAtual = null;
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void opcoesEmCartaz(){
        String[] filmes = {"Divertidamente", "Jogos Vorazes", "Nem que a vaca tussa"};
        double[] precos = {30.00, 15.00};

        while (true) {
            while (true) {
                System.out.println("\n--- Opções em cartaz ---");
                for (int i = 0; i < filmes.length; i++) {
                    System.out.println((i + 1) + ". " + filmes[i]);
                }
                System.out.println("4. Voltar");
                System.out.println("0. Sair");
                System.out.print("Digite sua opção: ");
                int opcao = Console.lerInt();
        
                switch (opcao) {
                    case 1:
                    case 2:
                    case 3:
                        String filmeEscolhido = filmes[opcao - 1];
                        System.out.println(
                            "\n--- Ingressos ---\n"+
                            "1. Inteira - R$30,00\n"+
                            "2. Meia - R$15,00\n"+
                            "3. Voltar\n"+
                            "0. Sair"
                            );
                        System.out.print("Digite sua opção: ");
                        int tipoIngresso = Console.lerInt();
        
                        switch (tipoIngresso) {
                            case 1:
                            case 2:
                                double precoIngresso = precos[tipoIngresso - 1];
                                escolherAssento(filmeEscolhido, precoIngresso);
                                break;
                            case 3:
                                continue;
                            case 0:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                        break;
                    case 4:
                        return;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }

    private void escolherAssento(String filme, double preco){
        
    }

    private void realizarCheckin(){

    }

    private void bomboniere(){

    }
}
