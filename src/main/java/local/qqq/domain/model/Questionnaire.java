package local.qqq.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Questionnaire {
    private int id;
    private String title;
    private List<Question> questions;
    private QuestionnaireStatus status;

    public Questionnaire(int id, String title) {
        this.id = id;
        this.title = title;
        this.questions = new ArrayList<>();
        this.status = QuestionnaireStatus.TODO;
    }

    public int id() {
        return id;
    }

    public String title() {
        return title;
    }

    public void add(Question q) {
        questions.add(q);
    }

    public int count() {
        return questions.size();
    }

    public Question get(int index) {
        return questions.get(index);
    }

    public void forEach(Consumer<? super Question> action) {
        questions.forEach(action);
    }

    public Stream<Question> stream() {
        return questions.stream();
    }

    public void done() throws AnswerNotCompleteException {
        List<Integer> notEnteredNo = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            try {
                q.done();
            } catch (AnswerNotFoundException e) {
                notEnteredNo.add(i);
            }
        }
        if (notEnteredNo.size() > 0) {
            rollbackDone();
            throw new AnswerNotCompleteException(notEnteredNo);
        }
        status = QuestionnaireStatus.DONE;
    }

    private void rollbackDone() {
        status = QuestionnaireStatus.TODO;
        questions.stream().filter(q -> q.isDone()).forEach(q -> q.todo());
    }

    public void cancel(int index) {
        questions.get(index).cancel();
        rollbackDone();
    }

    public boolean isDone() {
        return status == QuestionnaireStatus.DONE;
    }
}