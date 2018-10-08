package local.qqq.application;

public class AnswerOutput {
    private final Exception[] exceptions;

    public AnswerOutput() {
        this.exceptions = null;
    }

    public AnswerOutput(Exception[] exceptions) {
        this.exceptions = exceptions;
    }

    public Exception[] getExceptions() {
        return exceptions;
    }
}