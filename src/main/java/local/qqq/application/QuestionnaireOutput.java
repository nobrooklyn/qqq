package local.qqq.application;

import local.qqq.domain.model.Questionnaire;

public class QuestionnaireOutput {
    private final int id;
    private final String title;
    private final boolean done;
    private final QuestionOutput[] questions;

    QuestionnaireOutput(Questionnaire questionnaire) {
        this.id = questionnaire.id();
        this.title = questionnaire.title();
        this.done = questionnaire.isDone();
        this.questions = questionnaire.stream().map(q -> new QuestionOutput(q)).toArray(QuestionOutput[]::new);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

    public QuestionOutput[] getQuestions() {
        return questions;
    }
}