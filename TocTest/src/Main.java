import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String aReg = "(a(aa)*)";
        String bReg = "(b(bb)*)";


        // String regex = '('+bReg+aReg+bReg+aReg+bReg+aReg+bReg+ ")|(" + aReg+bReg+aReg+bReg+aReg+bReg+aReg+')';
        // String regex = "(("+bReg+aReg+aReg+aReg+
        // aReg+bReg+aReg+aReg+")|("+
        // aReg+aReg+bReg+aReg+")|("+
        // aReg+aReg+aReg+bReg
        // +"))|(("+
        // aReg+bReg+bReg+bReg+")|("+
        // bReg+aReg+bReg+bReg+")|("+
        // bReg+bReg+aReg+bReg+")|("+
        // bReg+bReg+bReg+aReg+"))";

        String optA = '('+aReg+aReg+")*";
        String optB = '('+bReg+bReg+")*";


        String regex = 
        "(("+
        bReg+aReg+optA+")|("+
        aReg+bReg+optA+")|("+
        optA+bReg+aReg+")|("+
        optA+aReg+bReg+
        "))|(("+
        aReg+bReg+optB+")|("+
        bReg+aReg+optB+")|("+
        optB+aReg+bReg+")|("+
        optB+bReg+aReg+
        "))"
        ;
        ;
//        String regex = "(a(aa)*+b(bb)*)+";
        Pattern pattern = Pattern.compile(regex);

        String[] strings = {
                "aabb",
                "aabaaaaabaa",
                "aab",
                "abbbaaaab",
                "bbaaab"
        };
        for (String st : strings) {
            Matcher matcher = pattern.matcher(st);
            int count = 0;
            System.out.println("*" + st + "*");
            while (matcher.find()) {
                count++;
                String matchedSubstring = matcher.group();
                int startIdx = matcher.start();
                int endIdx = matcher.end();
                System.out.println("matched substring: " + matchedSubstring);
                System.out.println("start index: " + startIdx + ", end index: " + endIdx);
            }
            System.out.println("number of matched substrings: " + count);
        }
    }
}
