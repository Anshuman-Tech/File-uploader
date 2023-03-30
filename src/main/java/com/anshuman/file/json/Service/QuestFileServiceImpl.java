package com.anshuman.file.json.Service;

import com.anshuman.file.json.Entity.QuestFile;
import com.anshuman.file.json.Repository.QuestFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QuestFileServiceImpl implements QuestFileService{

    @Autowired
    private QuestFileRepository questFileRepository;

    @Override
    public void saveQuestFile(QuestFile file) {
        questFileRepository.save(file);
    }
}
