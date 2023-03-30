package com.anshuman.file.json.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestFileDTO {

    private String questFileId;

    private byte[] questFile;
}
