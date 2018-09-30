package local.qqq.application;

import local.qqq.domain.model.Question;
import local.qqq.domain.model.Questionnaire;

public class QuestionnaireResponse {
    private final int id;
    private final String title;
    private final boolean done;
    private final Question[] questions;

    QuestionnaireResponse(Questionnaire questionnaire) {
        this.id = questionnaire.id();
        this.title = questionnaire.title();
        this.done = questionnaire.isDone();
        this.questions = questionnaire.stream().toArray(Question[]::new);
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

    public Question[] getQuestions() {
        return questions;
    }
}