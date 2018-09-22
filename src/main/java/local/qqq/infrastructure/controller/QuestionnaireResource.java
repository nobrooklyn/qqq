package local.qqq.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import local.qqq.application.QuestionnaireUsecase;
import local.qqq.domain.model.Questionnaire;

@RestController
@RequestMapping("/api/v1/questionnaire")
public class QuestionnaireResource {
    private final QuestionnaireUsecase uc;

    @Autowired
    public QuestionnaireResource(QuestionnaireUsecase uc) {
        this.uc = uc;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse create(String title) {
        int newId = uc.create(title);
        return new IdResponse(newId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionnaireResponse find(@PathVariable int id) {
        Questionnaire resutl = uc.find(id);
        return new QuestionnaireResponse(resutl);
    }
}