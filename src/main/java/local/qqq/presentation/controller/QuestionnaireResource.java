package local.qqq.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import local.qqq.application.QuestionnaireResponse;
import local.qqq.application.QuestionnaireUseCase;

@RestController
@RequestMapping("/api/v1/questionnaire")
public class QuestionnaireResource {
    private final QuestionnaireUseCase uc;

    @Autowired
    public QuestionnaireResource(QuestionnaireUseCase uc) {
        this.uc = uc;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<QuestionnaireResponse> create(String title) {
        QuestionnaireResponse response = uc.create(title);
        return new Response<>(HttpStatus.CREATED, "created a new questionnaire", response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<QuestionnaireResponse> find(@PathVariable int id) {
        QuestionnaireResponse response = uc.find(id);
        return new Response<>(HttpStatus.OK, "found a questionnaire", response);
    }

    @PostMapping("/{id}/q")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<QuestionnaireResponse> add(@PathVariable int id, String statement, String... options) {
        QuestionnaireResponse response = uc.addQuestion(id, statement, options);
        return new Response<>(HttpStatus.CREATED, "add a question to questionnaire", response);
    }

}