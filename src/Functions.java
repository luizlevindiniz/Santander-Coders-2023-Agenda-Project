import org.jetbrains.annotations.NotNull;
import utils.Contato;
import utils.Telefone;

import java.util.*;

public class Functions {

    public static void printTelefones(List<Telefone> telefones) {
        for (Telefone telefone : telefones) {
            System.out.print("DDD atual: "+telefone.getDdd() + " " + "Número atual: "+ telefone.getNumero() +" |");
        }
    }
    public static @NotNull Contato adicionarContato(Scanner scanner){
        try{
            List<Telefone> telefoneList = new ArrayList<>();

            System.out.print("Insira o id: ");
            long contatoId = scanner.nextLong();
            scanner.nextLine(); // consumir newline

            System.out.print("Insira o nome: ");
            String nome = scanner.nextLine();

            System.out.print("Insira o sobrenome: ");
            String sobreNome = scanner.nextLine();

            System.out.print("Insira o ddd do telefone: ");
            String ddd = scanner.nextLine();

            System.out.print("Insira o número do telefone: ");
            long numero = scanner.nextLong();
            scanner.nextLine(); // consumir newline

            Telefone telefone = new Telefone(TelefoneIdGenerator.getNextTelefoneId(),ddd,numero);
            telefoneList.add(telefone);

            return new Contato(contatoId,nome,sobreNome,telefoneList);

        }catch (NoSuchElementException e){
            throw e;
        }

    }

    public static void deletarContato(@NotNull List<Contato> agenda, long idContato){
        for (int i = 0; i < agenda.size(); i++) {
            Contato contato = agenda.get(i);
            if(contato.getId() == idContato){
                agenda.remove(i);
                System.out.println("Removido ID " + i+1 + " da lista!");
                return;
            }
        }
        System.out.println("ID não encontrado! Tente novamente!");

    }

    public static void editarContato(Scanner scanner, long idContato, List<Contato> agenda){
        try{
            Contato contatoParaEditar = null;
            int index = 0;
            for (int i = 0; i < agenda.size(); i++) {
                Contato contatoAtual = agenda.get(i);
                if(contatoAtual.getId() == idContato){
                    contatoParaEditar = contatoAtual;
                    index = i;
                }
            }
            
            if(contatoParaEditar == null){
                System.out.println("ID não encontrado! Tente novamente!");
            }
            else{
                List<Telefone> telefoneList = new ArrayList<>();
                long contatoId = contatoParaEditar.getId();

                System.out.printf("Nome atual: %s | Novo Nome: ",contatoParaEditar.getNome());
                String novoNome = scanner.nextLine();

                System.out.printf("Sobrenome atual: %s | Novo sobrenome: ",contatoParaEditar.getSobreNome());
                String novoSobreNome = scanner.nextLine();

                printTelefones(contatoParaEditar.getTelefones());
                System.out.print(" Novo DDD: ");
                String novoDDD = scanner.nextLine();

                printTelefones(contatoParaEditar.getTelefones());
                System.out.print(" Novo número: ");
                long novoNumero = scanner.nextLong();
                scanner.nextLine(); // consumir newline

                Telefone telefone = new Telefone(TelefoneIdGenerator.getNextTelefoneId(),novoDDD,novoNumero);
                telefoneList.add(telefone);

                Contato novoContato = new Contato(contatoId,novoNome,novoSobreNome,telefoneList);
                agenda.set(index,novoContato);
            }
        }catch (Exception e){
            throw e;
        }

    }

}

