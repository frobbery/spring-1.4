package com.example.spring_111.service;

import com.example.spring_111.dao.QuestionDao;
import com.example.spring_111.domain.QuestionWithAnswers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionDao questionDao;

    private final Integer passingNumber;

    private final InputOutputService inputOutputService;

    public QuestionServiceImpl(QuestionDao questionDao,
                               @Value("${questions.number}") Integer passingNumber,
                               InputOutputService inputOutputService) throws IOException, URISyntaxException {
        this.questionDao = questionDao;
        questionDao.addQuestionsFromFile();
        this.passingNumber = passingNumber;
        this.inputOutputService = inputOutputService;
    }

    @Override
    public void printAllQuestionsWithAnswers() {
        inputOutputService.printAllQuestions(questionDao.getAllQuestions());
    }

    @Override
    public Boolean conductTesting() {
        var studentName = inputOutputService.getStudentLastAndFirstName();
        var questions = questionDao.getAllQuestions();
        int numberOfPoints = 0;
        for (QuestionWithAnswers question : questions) {
            var answer = inputOutputService.printQuestionAndGetAnswer(question);
            var answerIsRight = question.getRightAnswer().equals(answer);
            inputOutputService.printAnswerResult(answerIsRight);
            if (answerIsRight) {
                numberOfPoints++;
            }
        }
        inputOutputService.printResultOfStudent(studentName);
        var testPassed = numberOfPoints >= passingNumber;
        inputOutputService.printTestResult(testPassed);
        return testPassed;
    }
}
