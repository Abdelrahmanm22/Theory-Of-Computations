import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

//        String regex = "(a(aa)*b(bb)*)|(b(bb)*a(aa)*)";
        String regex = "(((aa)*b(bb)*a(aa)*)|((bb)*a(aa)*b(bb)*))|((a(aa)*b(bb)*(aa)*)|(b(bb)*a(aa)*(bb)*))";
//        String regex = "(a(aa)*+b(bb)*)+";
        Pattern pattern = Pattern.compile(regex);

        String[] strings = {
                "aabb",
                "aabaaaaabaa",
                "aab",
                "abbbaaaab",
                "bbbaaab",
                "abaa",
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
