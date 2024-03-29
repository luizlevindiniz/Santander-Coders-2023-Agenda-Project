import utils.Contato;

import java.io.IOException;
import java.util.*;

import utils.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void printMenu(List<Contato> agenda) {
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append("""
                ##################
                ##### AGENDA #####
                ##################
                >>>> Contatos <<<<
                Id | Nome
                """);
        if (agenda.isEmpty()) {
            menuBuilder.append("Vazio!\n");
        } else {
            for (Contato contato : agenda) {
                menuBuilder.append(String.format("%d | %s %s%n", contato.getId(), contato.getNome(), contato.getSobreNome()));
            }
        }
        menuBuilder.append(
                """
                        >>>> Menu <<<<
                        1 - Adicionar Contato
                        2 - Remover Contato
                        3 - Editar Contato
                        4 - Sair
                        """);

        String menu = menuBuilder.toString();
        System.out.println(menu);
    }

    public static void handleUserInput(List<Contato> agenda, Scanner scanner) {
        int option = 0;
        while (option != 4) {
            try {
                printMenu(agenda);
                option = scanner.nextInt();

                if (option <= 0 || option > 4) {
                    System.out.println("Opção Inválida! Tente novamente!");
                } else {
                    switch (option) {
                        case 1:
                            Contato novo = ContatosFunctions.adicionarContato(scanner, agenda);
                            agenda.add(novo);
                            break;
                        case 2:
                            System.out.print("Entre o id para remover: ");
                            long idParaRemover = scanner.nextLong();
                            scanner.nextLine(); // consumir newline
                            ContatosFunctions.deletarContato(agenda, idParaRemover);
                            break;
                        case 3:
                            System.out.print("Entre o id para editar: ");
                            long idParaEditar = scanner.nextLong();
                            scanner.nextLine(); // consumir newline
                            ContatosFunctions.editarContato(scanner, idParaEditar, agenda);
                            break;
                        default:
                            break;
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Entrada Inválida!");
                scanner.nextLine(); // consumir newline
            }
        }
        // Retornando para main
        System.out.println("Salvando dados!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Instancia File em um objeto. File é utilizado
            // para leitura e escrita de arquivo
            File file = new File();
            List<Contato> agenda = new ArrayList<>();
            file.readFile(agenda);

            // Interface de interação com o usuário
            handleUserInput(agenda, scanner);

            // Escrita no txt (banco de dados)
            file.writeFile(agenda);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        scanner.close();
    }
}