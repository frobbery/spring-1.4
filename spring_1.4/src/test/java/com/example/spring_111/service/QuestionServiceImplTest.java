package com.example.spring_111.service;

import com.example.spring_111.dao.QuestionDao;
import com.example.spring_111.domain.QuestionWithAnswers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("Класс QuestionServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    @Mock
    private InputOutputService inputOutputService;

    private QuestionServiceImpl sut;

    @BeforeEach
    void setup() throws IOException, URISyntaxException {
        this.sut = new QuestionServiceImpl(questionDao, 1, inputOutputService);
    }

    @Test
    @DisplayName("Должен выводить все вопросы")
    void shouldPrintAllQuestions() {
        //given
        var questions = List.of(question());
        when(questionDao.getAllQuestions()).thenReturn(questions);

        //when
        sut.printAllQuestionsWithAnswers();

        //then
        verify(questionDao, times(1)).getAllQuestions();
        verify(inputOutputService, times(1)).printAllQuestions(questions);
    }

    @Test
    @DisplayName("Должен вовзращать true, когда тестирование пройдено")
    void shouldReturnTrue_whenTestingPassed() {
        //given
        var question = question();
        when(questionDao.getAllQuestions()).thenReturn(List.of(question));
        when(inputOutputService.printQuestionAndGetAnswer(question)).thenReturn("answer1");

        //when
        var result = sut.conductTesting();

        //then
        assertTrue(result);
        verify(questionDao, times(1)).getAllQuestions();
    }

    @Test
    @DisplayName("Должен вовзращать false, когда тестирование не пройдено")
    void shouldReturnFalse_whenTestingNotPassed() {
        //given
        var question = question();
        when(questionDao.getAllQuestions()).thenReturn(List.of(question));
        when(inputOutputService.printQuestionAndGetAnswer(question)).thenReturn("answer2");

        //when
        var result = sut.conductTesting();

        //then
        assertFalse(result);
        verify(questionDao, times(1)).getAllQuestions();
    }

    private QuestionWithAnswers question() {
        return new QuestionWithAnswers("question", "answer1", List.of("answer1", "answer2"));
    }
}