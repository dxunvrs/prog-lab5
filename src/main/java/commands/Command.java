package commands;

public abstract class Command {
    private final String name;
    private final String description;
    private final int expectArgs;
    protected String[] tokens;

    public Command(String name, String description, int expectArgs) {
        this.name = name;
        this.description = description;
        this.expectArgs = expectArgs;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void execute(String[] tokens) {
        this.tokens = tokens;
        if (expectArgs == (tokens.length - 1)) {
            process();
        } else {
            System.out.println("Ожидалось " + expectArgs + " аргументов, получено " + (tokens.length-1));
        }
    }

    protected abstract void process();
}