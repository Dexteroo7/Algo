import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FML {

    public static void main(String[] args) throws IOException {

        String pattern = "(.+)(Processing batch of )(\\d+)( files for merged_topic: public\\.android\\.mini\\.trips\\.TripsView public\\.android\\.mini\\.trips\\.TripsView bronze)$";
        System.out.println(Files.readAllLines(Paths.get("combined.out"))
                .stream()
                .filter(x -> x.matches(pattern))
                .mapToInt(value -> {

                    Pattern compile = Pattern.compile(pattern);
                    Matcher matcher = compile.matcher(value);
                    matcher.matches();
                    System.out.println(value);
                    return Integer.parseInt(matcher.group(3));
                }).sum());
    }
}
