package local.qqq.application;

import local.qqq.domain.model.Question;

public class QuestionOutput {
    private final int id;
    private final String statement;

    public QuestionOutput(Question q) {
        this.id = q.id();
        this.statement = q.statement();
    }

    public int getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }
}