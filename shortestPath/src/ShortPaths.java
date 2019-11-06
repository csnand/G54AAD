import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ShortPaths {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please input  path to a test file");
            return;
        }

        String fileName = args[1];
        Path path = Paths.get(fileName);
        Scanner sc = new Scanner(System.in);

    }
}
