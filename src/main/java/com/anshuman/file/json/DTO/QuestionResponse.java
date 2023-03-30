package com.anshuman.file.json.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {

    private String questId;
    private String questName;
    private String questDescription;
    private List<HashMap<String,String>> questFile;
}
