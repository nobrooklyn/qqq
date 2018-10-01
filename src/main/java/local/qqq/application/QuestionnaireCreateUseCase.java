package local.qqq.application;

public interface QuestionnaireCreateUseCase {
    public QuestionnaireOutput create(String title);
    public QuestionnaireOutput addQuestion(int id, String statement, String[] options);
}