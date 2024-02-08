package com.example.spring_111.dao;

import com.example.spring_111.domain.QuestionWithAnswers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс QuestionDaoImpl")
class QuestionDaoImplTest {

    private final QuestionDaoImpl sut = new QuestionDaoImpl("/example.csv");

    @DisplayName("Должен добавлять вопрос")
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

    @Test
    @DisplayName("Должен добавлять вопросы из файла")
    void shouldAddQuestionsFromFile() throws IOException, URISyntaxException {
        //when
        sut.addQuestionsFromFile();

        //then
        assertEquals(sut.getAllQuestions().size(), 3);
    }

    private QuestionWithAnswers question() {
        return new QuestionWithAnswers("someQuestion", "someAnswer", List.of("someAnswer"));
    }
}