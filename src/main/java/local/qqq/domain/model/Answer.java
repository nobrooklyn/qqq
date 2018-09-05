package local.qqq.domain.model;

class Answer {
    private String[] enteredAnswers;

    Answer(String... enteredAnswers) {
        this.enteredAnswers = enteredAnswers;
    }

    String[] enteredAnswers() {
        return this.enteredAnswers;
    }
}