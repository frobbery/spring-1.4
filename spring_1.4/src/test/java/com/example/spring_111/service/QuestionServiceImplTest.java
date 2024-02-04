package com.example.spring_111.service;

import com.example.spring_111.dao.QuestionDao;
import com.example.spring_111.domain.QuestionWithAnswers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Класс QuestionServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    private QuestionServiceImpl sut;

    @BeforeEach
    void setup() {
        this.sut = new QuestionServiceImpl(questionDao, "/example.csv", 1);
    }

    @Test
    @DisplayName("Должен добавлять вопросы из файла")
    void shouldAddQuestionsFromFile() throws IOException, URISyntaxException {
        //when
        sut.addQuestions();

        //then
        verify(questionDao, times(3)).addQuestion(any());
    }

    @Test
    @DisplayName("Должен выводить все вопросы")
    void shouldPrintAllQuestions() {
        //given
        var question = question();
        when(questionDao.getAllQuestions()).thenReturn(List.of(question));
        var printStream = mock(PrintStream.class);

        //when
        sut.printAllQuestionsWithAnswers(printStream);

        //then
        verify(questionDao, times(1)).getAllQuestions();
        verify(printStream, times(1)).println(question);
    }

    @Test
    @DisplayName("Должен вовзращать true, когда тестирование пройдено")
    void shouldReturnTrue_whenTestingPassed() {
        //given
        var question = question();
        when(questionDao.getAllQuestions()).thenReturn(List.of(question));
        var printStream = mock(PrintStream.class);
        var scanner = new Scanner(new ByteArrayInputStream("First Last\nanswer1".getBytes()));

        //when
        var result = sut.conductTesting(printStream, scanner);

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
        var printStream = mock(PrintStream.class);
        var scanner = new Scanner(new ByteArrayInputStream("First Last\nanswer2".getBytes()));

        //when
        var result = sut.conductTesting(printStream, scanner);

        //then
        assertFalse(result);
        verify(questionDao, times(1)).getAllQuestions();
    }

    private QuestionWithAnswers question() {
        return new QuestionWithAnswers("question", "answer1", List.of("answer1", "answer2"));
    }
}