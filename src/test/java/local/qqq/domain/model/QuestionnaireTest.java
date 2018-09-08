package local.qqq.domain.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;

public class QuestionnaireTest {
    @Test
    public void test_scenario_create_question() throws UnmodifiableAnswerException, AnswerNotCompleteException {
        // create a questionnaire
        Questionnaire q = new Questionnaire("Test questionnaire");
        Question q1 = new Question("あなたの名前は？");
        Question q2 = new Question("あなたの性別は？", "男性", "女性");

        q.add(q1);
        q.add(q2);

        assertThat(q.name(), is("Test questionnaire"));
        assertThat(q.count(), is(2));
        q.forEach(qu -> assertFalse(qu.hasAnswer()));

        assertThat(q.get(0).statement(), is("あなたの名前は？"));
        assertFalse(q.get(0).hasOptions());
        assertThat(q.get(0).answer(), is(Optional.empty()));

        assertThat(q.get(1).statement(), is("あなたの性別は？"));
        assertTrue(q.get(1).hasOptions());
        assertThat(q.get(1).options(), is(new String[] { "男性", "女性" }));

        // entered answers
        q1.answer("山田 太郎");
        assertThat(q.get(0).answer().get().length, is(1));
        assertThat(q.get(0).answer().get()[0], is("山田 太郎"));

        q2.answer("男性");
        assertThat(q.get(1).answer().get().length, is(1));
        assertThat(q.get(1).answer().get()[0], is("男性"));

        q.forEach(qu -> assertTrue(qu.hasAnswer()));

        assertThat(q.stream().filter(qu -> qu.hasOptions()).count(), is(1L));

        // done questionnaire
        q.done();
        assertTrue(q.isDone());
        try {
            q1.answer("鈴木 一郎");
            fail("exception does not occured.");
        } catch (UnmodifiableAnswerException e) {
            assertThat(e.getMessage(), is("already done."));
        }

        // q2 cancel
        q.cancel(1);
        try {
            q.done();
            fail("exception does not occured.");
        } catch (AnswerNotCompleteException e) {
            assertThat(e.getMessage(), is("not complete.[1]"));
            assertThat(e.notEnteredNo().size(), is(1));
            assertThat(e.notEnteredNo().get(0), is(1));
            assertFalse(q.isDone());
        }
    }

    @Test
    public void test_scenario_error_question() {
        try {
            String[] options = null;
            new Question("あなたの性別は？", options);
            fail("not occured exception");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("options must be two or more items."));
        }

        try {
            new Question("あなたの性別は？", "男性");
            fail("not occured exception");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("options must be two or more items."));
        }
    }
}