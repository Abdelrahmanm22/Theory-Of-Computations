//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {

        String regex ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";;
        Pattern pattern = Pattern.compile(regex);

        String[] emails ={
                "user@example.com",
                "user.name@example.co.in",
                "user123@gmail.com",
                "user.name@domain.co.uk",
                "1234567890@example.com",
                "user-123@example.com",
                "user@subdomain.example.com",
                "user@123.123.123.123",
                "user@[123.123.123.123]",
                "user@example.com."
        };
        for(String email :emails){
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()){
                System.out.println(email +" : Valid");
            }else{
                System.out.println(email +" : inValid");
            }
        }
    }
}