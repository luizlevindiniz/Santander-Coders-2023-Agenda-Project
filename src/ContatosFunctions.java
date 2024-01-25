import org.jetbrains.annotations.NotNull;
import utils.Contato;
import utils.Telefone;
import java.util.*;


public class ContatosFunctions {
    public static long procurarIdContato(long idContato, List<Contato> agenda) {
        for (int i = 0; i < agenda.size(); i++) {
            Contato contato = agenda.get(i);
            if (contato.getId() == idContato) {
                return i;
            }
        }
        return -1;
    }

    public static @NotNull Contato adicionarContato(Scanner scanner, List<Contato> agenda) {
        boolean idValido = false;
        List<Telefone> telefoneList = new ArrayList<>();

        System.out.print("Insira o id: ");
        long contatoId = scanner.nextLong();
        scanner.nextLine(); // consumir newline

        while (!idValido) {
            if (procurarIdContato(contatoId, agenda) != -1) {
                System.out.println("Id já existente! Escolha outro!");
                System.out.print("Insira o id: ");
                contatoId = scanner.nextLong();
                scanner.nextLine(); // consumir newline
            } else {
                idValido = true;
            }
        }

        System.out.print("Insira o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Insira o sobrenome: ");
        String sobreNome = scanner.nextLine();

        int option = 0;
        while (option!=2){
            TelefoneFunctions.printTelefoneMenu(telefoneList);
            option = scanner.nextInt();

            if(option <= 0 || option > 2){
                System.out.println("Opção Inválida! Tente novamente!");
            }
            else{
                System.out.print("Insira o ddd do telefone: ");
                String ddd = scanner.nextLine();

                System.out.print("Insira o número do telefone: ");
                long numero = scanner.nextLong();
                scanner.nextLine(); // consumir newline

                Telefone telefone = new Telefone(TelefoneFunctions.getNextTelefoneId(), ddd, numero);
                telefoneList.add(telefone);
            }
        }

        return new Contato(contatoId, nome, sobreNome, telefoneList);
    }

    public static void deletarContato(@NotNull List<Contato> agenda, long idContato) {
        long index = procurarIdContato(idContato, agenda);
        if (index == -1) {
            System.out.println("ID não encontrado! Tente novamente!");
        } else {
            agenda.remove((int) index);
            System.out.println("Removido ID " + index + 1 + " da lista!");
        }

    }

    public static void editarContato(Scanner scanner, long idContato, List<Contato> agenda) {
        long indexContato = procurarIdContato(idContato, agenda);

        if (indexContato == -1) {
            System.out.println("ID não encontrado! Tente novamente!");
        } else {
            Contato contatoParaEditar = agenda.get((int) indexContato);
            List<Telefone> telefoneList = contatoParaEditar.getTelefones();
            long contatoId = contatoParaEditar.getId();

            System.out.printf("Nome atual: %s | Novo Nome: ", contatoParaEditar.getNome());
            String novoNome = scanner.nextLine();

            System.out.printf("Sobrenome atual: %s | Novo sobrenome: ", contatoParaEditar.getSobreNome());
            String novoSobreNome = scanner.nextLine();

            TelefoneFunctions.printTelefones(telefoneList);
            boolean idTelefoneValido = false;
            System.out.print("Insira o id: ");
            long telefoneId = scanner.nextLong();
            scanner.nextLine(); // consumir newline

            while (!idTelefoneValido) {
                if (TelefoneFunctions.procurarIdTelefone(telefoneId,telefoneList) != -1) {
                    System.out.println("Id já existente! Escolha outro!");
                    System.out.print("Insira o id: ");
                    telefoneId  = scanner.nextLong();
                    scanner.nextLine(); // consumir newline
                } else {
                    idTelefoneValido = true;
                }
            }
            System.out.print("Novo DDD: ");
            String novoDDD = scanner.nextLine();

            System.out.print("Novo número: ");
            long novoNumero = scanner.nextLong();
            scanner.nextLine(); // consumir newline

            Telefone atual = telefoneList.get((int) telefoneId);
            Telefone telefoneNovo = new Telefone(atual.getId(), novoDDD, novoNumero);
            telefoneList.set((int)telefoneId,telefoneNovo);

            Contato novoContato = new Contato(contatoId, novoNome, novoSobreNome, telefoneList);
            agenda.set((int) indexContato, novoContato);
        }

    }

}

