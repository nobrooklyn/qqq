package local.qqq.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import local.qqq.domain.model.Questionnaire;
import local.qqq.domain.model.QuestionnaireRepository;

@Service
public class QuestionnaireDisplayInteractor implements QuestionnaireDisplayUseCase {
    private final QuestionnaireRepository repository;

    @Autowired
    public QuestionnaireDisplayInteractor(QuestionnaireRepository repository) {
        this.repository = repository;
    }

    @Override
    public QuestionnaireOutput find(int id) {
        Questionnaire questionnaire = repository.find(id);
        return new QuestionnaireOutput(questionnaire);
    }

}