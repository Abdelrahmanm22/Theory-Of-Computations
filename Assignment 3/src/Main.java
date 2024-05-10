//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader("input_cfg.txt"));
        BufferedWriter writer1 = new BufferedWriter(new FileWriter("output_cfg.txt"));
        BufferedReader reader2 = new BufferedReader(new FileReader("input_pda.txt"));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("output_pda.txt"));


        ///CFG
        String line;
        while ((line= reader1.readLine())!=null){

            int problemNumber = Integer.parseInt(line);
            if (problemNumber==1){
                writer1.write(1+"\n");
                problem1CFG(reader1,writer1);
                writer1.write("end\n");
            }else if(problemNumber==2){
                writer1.write(2+"\n");
                problem2CFG(reader1,writer1);
                writer1.write("end\n");
            }else if (problemNumber==3){
                writer1.write(3+"\n");
                problem3CFG(reader1, writer1);
                writer1.write("end\n");
            }else if (problemNumber==4){
                writer1.write(4+"\n");
                problem4CFG(reader1, writer1);
                writer1.write("end\n");
            }
        }

//        String line2;
//        while ((line2= reader2.readLine())!=null){
//
//            int problemNumber = Integer.parseInt(line2);
//            if (problemNumber==1){
//                writer2.write(1+"\n");
//                problem1PDA(reader2,writer2);
//                writer2.write("end\n");
//            }else if(problemNumber==2){
//                writer2.write(2+"\n");
//                problem2PDA(reader2,writer2);
//                writer2.write("end\n");
//            }else if (problemNumber==3){
//                writer2.write(3+"\n");
//                problem3PDA(reader2, writer2);
//                writer2.write("end\n");
//            }else if (problemNumber==4){
//                writer2.write(4+"\n");
//                problem4PDA(reader2, writer2);
//                writer2.write("end\n");
//            }
//        }

        reader1.close();
        writer1.close();
        reader2.close();
        writer2.close();
    }

    public static void problem1CFG(BufferedReader reader, BufferedWriter writer) throws IOException {


    }

    ///Validate phone numbers
    public static void problem2CFG(BufferedReader reader, BufferedWriter writer) throws IOException {

    }

    public static void problem3CFG(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("end")){
            if (recursion(line, 0, line.length() - 1)){
                writer.write("accepted \n");
            }else{
                writer.write("not accepted \n");
            }
        }
    }
    public static boolean recursion(String str, int left, int right){
        ///base case
        if (left >= right) {
            return true;
        }
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        return recursion(str, left + 1, right - 1);
    }

    public static void problem4CFG(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("end")){
            if (isValid(line)){
                writer.write("accepted \n");
            }else{
                writer.write("not accepted \n");
            }
        }
    }
    public static boolean isValid(String str){
        if (str.charAt(0)=='b') {
            return false;
        }

        for (int i = 0; i < str.length()-1; i++) {
            if (str.charAt(i)=='b' && str.charAt(i+1)=='a') {
                return false;
            }
        }
        int countA = 0;
        int countB = 0;

        for (char c : str.toCharArray()) {
            if (c == 'a') {
                countA++;
            } else if (c == 'b') {
                countB++;
            }
        }
        if (countA == ((2*countB)+3)){
            return true;
        }else{
            return false;
        }
    }


    public static void problem1PDA(BufferedReader reader, BufferedWriter writer) throws IOException {


    }

    ///Validate phone numbers
    public static void problem2PDA(BufferedReader reader, BufferedWriter writer) throws IOException {

    }
    public static void problem3PDA(BufferedReader reader, BufferedWriter writer) throws IOException {

    }
    public static void problem4PDA(BufferedReader reader, BufferedWriter writer) throws IOException {

    }
}