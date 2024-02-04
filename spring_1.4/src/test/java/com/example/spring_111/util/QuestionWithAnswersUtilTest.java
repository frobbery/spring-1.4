package com.example.spring_111.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Класс QuestionWithAnswersUtil")
class QuestionWithAnswersUtilTest {

    @DisplayName("Должен формировать вопрос с ответами из csv-строки")
    @Test
    void shouldReturnQuestion_whenGetQuestionFromLine() {
        //given
        var line = "someQuestion,someAnswer";

        //when
        var result = QuestionWithAnswersUtil.getQuestionWithAnswersFromLine(line);

        //then
        assertThat(result).hasFieldOrPropertyWithValue("question", "someQuestion")
                .hasFieldOrPropertyWithValue("rightAnswer", "someAnswer")
                .hasFieldOrPropertyWithValue("answers", List.of("someAnswer"));
    }
}