package com.motorbikes_hiring.payload.request.searchParam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminSearchRequest {
    private String id ="";
    private String name = "";
}
