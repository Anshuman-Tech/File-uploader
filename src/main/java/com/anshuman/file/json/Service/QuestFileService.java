package com.anshuman.file.json.Service;

import com.anshuman.file.json.Entity.QuestFile;
import org.springframework.web.multipart.MultipartFile;

public interface QuestFileService {

    void saveQuestFile(QuestFile file);
}
