package local.qqq.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import local.qqq.domain.model.IdGenerator;
import local.qqq.domain.model.Questionnaire;
import local.qqq.domain.model.QuestionnaireRepository;

@Service
public class QuestionnaireUsecase {
    private final QuestionnaireRepository repository;
    private final IdGenerator idGenerator;

    @Autowired
    public QuestionnaireUsecase(IdGenerator idGenerator, QuestionnaireRepository repository) {
        this.idGenerator = idGenerator;
        this.repository = repository;
    }

    public int create(String title) {
        Questionnaire questionnaire = new Questionnaire(idGenerator.newId(), title);
        repository.save(questionnaire);
        return questionnaire.id();
    }

    public Questionnaire find(int id) {
        Questionnaire questionnaire = repository.find(id);
        return questionnaire;
    }
}