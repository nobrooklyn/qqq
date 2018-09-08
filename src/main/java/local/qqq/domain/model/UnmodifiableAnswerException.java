package local.qqq.domain.model;

public class UnmodifiableAnswerException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnmodifiableAnswerException() {
        super("already done.");
    }
}