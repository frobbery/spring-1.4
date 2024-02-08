package com.example.spring_111.service;

import com.example.spring_111.domain.QuestionWithAnswers;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;

@Component
public class InputOutputServiceImpl implements InputOutputService {

    private final Scanner scanner = new Scanner(System.in);

    private final PrintStream printStream = new PrintStream(System.out);

    private final static String RIGHT_ANSWER = "Your answered right!";

    private final static String WRONG_ANSWER = "Your answered wrong!";

    private final static String RESULT = "Result of student {0}:";

    private final static String TEST_PASSED = "Congratulations!!! You passed the test.";

    private final static String TEST_FAILED = "You did not pass the test :(";

    @Override
    public void printAllQuestions(List<QuestionWithAnswers> questions) {
        for (QuestionWithAnswers question : questions) {
            print(question.toString());
        }
    }

    @Override
    public String getStudentLastAndFirstName() {
        print("Print your first and lastname");
        var firstAndLastName = scanner.nextLine();
        while (!firstAndLastName.contains(" ") && firstAndLastName.split(" ").length != 2) {
            print("Wrong format. Print your first and lastname");
            firstAndLastName = scanner.nextLine();
        }
        return firstAndLastName;
    }

    @Override
    public String printQuestionAndGetAnswer(QuestionWithAnswers question) {
        printStream.println(question);
        return scanner.nextLine();
    }

    @Override
    public void printAnswerResult(boolean result) {
        if (result) {
            print(RIGHT_ANSWER);
        } else {
            print(WRONG_ANSWER);
        }
    }
    @Override
    public void printResultOfStudent(String studentName) {
        print(MessageFormat.format(RESULT, studentName));
    }

    @Override
    public void printTestResult(boolean passed) {
        if (passed) {
            print(TEST_PASSED);
        } else {
            print(TEST_FAILED);
        }
    }

    protected void print(String toPrint) {
        printStream.println(toPrint);
    }
}
