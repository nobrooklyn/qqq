package local.qqq.presentation.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import local.qqq.application.QuestionnaireInteractor;
import local.qqq.application.QuestionnaireResponse;
import local.qqq.application.QuestionnaireUseCase;
import local.qqq.infrastructure.database.LocalQuestionnaireDatabase;
import local.qqq.infrastructure.util.LocalIdGenerator;

public class QuestionnaireResourceTest {
    @Test
    public void test_create_and_find() {
        QuestionnaireUseCase uc = new QuestionnaireInteractor(new LocalIdGenerator(), new LocalQuestionnaireDatabase());
        QuestionnaireResource resource = new QuestionnaireResource(uc);
        Response<QuestionnaireResponse> res = resource.create("test");

        assertThat(res.getEntity().getId(), is(1));

        res = resource.find(1);

        assertThat(res.getEntity().getTitle(), is("test"));
        assertThat(res.getEntity().getId(), is(1));
        assertThat(res.getEntity().isDone(), is(false));

        res = resource.add(1, "test question 1", null);
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