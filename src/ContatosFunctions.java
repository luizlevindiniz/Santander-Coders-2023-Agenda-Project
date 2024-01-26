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

    public static long gravarIdContato(Scanner scanner){
        long contatoId;
        do{
            System.out.print("Insira o id (inteiro positivo): ");
            contatoId = scanner.nextLong();
            scanner.nextLine(); // consumir newline
        } while (contatoId <= 0);
        return contatoId;
    }

    // adiciona um novo contato na agenda
    public static @NotNull Contato adicionarContato(Scanner scanner, List<Contato> agenda) {
        boolean idValido = false;
        List<Telefone> telefoneList = new ArrayList<>();

        long contatoId = gravarIdContato(scanner);

        while (!idValido) {
            if (procurarIdContato(contatoId, agenda) != -1) {
                System.out.println("Id já existente! Escolha outro!");
                contatoId = gravarIdContato(scanner);
            } else {
                idValido = true;
            }
        }

        System.out.print("Insira o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Insira o sobrenome: ");
        String sobreNome = scanner.nextLine();

        int option;
        boolean telefonesValidos = false;
        while (!telefonesValidos){
            TelefoneFunctions.printTelefoneMenu(telefoneList);
            option = scanner.nextInt();
            scanner.nextLine(); // consumir newline

            if(option <= 0 || option > 2){
                System.out.println("Opção Inválida! Tente novamente!");
            }

            else if (option == 2 && telefoneList.isEmpty()){
                System.out.println("Ops! Adicione pelo menos 1 telefone!");
            }

            else if(option == 2){
                telefonesValidos=true;
            }
            else{
                System.out.print("Insira o ddd do telefone: ");
                String ddd = scanner.nextLine();

                System.out.print("Insira o número do telefone: ");
                long numero = scanner.nextLong();
                scanner.nextLine(); // consumir newline

                // checa se o telefone já existe no db
                if(TelefoneFunctions.procurarTelefonePorNumero(ddd,numero,agenda)){
                    System.out.println("Esse telefone já está cadastrado! Tente Novamente!");
                }

                else {
                    Telefone telefone = new Telefone(TelefoneFunctions.getNextTelefoneId(), ddd, numero);
                    telefoneList.add(telefone);
                }

            }
        }

        return new Contato(contatoId, nome, sobreNome, telefoneList);
    }

    // deleta contato na agenda
    public static void deletarContato(@NotNull List<Contato> agenda, long idContato) {
        long index = procurarIdContato(idContato, agenda);
        if (index == -1) {
            System.out.println("ID não encontrado! Tente novamente!");
        } else {
            agenda.remove((int) index);
            System.out.println("Removido ID " + index + 1 + " da lista!");
        }

    }

    // edita um contato na agenda
    public static void editarContato(Scanner scanner, long idContato, List<Contato> agenda) {
        long indexContato = procurarIdContato(idContato, agenda);

        if (indexContato == -1) {
            System.out.println("ID não encontrado! Tente novamente!");
        } else {
            Contato contatoParaEditar = agenda.get((int) indexContato);
            List<Telefone> telefoneList = contatoParaEditar.getTelefones();

            System.out.printf("Nome atual: %s | Novo Nome: ", contatoParaEditar.getNome());
            String novoNome = scanner.nextLine();

            System.out.printf("Sobrenome atual: %s | Novo sobrenome: ", contatoParaEditar.getSobreNome());
            String novoSobreNome = scanner.nextLine();

            TelefoneFunctions.printTelefones(telefoneList);
            boolean idTelefoneValido = false;
            System.out.print("Insira o id para editar: ");
            long telefoneId = scanner.nextLong();
            scanner.nextLine(); // consumir newline
            long index = TelefoneFunctions.procurarIdTelefone(telefoneId,telefoneList);
            while (!idTelefoneValido) {
                if (index == -1) {
                    System.out.println("Id não existente! Escolha outro!");
                    System.out.print("Insira o id para editar: ");
                    telefoneId  = scanner.nextLong();
                    scanner.nextLine(); // consumir newline
                    index = TelefoneFunctions.procurarIdTelefone(telefoneId,telefoneList);
                } else {
                    idTelefoneValido = true;
                }
            }
            System.out.print("Novo DDD: ");
            String novoDDD = scanner.nextLine();

            System.out.print("Novo número: ");
            long novoNumero = scanner.nextLong();
            scanner.nextLine(); // consumir newline

            // checa se o telefone já existe no db
            if(TelefoneFunctions.procurarTelefonePorNumero(novoDDD,novoNumero,agenda)){
                System.out.println("Esse telefone já está cadastrado! Tente Novamente!");
            }

            else{
                Telefone atual = telefoneList.get((int) index);
                Telefone telefoneNovo = new Telefone(atual.getId(), novoDDD, novoNumero);
                telefoneList.set((int)index,telefoneNovo);

                Contato novoContato = new Contato(contatoParaEditar.getId(), novoNome, novoSobreNome, telefoneList);
                agenda.set((int) indexContato, novoContato);
            }

        }

    }

}

