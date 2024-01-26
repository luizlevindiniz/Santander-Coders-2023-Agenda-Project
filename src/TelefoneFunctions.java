import utils.Contato;
import utils.Telefone;

import java.util.List;

// TelefoneIdGenerator.java
public class TelefoneFunctions {
    private static long telefoneId = 1;

    // usado na função de adicionar contato
    public static long getNextTelefoneId() {
        return telefoneId++;
    }


    public static StringBuilder telefoneMenuHeader(List<Telefone> telefones){
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append("""
                >>>> Telefones <<<<
                Id | DDD | Número
                """);
        // essa opção não deveria acontecer
        if (telefones.isEmpty()) {
            menuBuilder.append("Vazio!\n");
        } else {
            for (Telefone telefone : telefones) {
                menuBuilder.append(String.format("%d | %s | %d%n", telefone.getId(), telefone.getDdd(), telefone.getNumero()));
            }
        }
        return menuBuilder;
    }
    // usado na função de adicionar contato
    public static void printTelefoneMenu(List<Telefone> telefones) {
        StringBuilder menuBuilder = telefoneMenuHeader(telefones);
        menuBuilder.append(
                """
                        >>>> Menu <<<<
                        1 - Adicionar Telefone
                        2 - Finalizar Cadastro
                        """);

        String menu = menuBuilder.toString();
        System.out.println(menu);
    }


    // usado na função de editar
    public static long procurarIdTelefone(long idTelefone, List<Telefone> telefones) {
        for (int i = 0; i < telefones.size(); i++) {
            Telefone tel = telefones.get(i);
            if (tel.getId() == idTelefone) {
                return i;
            }
        }
        return -1;
    }

    // usado na função de editar
    public static void printTelefones(List<Telefone> telefones) {
        StringBuilder menuBuilder = telefoneMenuHeader(telefones);
        String menu = menuBuilder.toString();
        System.out.println(menu);
    }

    public static boolean procurarTelefonePorNumero(String ddd, long numero,List<Contato> agenda){
        for(Contato contato:agenda){
            List<Telefone> telefones = contato.getTelefones();
            for(Telefone telefone : telefones){
                if(telefone.getNumero() == numero && telefone.getDdd().equals(ddd)){
                    return true;
                }
            }
        }
        return false;
    }


}
