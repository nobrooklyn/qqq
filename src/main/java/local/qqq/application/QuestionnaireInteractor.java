package local.qqq.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import local.qqq.domain.model.IdGenerator;
import local.qqq.domain.model.Question;
import local.qqq.domain.model.Questionnaire;
import local.qqq.domain.model.QuestionnaireRepository;

@Service
public class QuestionnaireInteractor implements QuestionnaireUseCase {
    private final QuestionnaireRepository repository;
    private final IdGenerator idGenerator;

    @Autowired
    public QuestionnaireInteractor(IdGenerator idGenerator, QuestionnaireRepository repository) {
        this.idGenerator = idGenerator;
        this.repository = repository;
    }

    @Override
    public QuestionnaireResponse create(String title) {
        Questionnaire questionnaire = new Questionnaire(idGenerator.newId(), title);
        repository.save(questionnaire);
        return new QuestionnaireResponse(questionnaire);
    }

    @Override
    public QuestionnaireResponse find(int id) {
        Questionnaire questionnaire = repository.find(id);
        return new QuestionnaireResponse(questionnaire);
    }

    @Override
    public QuestionnaireResponse addQuestion(int id, String statement, String[] options) {
        Questionnaire questionnaire = repository.find(id);
        if (options == null) {
            questionnaire.add(new Question(questionnaire.count() + 1, statement));
        } else {
            questionnaire.add(new Question(questionnaire.count() + 1, statement, options));
        }
        // TODO: add concurrency (duplicate Question id)
        repository.save(questionnaire);
        return new QuestionnaireResponse(questionnaire);
    }
}