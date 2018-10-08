package local.qqq.application;

import local.qqq.domain.model.Questionnaire;
import local.qqq.domain.model.QuestionnaireRepository;
import local.qqq.domain.model.UnmodifiableAnswerException;

public class QuestionnaireAnswerInteractor implements QuestionnaireAnswerUseCase {
    private final QuestionnaireRepository repository;

    public QuestionnaireAnswerInteractor(QuestionnaireRepository repository) {
        this.repository = repository;
    }

    @Override
    public AnswerOutput write(int id, int qid, String... enteredAnswers) {
        try {
            Questionnaire questionnaire = repository.find(id);
            questionnaire.get(qid).answer(enteredAnswers);
            return new AnswerOutput();
        } catch (UnmodifiableAnswerException e) {
            e.printStackTrace();
            return new AnswerOutput(new Exception[] { e });
        }
    }

}