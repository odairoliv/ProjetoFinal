public class Sistema {

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

    }

    private void cadastrar(){
        
    }
}
