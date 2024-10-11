package backend.academy.renderer;

public abstract class HangmanRenderer {

    String firstStepPicture = """
         _
         |
         |
         |
         |
        """;

    String secondStepPicture = """
         _ _ _ _
         |
         |
         |
         |
        """;

    String thirdStepPicture = """
         _ _ _ _
         |     |
         |
         |
         |
        """;

    String fourthStepPicture = """
         _ _ _ _
         |     |
         |     O
         |
         |
        """;

    String fifthStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |
         |
        """;

    String sixthStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |
         |    /
        """;

    String seventhStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |
         |    / \\
        """;

    String eighthStepPicture = """
         _ _ _ _
         |     |
         |     O
         |     |-
         |    / \\
        """;

    String ninthStepPicture = """
         _ _ _ _
         |     |
         |     O
         |    -|-
         |    / \\
        """;

    public abstract void render(int step);
}
