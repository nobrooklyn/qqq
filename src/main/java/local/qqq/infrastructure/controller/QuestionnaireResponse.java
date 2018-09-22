package local.qqq.infrastructure.controller;

import local.qqq.domain.model.Questionnaire;

class QuestionnaireResponse {
    private Questionnaire questionnaire;

    QuestionnaireResponse(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public int getId() {
        return questionnaire.id();
    }

    public String getTitle() {
        return questionnaire.title();
    }

    public boolean isDone() {
        return questionnaire.isDone();
    }
}