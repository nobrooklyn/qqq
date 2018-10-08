package local.qqq.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import local.qqq.application.AnswerOutput;
import local.qqq.application.QuestionOutput;
import local.qqq.application.QuestionnaireAnswerUseCase;
import local.qqq.application.QuestionnaireCreateUseCase;
import local.qqq.application.QuestionnaireDisplayUseCase;
import local.qqq.application.QuestionnaireOutput;

@RestController
@RequestMapping("/api/v1/questionnaire")
public class QuestionnaireResource {
    private final QuestionnaireCreateUseCase creator;
    private final QuestionnaireDisplayUseCase displayer;
    private final QuestionnaireAnswerUseCase answer;

    @Autowired
    public QuestionnaireResource(QuestionnaireCreateUseCase creator, QuestionnaireDisplayUseCase displayer,
            QuestionnaireAnswerUseCase answer) {
        this.creator = creator;
        this.displayer = displayer;
        this.answer = answer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<QuestionnaireOutput> create(String title) {
        QuestionnaireOutput output = creator.create(title);
        return new Response<>(HttpStatus.CREATED, "created a new questionnaire", output);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<QuestionnaireOutput> find(@PathVariable int id) {
        QuestionnaireOutput output = displayer.find(id);
        return new Response<>(HttpStatus.OK, "found a questionnaire", output);
    }

    @PostMapping("/{id}/q")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<QuestionnaireOutput> add(@PathVariable int id, String statement, String... options) {
        QuestionnaireOutput output = creator.addQuestion(id, statement, options);
        return new Response<>(HttpStatus.CREATED, "add a question to questionnaire", output);
    }

    @GetMapping("/{id}/q")
    @ResponseStatus(HttpStatus.OK)
    public Response<QuestionOutput[]> dispalyQuestions(@PathVariable int id) {
        QuestionOutput[] output = displayer.find(id).getQuestions();
        return new Response<>(HttpStatus.OK, "found questions", output);
    }

    @PostMapping("/{id}/q/{qid}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<AnswerOutput> anwswerQuestion(@PathVariable int id, @PathVariable int qid, String... answers) {
        AnswerOutput output = answer.write(id, qid, answers);
        return new Response<>(HttpStatus.CREATED, "accepted answers", output);
    }

}