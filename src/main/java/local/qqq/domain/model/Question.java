package local.qqq.domain.model;

import java.util.Optional;

public class Question {
    private int id;
    private String statement;
    private String[] options;
    private Optional<Answer> answer;
    private QuestionStatus status;

    public Question(int id, String statement) {
        this.id = id;
        this.statement = statement;
        this.answer = Optional.empty();
        this.status = QuestionStatus.TODO;
    }

    public Question(int id, String statement, String... options) {
        this.id = id;
        this.statement = statement;
        this.answer = Optional.empty();
        this.status = QuestionStatus.TODO;

        if (options == null || options.length < 2) {
            throw new IllegalArgumentException("options must be two or more items.");
        }
        this.options = options;
    }

    public int id() {
        return id;
    }

    public String statement() {
        return statement;
    }

    public String[] options() {
        return options;
    }

    public boolean hasOptions() {
        return options != null;
    }

    public void answer(String... enteredAnswers) throws UnmodifiableAnswerException {
        if (isDone()) {
            throw new UnmodifiableAnswerException();
        }
        answer = Optional.of(new Answer(enteredAnswers));
    }

    public Optional<String[]> answer() {
        return answer.map(a -> a.enteredAnswers());
    }

    public boolean hasAnswer() {
        return answer.isPresent();
    }

    void cancel() {
        answer = Optional.empty();
        status = QuestionStatus.TODO;
    }

    void done() throws AnswerNotFoundException {
        if (!hasAnswer()) {
            throw new AnswerNotFoundException();
        }
        status = QuestionStatus.DONE;
    }

    boolean isDone() {
        return status == QuestionStatus.DONE;
    }

    void todo() {
        status = QuestionStatus.TODO;
    }
}