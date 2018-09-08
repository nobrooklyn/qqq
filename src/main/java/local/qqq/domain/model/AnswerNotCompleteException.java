package local.qqq.domain.model;

import java.util.List;

public class AnswerNotCompleteException extends Exception {
    private static final long serialVersionUID = 1L;
    private List<Integer> notEnteredNo;

    public AnswerNotCompleteException(List<Integer> notEnteredNo) {
        super("not complete." + notEnteredNo);
        this.notEnteredNo = notEnteredNo;
    }

    public List<Integer> notEnteredNo() {
        return notEnteredNo;
    }
}