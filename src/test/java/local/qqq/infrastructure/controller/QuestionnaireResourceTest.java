package local.qqq.infrastructure.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import local.qqq.application.QuestionnaireUsecase;
import local.qqq.infrastructure.database.LocalQuestionnaireDatabase;
import local.qqq.infrastructure.util.LocalIdGenerator;

public class QuestionnaireResourceTest {
    @Test
    public void test_create_and_find() {
        QuestionnaireUsecase uc = new QuestionnaireUsecase(new LocalIdGenerator(), new LocalQuestionnaireDatabase());
        QuestionnaireResource resource = new QuestionnaireResource(uc);
        IdResponse idRes = resource.create("test");

        assertThat(idRes.getId(), is(1));

        QuestionnaireResponse queRes = resource.find(1);

        assertThat(queRes.getTitle(), is("test"));
        assertThat(queRes.getId(), is(1));
        assertThat(queRes.isDone(), is(false));
    }
}