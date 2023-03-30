package com.anshuman.file.json.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestFile {

    @Id
    private String questFileId;

    private String questFileType;

    @Lob
    private byte[] questFile;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name="questId",referencedColumnName="questId")
    private Question question;
}
