package com.motorbikes_hiring.payload.request.forumRequest;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerUpdateRequest {
    private String content;
}
