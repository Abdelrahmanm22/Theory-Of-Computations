//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));


        String line;
        while ((line= reader.readLine())!=null){



            int problemNumber = Integer.parseInt(line);
            if (problemNumber==1){
                writer.write(1+"\n");
                problem1(reader,writer);
                writer.write("x\n");
            }else if(problemNumber==2){
                writer.write(2+"\n");
                problem2(reader,writer);
                writer.write("x\n");
            }else if (problemNumber==3){

            }else if (problemNumber==4){

            }else if (problemNumber==5){

            }else if (problemNumber==6){

            }else if(problemNumber==7){

            }else if (problemNumber==8){

            }else if (problemNumber==9){

            }else if (problemNumber==10){

            }
        }

        ///من غير السطرين دول مش هيكتب في الفايل
        reader.close();
        writer.close();
    }


    ///Validate email addresses
    public static void problem1(BufferedReader reader, BufferedWriter writer) throws IOException {
        String regex ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);

        String line;
        while (!(line = reader.readLine()).equals("end")){
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()){
//                System.out.println(line);
                writer.write("valid email\n");
            }else{
                writer.write("invalid email\n");
            }
        }

    }

    ///Validate phone numbers
    public static void problem2(BufferedReader reader, BufferedWriter writer) throws IOException {
        String regex ="^(\\d{10}|\\d{3}-\\d{3}-\\d{4}|\\(\\d{3}\\)-\\d{3}-\\d{4}|\\d{3}\\.\\d{3}\\.\\d{4}|\\d{3} \\d{3} \\d{4})$";
        Pattern pattern = Pattern.compile(regex);

        String line;
        while (!(line = reader.readLine()).equals("end")){
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()){
//                System.out.println(line);
                writer.write("valid phone number\n");
            }else{
                writer.write("invalid phone number\n");
            }
        }
    }
}