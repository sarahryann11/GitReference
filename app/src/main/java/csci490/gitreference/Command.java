package csci490.gitreference;

/**
 * Created by Sarah on 2/20/2018.
 */

public class Command {
    private String command;
    private String example;
    private String explanation;
    private String section;

    public Command() {}

    public String getCommand() {
        return command;
    }

    public void setCommand(String courseName) {
        this.command = command;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String professor) {
        this.example = example;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
