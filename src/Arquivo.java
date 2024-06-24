import java.io.*;
import java.util.ArrayList;

public class Arquivo {

    private static final String CAMINHO_USUARIOS = "dados_usuarios.txt";

    public static ArrayList<Usuario> carregarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_USUARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 6) {
                    Usuario usuario = new Usuario(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5]);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
        return usuarios;
    } 

    public static void salvarUsuarios(ArrayList<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_USUARIOS))) {
            for (Usuario usuario : usuarios) {
                bw.write(usuario.transformar());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
}
