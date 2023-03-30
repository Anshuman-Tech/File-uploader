package com.anshuman.file.json.Repository;

import com.anshuman.file.json.Entity.QuestFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestFileRepository extends JpaRepository<QuestFile,String> {

    List<QuestFile> findAllByQuestion_QuestId(String questId);
}
