package app;

import java.io.*;

public class ReadandWrite {
    private String textFile;
    private String line = null;
    private Controller control;

    public ReadandWrite(String txtFile){
        this.textFile = txtFile;
        this.control = control;
        File file = new File(textFile);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("SYS rnw: \nError creating opening/creating file! \n\""+e.getMessage()+"\"");
        }
    }

    public String read(){
        try {
            FileReader fileReader = new FileReader(textFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch(FileNotFoundException ex) {
            System.out.println("SYS rnw: Unable to open file !");
        } catch(IOException ex) {
            System.out.println("SYS rnw: Error reading file !");
        }
        return null;
    }
    public void write(String data){
        try {
            FileWriter fileWriter = new FileWriter(textFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch(IOException ex) {
            System.out.println(" SYS rnw: Error writing to file !");
        }
    }
}