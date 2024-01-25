package utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class File {

    public String fileName = "data.txt";

    public void readFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void writeFile(List<Contato> agenda) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            for(Contato contato : agenda){
                bw.write(contato.getId()+","+contato.getNome()+","+contato.getSobreNome()+",[");
                List<Telefone> telefones = contato.getTelefones();
                for(Telefone tel:telefones){
                    bw.write("("+tel.getDdd()+","+tel.getNumero()+"),");
                }
                bw.write("]\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


}
