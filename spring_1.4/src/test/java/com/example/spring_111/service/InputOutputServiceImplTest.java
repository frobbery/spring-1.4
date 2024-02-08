package com.example.spring_111.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Класс InputOutputServiceImpl")
class InputOutputServiceImplTest {

    private final InputOutputServiceImpl sut = Mockito.spy(InputOutputServiceImpl.class);

    @Test
    @DisplayName("Должен взвращать введенные имя и фамилию")
    void shouldReturnPrintedNameAndSurname() {
        //given
        var name = "first last";
        var inputStream = new ByteArrayInputStream(name.getBytes());
        System.setIn(inputStream);
        var sut = Mockito.spy(InputOutputServiceImpl.class);

        //when
        var result = sut.getStudentLastAndFirstName();

        //then
        verify(sut, times(1)).print("Print your first and lastname");
        assertEquals(name, result);
    }

    @Test
    @DisplayName("Должен заново просить ввести имя и фамилию, когда введено в неверном формате")
    void shouldAskAgainWhenInputNameOfWrongFormat() {
        //given
        var inputStream = new ByteArrayInputStream("wrongName\nfirst last".getBytes());
        System.setIn(inputStream);
        var sut = Mockito.spy(InputOutputServiceImpl.class);

        //when
        var result = sut.getStudentLastAndFirstName();

        //then
        verify(sut, times(1)).print("Print your first and lastname");
        verify(sut, times(1)).print("Wrong format. Print your first and lastname");
        assertEquals("first last", result);
    }

    @Test
    @DisplayName("Должен выводить строку с результатом студента")
    void shouldPrintResultOfAStudent() {
        //when
        sut.printResultOfStudent("studentName");

        //then
        verify(sut, times(1)).print("Result of student studentName:");
    }

    @ParameterizedTest
    @MethodSource("answerResultsAndMessages")
    @DisplayName("Должен выводить строку с результатом ответа")
    void shouldPrintAnswerResult(Boolean result, String expectedPrint) {
        //when
        sut.printAnswerResult(result);

        //then
        verify(sut, times(1)).print(expectedPrint);
    }

    @ParameterizedTest
    @MethodSource("testResultsAndMessages")
    @DisplayName("Должен выводить строку с результатом теста")
    void shouldPrintTestResult(Boolean result, String expectedPrint) {
        //when
        sut.printTestResult(result);

        //then
        verify(sut, times(1)).print(expectedPrint);
    }

    private static Stream<Arguments> answerResultsAndMessages() {
        return Stream.of(
                Arguments.of(true, "Your answered right!"),
                Arguments.of(false, "Your answered wrong!")
        );
    }

    private static Stream<Arguments> testResultsAndMessages() {
        return Stream.of(
                Arguments.of(true, "Congratulations!!! You passed the test."),
                Arguments.of(false, "You did not pass the test :(")
        );
    }
}