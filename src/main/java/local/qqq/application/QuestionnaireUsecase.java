package local.qqq.application;

public interface QuestionnaireUseCase {
    public QuestionnaireResponse create(String title);
    public QuestionnaireResponse find(int id);
    public QuestionnaireResponse addQuestion(int id, String statement, String[] options);
}