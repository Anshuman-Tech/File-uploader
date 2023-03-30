package com.anshuman.file.json.Service;

import com.anshuman.file.json.Entity.QuestFile;
import com.anshuman.file.json.Entity.Question;

import java.util.List;

public interface QuestionService {

    Question saveQuestion(Question question);

    List<Question> getAllQuestions();

    List<QuestFile> getQuestFilesById(String questId);

    Question getQuestionById(String questId);
}
