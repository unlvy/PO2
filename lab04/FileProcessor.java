import java.io.*;
import java.util.*;
import java.text.Collator;

/**
 * class performing simple text file-processing operations
 * */
public class FileProcessor {

    /** collection of lines from a file */
    ArrayList<String> lines;
    /** collection of words from a file */
    ArrayList<String> words;

    /**
     * constructor
     * @param filePath path to file
     * */
    FileProcessor(String filePath) {
        lines = new ArrayList<String>();
        words = new ArrayList<String>();

        try {
            BufferedReader fReader = new BufferedReader(new FileReader(filePath));
            String line;
            int i = 1;
            while ((line = fReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
            fReader.close();
        } catch(Throwable e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        }
    }

    /**
     * prints first 3 and last 3 lines of a file
     * */
    void ex1() {
        System.out.println("Printing first three lines of a file:");
        for (int i = 0; i < 3; i++) { System.out.println(lines.get(i)); }
        System.out.println("Printing last three lines of a file:");
        for (int i = 3; i > 0; i--) { System.out.println(lines.get(lines.size() - i)); }
    }

    /**
     * splits lines into words, fills words arrayList
     * and prints number of lines, words and average number of words per line
     * */
    void ex2() {
        String[] currentLine;
        for (String line : lines) {
            currentLine = line.split("\\s");
            for (String word : currentLine) {
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        }
        System.out.println("Number of lines in the file: " + lines.size());
        System.out.println("Number of words in the file: " + words.size());
        System.out.println("Average number of words in line: " + (double)words.size() / (double)lines.size());
    }

    /**
     * sorts words in alphabetical order (based on polish language)
     * then asks user to give start and stop
     * and prints words from start to stop
     * */
    void ex3() {
        Collator collator = Collator.getInstance(new Locale("pl", "PL"));
        words.sort(collator);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter start");
        int START = scanner.nextInt();
        System.out.println("Enter stop");
        int STOP = scanner.nextInt();

        for (int i = START; i <= STOP; i++) {
            System.out.print(words.get(i) + " ");
        }
        System.out.println();
    }

    /**
     * asks user to give number N
     * then prints 5 most common words
     * with number of occurences
     * */
    void ex4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter N");
        int N = scanner.nextInt();

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        for (String word : words) {
            if (word.length() == N) {
                if (keys.contains(word)) {
                    int index = keys.indexOf(word);
                    values.set(index, values.get(index) + 1);
                } else {
                    keys.add(word);
                    values.add(1);
                }
            }
        }

        for (int j = 0; j < 5; j++) {
            int max = values.get(0);
            int index = 0;
            for (int i = 1; i < values.size(); i++) {
                if (values.get(i) > max) {
                    max = values.get(i);
                    index = i;
                }
            }
            System.out.println("Word: " + keys.get(index) + " occurences: " + max);
            keys.remove(index);
            values.remove(index);
        }
    }

}
