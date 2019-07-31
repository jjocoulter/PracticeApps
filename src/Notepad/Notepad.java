package Notepad;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Note program to append to a notes file using JSON.
 */
public class Notepad {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String title;
        String note;
        ArrayList<Note> notes = new ArrayList<>();

        while (true) {
            Note noteObj = new Note();
            System.out.println("Please enter a title for your note. Enter Q to exit.");

            title = scanner.nextLine();

            System.out.println("Please enter your note.");
            note = scanner.nextLine();

            if (title.equals("q") || note.equals("q")) {
                break;
            }
            noteObj.title = title;
            noteObj.note = note;
            notes.add(noteObj);
        }
        scanner.close();


        ObjectMapper mapper = new ObjectMapper();
        StringBuilder notesJson = new StringBuilder();
        for (Note g : notes) {
            notesJson.append(mapper.writeValueAsString(g));
        }
        Files.write(new File("k:\\PracticeApps\\notes.json").toPath(), Arrays.asList(notesJson.toString()),
                StandardOpenOption.APPEND);
    }
}
