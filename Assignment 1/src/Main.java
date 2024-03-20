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
                writer.write(3+"\n");
                problem3(reader,writer);
                writer.write("x\n");
            }else if (problemNumber==4){
                writer.write(4+"\n");
                problem4(reader,writer);
                writer.write("x\n");
            }else if (problemNumber==5){

            }else if (problemNumber==6){

            }else if(problemNumber==7){
                ////مش متأكد منها اوي لانها بتطلع انسر مختلف عن الدكتور بس انسر صح
                //فابقو بصو عليها
                writer.write(7+"\n");
                problem7(reader,writer);
                writer.write("x\n");
            }else if (problemNumber==8){
                writer.write(8+"\n");
                problem8(reader, writer);
                writer.write("x\n");
            }else if (problemNumber==9){
                writer.write(9+"\n");
                problem9(reader,writer);
                writer.write("x\n");
            }else if (problemNumber==10){
                writer.write(10+"\n");
                problem10(reader, writer);
                writer.write("x\n");
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

    ///Validate dates
    public static void problem3(BufferedReader reader, BufferedWriter writer) throws IOException {
        String regex = "^(\\d{4}\\/\\d{2}\\/\\d{2}|\\d{4}-\\d{2}-\\d{2}|\\d{2}\\/\\d{2}\\/\\d{4}|\\d{1}\\/\\d{2}\\/\\d{4}|\\d{2}\\/\\d{1}\\/\\d{4}|\\d{2}-\\d{2}-\\d{4}|\\d{1}-\\d{2}-\\d{4}|\\d{2}-\\d{1}-\\d{4})$";
        Pattern pattern = Pattern.compile(regex);

        String line;
        while (!(line = reader.readLine()).equals("end")){
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()){
//                System.out.println(line);
                writer.write("valid date\n");
            }else{
                writer.write("invalid date\n");
            }
        }
    }

    public static void problem4(BufferedReader reader, BufferedWriter writer) throws IOException {
        String regex ="^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern pattern = Pattern.compile(regex);

        String line;
        while (!(line = reader.readLine()).equals("end")){
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()){
//                System.out.println(line);
                writer.write("valid IP address\n");
            }else{
                writer.write("invalid IP address\n");
            }
        }
    }


    ////مش متأكد منها اوي لانها بتطلع انسر مختلف عن الدكتور بس انسر صح
    //فابقو بصو عليها
    public static void problem7(BufferedReader reader, BufferedWriter writer) throws IOException {
        String regex = "(a(aa)*b(bb)*)"; ////شبه الفي الslides بتاعة lecture2 سلايد رقم 15
        Pattern pattern = Pattern.compile(regex);

        String line;
        while (!(line = reader.readLine()).equals("end")){
            Matcher matcher = pattern.matcher(line);
            int count = 0;
            writer.write("*" + line + "*\n");
            while (matcher.find()) {
                count++;
                String matchedSubstring = matcher.group();
                int startIdx = matcher.start();
                int endIdx = matcher.end();
                writer.write("matched substring: " + matchedSubstring+"\n");
                writer.write("start index: " + startIdx + ", end index: " + endIdx+"\n");
            }
            writer.write("number of matched substrings: " + count+"\n");
        }
    }

    
    //Extract words whose length is a multiple of 3
    public static void problem8(BufferedReader reader, BufferedWriter writer) throws IOException {
        String regex = "\\b(\\w{3})+\\b";
        Pattern pattern = Pattern.compile(regex);

        String line;
        while (!(line = reader.readLine()).equals("end")){
            writer.write('*'+line+"*\n");
            Matcher matcher = pattern.matcher(line);
            int count = 0;
            while (matcher.find()) {
                String word = matcher.group();
                int start = matcher.start();
                int end = matcher.end();
                // System.out.println("Word: " + word + ", Start index: " + start + ", End index: " + end);
                writer.write("matched word: "+ word + '\n');
                writer.write("start index: "+ start + ", end index: "+ end + '\n');
                count++;
            }
            if(count>0)
                writer.write("number of matched words: "+ count + '\n');
            else 
                writer.write("No word matches\n");
        }
    }

    public static void problem9(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("end")) {
            File file = new File(line);
            writer.write("*" + file.getName() + "*\n");

            String regex = "(https?://\\S+)";
            Pattern pattern = Pattern.compile(regex);

            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String fileLine;
            int lineNumber = 1;
            int totalURLs = 0;

            while ((fileLine = fileReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(fileLine);
                while (matcher.find()) {
                    totalURLs++;
                    String url = matcher.group();
                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    writer.write("URL: " + url + "\n");
                    writer.write("Line: " + lineNumber + "\n");
                    writer.write("Start index: " + startIndex + ", End index: " + (endIndex) + "\n");
                }
                lineNumber++;
            }

            writer.write("Number of URLs: " + totalURLs + "\n");
            fileReader.close();
        }
    }


    public static void problem10(BufferedReader reader, BufferedWriter writer) throws IOException {
        String varRegex = "((-?\\d)+|([a-zA-z])|(-?\\d)+([a-zA-z])?)";
        String regex = "^"+varRegex+"([+-/*]"+varRegex+")*="+varRegex+"([+-/*]"+varRegex+")*$";
        Pattern pattern = Pattern.compile(regex);

        String line;
        while (!(line = reader.readLine()).equals("end")){
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()){
                writer.write("valid mathematical expression\n");
            }else{
                writer.write("invalid mathematical expression\n");
            }
        }
    }
}