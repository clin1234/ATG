import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class AEG {
    private static final Pattern COMPILE = Pattern.compile("\\R");

    public static void main(String[] args) throws IOException {
        // Insert name of file containing user answers here.
        var inputFile = Path.of("actually_right.txt");
        if (!Files.exists(inputFile)) throw new NoSuchFileException(inputFile + " does not exist");

        var contents = Files.readString(inputFile);
        String[] splitter = COMPILE.split(contents, 2);
        String name = splitter[0];
        String answersString = splitter[1].replace(System.lineSeparator(), "");
        if (24L != splitter[1].chars().filter(c -> c == ';').count())
            System.err.println(inputFile + " must have exactly 24 ';' separating answers");
        else {
            Exam e = new Exam(name, LocalDate.now().toString());
            String[] answersArray = answersString.split(";");
            e.takeExam(answersArray);
            e.gradeExam();
            e.writeOut();
            e.showForIncorrectAns(answersArray);
            Reporter.displayGraph(e);
        }
    }
}
