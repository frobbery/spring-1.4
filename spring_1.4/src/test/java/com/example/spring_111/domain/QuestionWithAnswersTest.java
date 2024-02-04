package com.example.spring_111.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Класс QuestionWithAnswers")
class QuestionWithAnswersTest {

    @DisplayName("Должен возвращать строку из вопроса в правильном формате")
    @Test
    void shouldReturnRightFormat_whenToStringCalled() {
        //given
        var question = new QuestionWithAnswers("question", "answer1", new ArrayList<>(
                Arrays.asList("answer1", "answer2")));

        //when
        var printedQuestion = question.toString();

        //then
        assertThat(printedQuestion)
                .matches("question\\?\n1\\. (answer1|answer2)\n2\\. (answer1|answer2)\n");
    }
}