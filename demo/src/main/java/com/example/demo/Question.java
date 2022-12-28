package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Question {
    private @Id @GeneratedValue Long id;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;
    private String category;
    private String type;



    public Question(String difficulty, String question, String correct_answer, String[] wrong_answers, String category, String type) {
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = wrong_answers;
        this.category = category;
        this.type = type;
    }

    public Question() {}
}
