package local.qqq.infrastructure.database;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import local.qqq.domain.model.Questionnaire;
import local.qqq.domain.model.QuestionnaireRepository;

@Component
public class LocalQuestionnaireDatabase implements QuestionnaireRepository {
    private static Map<Integer, Questionnaire> db = new ConcurrentHashMap<>();

    @Override
    public void save(Questionnaire questionnaire) {
        db.put(questionnaire.id(), questionnaire);
    }

    @Override
    public Questionnaire find(int id) {
        return db.get(id);
    }

}