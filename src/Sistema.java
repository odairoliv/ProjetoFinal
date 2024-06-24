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
        String[][] assentos = new String[5][9];
        for (int i = 0; i < assentos.length; i++) {
            for (int j = 0; j < assentos[i].length; j++) {
                assentos[i][j] = " ";
            }
        }
        
        // Carregar assentos ocupados
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getFilme().equals(filme)) {
                String assento = ingresso.getAssento();
                int linha = assento.charAt(0) - 'A';
                int coluna = Integer.parseInt(assento.substring(1)) - 1;
                assentos[linha][coluna] = "X";
            }
        }
    
        while (true) {
            System.out.println("\n--- Assentos ---");
            System.out.print("  ");
            for (int i = 1; i <= 9; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
    
            for (int i = 0; i < assentos.length; i++) {
                System.out.print((char) ('A' + i) + " ");
                for (int j = 0; j < assentos[i].length; j++) {
                    System.out.print(assentos[i][j] + " ");
                }
                System.out.println();
            }
    
            System.out.print("Escolha o assento (ex: A1): ");
            String assentoEscolhido = Console.lerString().toUpperCase();
    
            if (assentoEscolhido.length() == 2 || assentoEscolhido.length() == 3) {
                int linha = assentoEscolhido.charAt(0) - 'A';
                int coluna = Integer.parseInt(assentoEscolhido.substring(1)) - 1;
    
                if (linha >= 0 && linha < assentos.length && coluna >= 0 && coluna < assentos[0].length) {
                    if (assentos[linha][coluna].equals(" ")) {
                        assentos[linha][coluna] = "X";
                        
                        Ingresso ingresso = new Ingresso(
                            usuarioAtual.getId(),
                            usuarioAtual.getNome(),
                            usuarioAtual.getEmail(),
                            usuarioAtual.getDataNascimento(),
                            usuarioAtual.getCpf(),
                            usuarioAtual.getSenha(),
                            filme,
                            assentoEscolhido,
                            preco
                        );
                        
                        ingressos.add(ingresso);
                        Arquivo.salvarIngressos(ingressos);
                        bomboniere();
                        return;
                    } else {
                        System.out.println("Assento ocupado, escolha outro.");
                    }
                } else {
                    System.out.println("Assento inválido, tente novamente.");
                }
            } else {
                System.out.println("Assento inválido, tente novamente.");
            }
        }
    }

    private void realizarCheckin(){
        ArrayList<Ingresso> ingressosUsuario = new ArrayList<>();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(usuarioAtual.getId())) {
                ingressosUsuario.add(ingresso);
            }
        }
    
        if (ingressosUsuario.isEmpty()) {
            System.out.println("Você não possui ingressos.");
            return;
        }
    
        System.out.println("Seus ingressos:");
        for (int i = 0; i < ingressosUsuario.size(); i++) {
            Ingresso ingresso = ingressosUsuario.get(i);
            System.out.println((i + 1) + ". Filme: " + ingresso.getFilme() + ", Assento: " + ingresso.getAssento());
        }
    
        System.out.print("Escolha o ingresso para checkin (0 para sair): ");
        int escolha = Console.lerInt();
    
        if (escolha > 0 && escolha <= ingressosUsuario.size()) {
            Ingresso ingressoEscolhido = ingressosUsuario.get(escolha - 1);
            System.out.println("Realizar checkin para o ingresso do filme " + ingressoEscolhido.getFilme() + ", assento " + ingressoEscolhido.getAssento() + "? (1. Sim, 2. Não, 3. Voltar, 0. Sair)");
            int opcao = Console.lerInt();
    
            if (opcao == 1) {
                System.out.println("Checkin realizado com sucesso!");
            } else if (opcao == 0) {
                System.exit(0);
            }
        }
    }

    private void bomboniere(){

        while (true) {
            System.out.println("1. Combo I (Pipoca Pequena + Refri) R$30,00\n2. Combo II (Pipoca Média + Refri + MM) R$50,00\n3. Combo III (Pipoca Grande + 2 Refri + MM) R$70,00\n4. Voltar\n5. Pular\n0. Sair");
            int opcao = Console.lerInt();
            double precoCombo = 0;
            String combo = "";

            switch (opcao) {
                case 1:
                    precoCombo = 30.0;
                    combo = "Combo I (Pipoca Pequena + Refri)";
                    break;
                case 2:
                    precoCombo = 50.0;
                    combo = "Combo II (Pipoca Média + Refri + MM)";
                    break;
                case 3:
                    precoCombo = 70.0;
                    combo = "Combo III (Pipoca Grande + 2 Refri + MM)";
                    break;
                case 4:
                    return;
                case 5:
                    resumoCompra(0, "");
                    return;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }

            if (opcao >= 1 && opcao <= 3) {
                resumoCompra(precoCombo, combo);
                return;
            }
        }
    }

      private void resumoCompra(double precoCombo, String combo) {
        System.out.println("\n--- Resumo de compra ---");
        System.out.println("Ingresso: " + ingressos.get(ingressos.size() - 1).getFilme() + " - R$" + ingressos.get(ingressos.size() - 1).getPreco());
        if (!combo.isEmpty()) {
            System.out.println(combo + " - R$" + precoCombo);
        }
        double total = ingressos.get(ingressos.size() - 1).getPreco() + precoCombo;
        System.out.println("Total: R$" + total);

        System.out.println("1. Realizar Pagamento\n2. Voltar\n0. Sair");
        int opcao = Console.lerInt();

        switch (opcao) {
            case 1:
                System.out.println("Pagamento realizado com sucesso!");
                menuPrincipal();
                break;
            case 2:
                bomboniere();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Opção inválida.");
        }
    }
}
