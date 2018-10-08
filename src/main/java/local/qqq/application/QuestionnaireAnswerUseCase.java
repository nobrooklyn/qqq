package local.qqq.application;

public interface QuestionnaireAnswerUseCase {
    public AnswerOutput write(int id, int qid, String... enteredAnswers);
}