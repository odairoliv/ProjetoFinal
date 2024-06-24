import java.io.*;
import java.util.ArrayList;

public class Arquivo {

    private static final String CAMINHO_USUARIOS = "dados_usuarios.txt";
    private static final String CAMINHO_INGRESSOS = "dados_ingressos.txt";

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

    public static ArrayList<Ingresso> carregarIngressos() {
        ArrayList<Ingresso> ingressos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_INGRESSOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 8) {
                    Ingresso ingresso = new Ingresso(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], dados[6], dados[7], Double.parseDouble(dados[8]));
                    ingressos.add(ingresso);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar ingressos: " + e.getMessage());
        }
        return ingressos;
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

    public static void salvarIngressos(ArrayList<Ingresso> ingressos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_INGRESSOS))) {
            for (Ingresso ingresso : ingressos) {
                bw.write(ingresso.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar ingressos: " + e.getMessage());
        }
    }
}
