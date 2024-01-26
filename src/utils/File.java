package utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class File {

    // Não mudar nome do arquivo e não remover
    public String fileName = "data.txt";

    // Lê se já existem dados em data.txt para iniciar a agenda
    public void readFile(List<Contato> agenda) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line = br.readLine();
            if(line == null){
                System.out.println("Banco de dados vazio!");
                return;
            }

            do{

                // split por virgulas que não estejam dentro de []
                List<String> reading = new ArrayList<>(Arrays.asList(line.split(",(?![^\\[]*\\])")));

                // lista de telefones do contato
                List<Telefone> listaTelefones = new ArrayList<>();

                long idContato = Long.parseLong(reading.get(0));
                String nome = reading.get(1);
                String sobreNome = reading.get(2);
                String telefones = reading.get(3);

                // remove [] e a última ,
                String telefonesFormatados = telefones.substring(1, telefones.length() - 2).trim();

                // split por virgulas que não estejam dentro de ()
                List<String> tuples = new ArrayList<>(Arrays.asList(telefonesFormatados.split(",(?![^\\(]*\\))")));

                // adiciona os telefones na lista
                for(String tupla:tuples){
                    tuplaToTelefone(tupla,listaTelefones);
                }

                // cria contato na agenda
                Contato contato = new Contato(idContato,nome,sobreNome,listaTelefones);
                agenda.add(contato);


            }
            while ((line = br.readLine()) != null);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

    // separa uma string no formato "(x,y,z),(w,v,m),..." para criar
    // um objeto de Telefone. O nome tupla aqui é só por familiaridade.
    public static void tuplaToTelefone(String str, List<Telefone> listaTelefones) {
        // Remove parenteses e split
        String[] values = str.replaceAll("[()]", "").split(",");

        long id = Long.parseLong(values[0]);
        String ddd = values[1];
        long numero = Long.parseLong(values[2]);

        Telefone telefone = new Telefone(id,ddd,numero);
        listaTelefones.add(telefone);
        ;
    }

    // escreve em data.txt
    public void writeFile(List<Contato> agenda) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            for(Contato contato : agenda){
                bw.write(contato.getId()+","+contato.getNome()+","+contato.getSobreNome()+",[");
                List<Telefone> telefones = contato.getTelefones();
                for(Telefone tel:telefones){
                    bw.write("("+ tel.getId() +","+tel.getDdd()+","+tel.getNumero()+"),");
                }
                bw.write("]\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


}
