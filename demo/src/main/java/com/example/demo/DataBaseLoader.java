package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataBaseLoader implements CommandLineRunner {

    private final QuestionRepository repository;

    @Autowired
    public DataBaseLoader(QuestionRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        QuestionGenerator ql = new QuestionGenerator();
        ArrayList<Question> questions = ql.generateQuestions(new URL("https://opentdb.com/api.php?amount=50&category=15&type=multiple"));
        for (Question q : questions) {
            System.out.println(q.getQuestion());
            repository.save(q);
        }
    }
}
