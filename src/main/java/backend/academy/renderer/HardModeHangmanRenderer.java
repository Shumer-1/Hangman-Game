package backend.academy.renderer;

import java.io.PrintStream;

public class HardModeHangmanRenderer implements HangmanRenderer {

    private final PrintStream printer;

    public HardModeHangmanRenderer(PrintStream printStream) {
        printer = printStream;
    }

    String startPicture = """
         _
         |
         |
         |
         |
        """;

    String firstStepPicture = """
         _ _ _ _
         |
         |
         |
         |
        """;

    String secondStepPicture = """
         _ _ _ _
         |     |
         |
         |
         |
        """;

    String thirdStepPicture = """
         _ _ _ _
         |     |
         |     O
         |
         |
        """;

    String fourthStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |
         |
        """;

    String fifthStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |
         |    /
        """;

    String sixthStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |
         |    / \\
        """;

    String seventhStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |-
         |    / \\
        """;

    String finalPicture = """
         _ _ _ _
         |     |
         |     O
         |    -|-
         |    / \\
        """;

    @Override
    @SuppressWarnings("MagicNumber")
    public void render(int step) {
        printer.println(switch (step) {
            case 0 -> startPicture;
            case 1 -> firstStepPicture;
            case 2 -> secondStepPicture;
            case 3 -> thirdStepPicture;
            case 4 -> fourthStepPicture;
            case 5 -> fifthStepPicture;
            case 6 -> sixthStepPicture;
            case 7 -> seventhStepPicture;
            default -> finalPicture;
        });
    }
}
