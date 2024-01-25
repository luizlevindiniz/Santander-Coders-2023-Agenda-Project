import utils.Telefone;

import java.util.List;

// TelefoneIdGenerator.java
public class TelefoneFunctions {
    private static long telefoneId = 1;

    public static long getNextTelefoneId() {
        return telefoneId++;
    }

    // usado na função de editar
    public static void printTelefones(List<Telefone> telefones) {
        for (Telefone telefone : telefones) {
            System.out.print("id: " + telefone.getId() + "|" + telefone.getDdd() + telefone.getNumero() + "\n");
        }
    }

    public static void printTelefoneMenu(List<Telefone> telefones) {
        StringBuilder menuBuilder = new StringBuilder();
        menuBuilder.append("""
                >>>> Telefones <<<<
                Id | DDD | Número
                """);
        if (telefones.isEmpty()) {
            menuBuilder.append("Vazio!\n");
        } else {
            for (Telefone telefone : telefones) {
                menuBuilder.append(String.format("%d | %s | %d%n", telefone.getId(), telefone.getDdd(), telefone.getNumero()));
            }
        }
        menuBuilder.append(
                """
                        >>>> Menu <<<<
                        1 - Adicionar Telefone
                        2 - Sair
                        """);

        String menu = menuBuilder.toString();
        System.out.println(menu);
    }


    public static long procurarIdTelefone(long idTelefone, List<Telefone> telefones) {
        for (int i = 0; i < telefones.size(); i++) {
            Telefone tel = telefones.get(i);
            if (tel.getId() == idTelefone) {
                return i;
            }
        }
        return -1;
    }
}