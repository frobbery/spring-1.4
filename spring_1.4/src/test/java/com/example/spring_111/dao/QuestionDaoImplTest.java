package com.example.spring_111.dao;

import com.example.spring_111.domain.QuestionWithAnswers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс QuestionDaoImpl")
class QuestionDaoImplTest {

    private final QuestionDaoImpl sut = new QuestionDaoImpl();

    @DisplayName("Должен добавлять вопросы")
    @Test
    void shouldAddQuestion() {
        //given
        var question = question();

        //when
        sut.addQuestion(question);

        //then
        assertEquals(sut.getAllQuestions().size(), 1);
    }

    @DisplayName("Должен выдавать все вопросы")
    @Test
    void shouldGetAllQuestions() {
        //given
        var question = question();
        sut.addQuestion(question);

        //when
        var result = sut.getAllQuestions();

        //then
        Assertions.assertThat(result)
                .contains(question)
                .hasSize(1);
    }

    private QuestionWithAnswers question() {
        return new QuestionWithAnswers("someQuestion", "someAnswer", List.of("someAnswer"));
    }
}