import Notepad.Note;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by u0861925 on 31/07/2019.
 */
public class Database {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String url;
        String username;
        String password;

            Data data = new Data();
            System.out.println("url");

            url = scanner.nextLine();

            System.out.println("Please enter your note.");
            username = scanner.nextLine();
            password = scanner.nextLine();
            data.url = url;
            data.username = username;
            data.password = password;

        scanner.close();


        ObjectMapper mapper = new ObjectMapper();
        String notesJson = mapper.writeValueAsString(data);
        Files.write(new File("k:\\PracticeApps\\database.json").toPath(), Arrays.asList(notesJson),
                StandardOpenOption.CREATE);
    }
}
