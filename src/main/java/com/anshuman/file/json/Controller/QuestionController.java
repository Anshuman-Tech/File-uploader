package com.anshuman.file.json.Controller;

import com.anshuman.file.json.DTO.QuestionResponse;
import com.anshuman.file.json.Entity.QuestFile;
import com.anshuman.file.json.Entity.Question;
import com.anshuman.file.json.Service.QuestFileService;
import com.anshuman.file.json.Service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/question")
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestFileService questFileService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/uploadques")
    public ResponseEntity<String> uploadQuestion(@RequestParam("file")MultipartFile[] file,
                                                 @RequestParam("ques")String question) throws Exception {

        Question quest;

        //Convert String data into JSON
        try{
            quest = objectMapper.readValue(question,Question.class);
        }catch (JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }



        //Save question
        Question savedQuestion = questionService.saveQuestion(quest);

        //Save question file
//        List<QuestFile> questFiles = new ArrayList<>();
        for(MultipartFile f:file){
            String fileName = StringUtils.cleanPath(f.getOriginalFilename());
            try {
                // checks for any invalid character in the file name
                if (fileName.contains("..")) {
                    throw new Exception("FileName contains invalid path sequence");
                }
                QuestFile questFile = new QuestFile(fileName, f.getContentType(), f.getBytes(), savedQuestion);
//                questFiles.add(questFile);
                questFileService.saveQuestFile(questFile);
            } catch(Exception e){
            throw new Exception("Could not save File: " + fileName);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Uploaded");
    }


    @GetMapping("/getAllQuests")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }


    public List<QuestFile> getQuestFileById(String questId){
        return questionService.getQuestFilesById(questId);
    }


    @GetMapping("/getQuestion/{questId}")
    public ResponseEntity getQuestionById(@PathVariable() String questId){
        Question question = questionService.getQuestionById(questId);
        List<HashMap<String,String>> res = new ArrayList<>();
        List<QuestFile> files = getQuestFileById(questId);
        for(QuestFile file:files){
            HashMap hm = new HashMap<>();
            hm.put(file.getQuestFileId().toString(),file.getQuestFileType().toString());

            //For creating download link
//            hm.put("Download Link",ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(file.getQuestFileType()))
//                    .header(HttpHeaders.CONTENT_DISPOSITION,
//                            "file; fileId=\"" + file.getQuestFileId() + "\"")
//                    .body(new ByteArrayResource(file.getQuestFile())).toString());

            res.add(hm);
        }
        QuestionResponse response = QuestionResponse.builder()
                .questId(question.getQuestId())
                .questName(question.getQuestName())
                .questDescription(question.getQuestDescription())
                .questFile(res)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
