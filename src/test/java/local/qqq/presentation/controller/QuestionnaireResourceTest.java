package local.qqq.presentation.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import local.qqq.application.QuestionnaireCreateInteractor;
import local.qqq.application.QuestionnaireCreateUseCase;
import local.qqq.application.QuestionnaireDisplayInteractor;
import local.qqq.application.QuestionnaireDisplayUseCase;
import local.qqq.application.QuestionnaireOutput;
import local.qqq.infrastructure.database.LocalQuestionnaireDatabase;
import local.qqq.infrastructure.util.LocalIdGenerator;

public class QuestionnaireResourceTest {
    @Test
    public void test_create_and_find() {
        QuestionnaireCreateUseCase creaotr = new QuestionnaireCreateInteractor(new LocalIdGenerator(),
                new LocalQuestionnaireDatabase());
        QuestionnaireDisplayUseCase displayer = new QuestionnaireDisplayInteractor(new LocalQuestionnaireDatabase());
        QuestionnaireResource resource = new QuestionnaireResource(creaotr, displayer);
        Response<QuestionnaireOutput> res = resource.create("test");

        assertThat(res.getEntity().getId(), is(1));
        assertThat(res.getStatus(), is(201));
        assertThat(res.getReason(), is(HttpStatus.CREATED.getReasonPhrase()));
        assertThat(res.getMessage(), is("created a new questionnaire"));
        assertThat(res.getTimestamp(), not(nullValue()));

        res = resource.find(1);

        assertThat(res.getStatus(), is(200));
        assertThat(res.getReason(), is(HttpStatus.OK.getReasonPhrase()));
        assertThat(res.getMessage(), is("found a questionnaire"));
        assertThat(res.getEntity().getTitle(), is("test"));
        assertThat(res.getEntity().getId(), is(1));
        assertThat(res.getEntity().isDone(), is(false));

        String[] options = null;
        res = resource.add(1, "test question 1", options);
        assertThat(res.getEntity().getQuestions().length, is(1));
        assertThat(res.getEntity().getQuestions()[0].id(), is(1));
        assertThat(res.getEntity().getQuestions()[0].statement(), is("test question 1"));

        res = resource.add(1, "test question 2", "option 1", "option 2");
        assertThat(res.getEntity().getQuestions().length, is(2));
        assertThat(res.getEntity().getQuestions()[0].id(), is(1));
        assertThat(res.getEntity().getQuestions()[0].statement(), is("test question 1"));
        assertThat(res.getEntity().getQuestions()[1].id(), is(2));
        assertThat(res.getEntity().getQuestions()[1].statement(), is("test question 2"));

    }
}