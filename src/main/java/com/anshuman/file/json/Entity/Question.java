package com.anshuman.file.json.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    private String questId;

    private String questName;
    private String questDescription;

    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<QuestFile> questFiles;
}
