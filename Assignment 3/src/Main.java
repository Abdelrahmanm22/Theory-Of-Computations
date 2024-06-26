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

        String line2;
        while ((line2= reader2.readLine())!=null){
            int problemNumber = Integer.parseInt(line2);
            if (problemNumber==1){
                writer2.write(1+"\n");
                problem1PDA(reader2,writer2);
                writer2.write("end\n");
            }else if(problemNumber==2){
                writer2.write(2+"\n");
                problem2PDA(reader2,writer2);
                writer2.write("end\n");
            }else if (problemNumber==3){
                writer2.write(3+"\n");
                problem3PDA(reader2, writer2);
                writer2.write("end\n");
            }else if (problemNumber==4){
                writer2.write(4+"\n");
                problem4PDA(reader2, writer2);
                writer2.write("end\n");
            }
        }

        reader1.close();
        writer1.close();
        reader2.close();
        writer2.close();
    }

    public static void problem1CFG(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("end")){
            if ( check(line,0,0,0) ){
                writer.write("accepted \n");
            }else{
                writer.write("not accepted \n");
            }
        }
    }
    public static boolean check(String str,int aCount, int bCount, int index){
        // Base case
        if (index == str.length()) {
            return aCount == bCount;
        }
        char currentChar = str.charAt(index);
        ///transactions
        if (currentChar == 'a') {
            return check(str, aCount + 1, bCount, index + 1);
        } else if (currentChar == 'b') {
            return check(str, aCount, bCount + 1, index + 1);
        } else {
            return check(str, aCount, bCount, index + 1);
        }
    }


    public static void problem2CFG(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("end")){
            if ( ok(line,0,0,0) ){
//                System.out.println("accepted");
                writer.write("accepted \n");
            }else{
//                System.out.println("not accepted");
                writer.write("not accepted \n");
            }
        }
    }

    public static boolean ok(String str,int aCount, int bCount, int index){
        // Base case
        if (index == str.length()) {
//            System.out.println(aCount+" "+bCount);
            return aCount == (2*bCount);
        }
        char currentChar = str.charAt(index);

        ///transactions
        if (currentChar == 'a') {
            return ok(str, aCount + 1, bCount, index + 1);
        } else if (currentChar == 'b') {
            return ok(str, aCount, bCount + 1, index + 1);
        } else {
            return ok(str, aCount, bCount, index + 1);
        }
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
            if (isValid(line,0,0,0)){
                writer.write("accepted \n");
//                System.out.println("True");
            }else{
                writer.write("not accepted \n");
//                System.out.println("false");
            }
        }
    }
    public static boolean isValid(String str,int aCount, int bCount, int index){
        // Base case
        if (index == str.length()) {
            return aCount == ((2*bCount)+3);
        }
        char currentChar = str.charAt(index);

        ///check a's comes after b's
        if (currentChar=='a' && index!=0 ){
            char lastChar = str.charAt(index-1);
            if (lastChar=='b'){
                return false;
            }

        }
        ///transactions
        if (currentChar == 'a') {
            return isValid(str, aCount + 1, bCount, index + 1);
        } else if (currentChar == 'b') {
            return isValid(str, aCount, bCount + 1, index + 1);
        } else {
            return isValid(str, aCount, bCount, index + 1);
        }
    }


    public static void problem1PDA(BufferedReader reader, BufferedWriter writer) throws IOException {
        Pda turnstilePDA = new Pda(
                new String[] {"q0", "q1", "q2", "q3"},
                new String[] {"a", "b"},
                new String[] {"$", "a", "b"},
                new String[] {"q0","q3"},
                new PdaTransition[] {
                        new PdaTransition("q0", "", "", "q1", "", "$"),
                        new PdaTransition("q1", "a", "", "q1", "", "a"),
                        new PdaTransition("q1", "b", "a", "q2", "a", ""),
                        new PdaTransition("q2", "b", "a", "q2", "a", ""),
                        new PdaTransition("q2", "", "$", "q3", "$", ""),
                },
                "q0"
        );

        String line;
        while (!(line = reader.readLine()).equals("end")) {
            turnstilePDA.input(line);
            if (turnstilePDA.isAccepting()) {
                writer.write("accepted\n");
            } else {
                writer.write("not accepted\n");
            }
            turnstilePDA.reset();
        }


    }
    
    public static void problem2PDA(BufferedReader reader, BufferedWriter writer) throws IOException {
        Pda turnstilePDA = new Pda(
                new String[] {"q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7"},
                new String[] {"a", "b"},
                new String[] {"$", "a", "b"},
                new String[] {"q7"},
                new PdaTransition[] {
                        new PdaTransition("q0", "", "", "q1", "", "$"),
                        new PdaTransition("q1", "a", "", "q2", "", ""),
                        new PdaTransition("q2", "a", "", "q3", "", "a"),
                        new PdaTransition("q3", "a", "", "q2", "", ""),
                        new PdaTransition("q3", "b", "", "q4", "", ""),
                        new PdaTransition("q4", "b", "", "q5", "", ""),
                        new PdaTransition("q5", "b", "a", "q6", "a", ""),
                        new PdaTransition("q6", "b", "", "q4", "", ""),
                        new PdaTransition("q6", "", "$", "q7", "$", ""),
                },
                "q0"
        );

        String line;
        while (!(line = reader.readLine()).equals("end")) {
            turnstilePDA.input(line);
            if (turnstilePDA.isAccepting()) {
                writer.write("accepted\n");
            } else {
                writer.write("not accepted\n");
            }
            turnstilePDA.reset();
        }
    }
    public static void problem3PDA(BufferedReader reader, BufferedWriter writer) throws IOException {
        Pda turnstilePDA = new Pda(
                new String[] { "q0", "q1", "q2", "q3", "q4" },
                new String[] { "{", "}" },
                new String[] { "$", "{", "}" },
                new String[] { "q4" },
                new PdaTransition[] {
                        new PdaTransition("q0", "", "", "q1", "", "$"),
                        new PdaTransition("q1", "{", "", "q2", "", "x"),
                        // does stack top have to be dollar sign???
                        new PdaTransition("q2", "{", "", "q2", "", "x"),
                        new PdaTransition("q2", "}", "x", "q3", "x", ""),
                        new PdaTransition("q3", "{", "", "q2", "", "x"),
                        new PdaTransition("q3", "}", "x", "q3", "x", ""),
                        new PdaTransition("q3", "", "$", "q4", "$", ""),

                },
                "q0");

        String line;
        while (!(line = reader.readLine()).equals("end")) {
            turnstilePDA.input(line);
            if (turnstilePDA.isAccepting()) {
                writer.write("accepted\n");
            } else {
                writer.write("not accepted\n");
            }
            turnstilePDA.reset();
        }
    }
    public static void problem4PDA(BufferedReader reader, BufferedWriter writer) throws IOException {
        Pda turnstilePDA = new Pda(
                new String[] { "q0", "q1", "q2", "q3", "q4", "q5" },
                new String[] { "a", "b", "c" },
                new String[] { "$", "a", "b", "c"},
                new String[] { "q5" },
                new PdaTransition[] {
                        new PdaTransition("q0", "", "", "q1", "", "$"),
                        new PdaTransition("q1", "a", "", "q2", "", "x"),
                        new PdaTransition("q2", "a", "", "q2", "", "x"),
                        new PdaTransition("q2", "b", "x", "q3", "x", ""),
                        new PdaTransition("q3", "b", "x", "q3", "x", ""),
                        new PdaTransition("q3", "c", "x", "q4", "x", ""),
                        new PdaTransition("q4", "c", "x", "q4", "x", ""),
                        new PdaTransition("q4", "", "$", "q5", "$", ""),

                },
                "q0");
        String line;
        while (!(line = reader.readLine()).equals("end")) {
            turnstilePDA.input(line);
            if (turnstilePDA.isAccepting()) {
                writer.write("accepted\n");
            } else {
                writer.write("not accepted\n");
            }
            turnstilePDA.reset();
        }
    }
}