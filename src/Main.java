
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void printMenu(){
        String menu = """
                ##################
                ##### AGENDA #####
                ##################
                >>>> Contatos <<<<
                Id | Nome
                1 | Alex Araujo
                2 | Joao Gomes
                3 | Silvio Santos
                >>>> Menu <<<<
                1 - Adicionar Contato
                2 - Remover Contato
                3 - Editar Contato
                4 - Sair
                             
                
                """;
        System.out.println(menu);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 4){
            printMenu();
            option = scanner.nextInt();

        }
    }
}