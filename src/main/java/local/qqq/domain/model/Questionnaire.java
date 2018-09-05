package local.qqq.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Questionnaire {
    private String name;
    private List<Question> questions;

    public Questionnaire(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public String name() {
        return name;
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
}