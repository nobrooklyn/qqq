package local.qqq.domain.model;

public interface QuestionnaireRepository {
    public void save(Questionnaire questionnaire);

    public Questionnaire find(int id);
}