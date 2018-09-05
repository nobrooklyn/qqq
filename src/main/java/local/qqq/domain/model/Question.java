package local.qqq.domain.model;

import java.util.Optional;

public class Question {
    private String statement;
    private String[] options;
    private Optional<Answer> answer;

    public Question(String statement) {
        this.statement = statement;
        this.answer = Optional.empty();
    }

    public Question(String statement, String... options) {
        this.statement = statement;
        this.answer = Optional.empty();

        if (options == null || options.length < 2) {
            throw new IllegalArgumentException("options must be two or more items.");
        }
        this.options = options;
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

    public void answer(String... enteredAnswers) {
        this.answer = Optional.of(new Answer(enteredAnswers));
    }

    public Optional<String[]> answer() {
        return answer.map(a -> a.enteredAnswers());
    }

    public boolean hasAnswer() {
        return answer.isPresent();
    }
}