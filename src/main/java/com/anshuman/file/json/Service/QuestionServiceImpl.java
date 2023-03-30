package com.anshuman.file.json.Service;

import com.anshuman.file.json.Entity.QuestFile;
import com.anshuman.file.json.Entity.Question;
import com.anshuman.file.json.Repository.QuestFileRepository;
import com.anshuman.file.json.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestFileRepository questFileRepository;

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<QuestFile> getQuestFilesById(String questId) {
        return questFileRepository.findAllByQuestion_QuestId(questId);
    }

    @Override
    public Question getQuestionById(String questId) {
        return questionRepository.findById(questId).get();
    }

}
