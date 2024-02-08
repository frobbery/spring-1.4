package com.example.spring_111.dao;

import com.example.spring_111.domain.QuestionWithAnswers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.spring_111.util.QuestionWithAnswersUtil.getQuestionWithAnswersFromLine;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final List<QuestionWithAnswers> questions = new ArrayList<>();

    private final String questionPath;

    public QuestionDaoImpl(@Value("${questions.path}") String questionPath) {
        this.questionPath = questionPath;
    }

    @Override
    public void addQuestion(QuestionWithAnswers question) {
        questions.add(question);
    }

    @Override
    public List<QuestionWithAnswers> getAllQuestions() {
        return questions;
    }

    @Override
    public void addQuestionsFromFile() throws IOException, URISyntaxException {
        var uri = Objects.requireNonNull(getClass().getResource(questionPath)).toURI();
        Files.readAllLines(Paths.get(uri)).forEach(line -> addQuestion(getQuestionWithAnswersFromLine(line)));
    }
}
