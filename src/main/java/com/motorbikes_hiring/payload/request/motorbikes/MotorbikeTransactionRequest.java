package com.motorbikes_hiring.payload.request.motorbikes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
public class MotorbikeTransactionRequest implements Serializable {
    private Long user_id;
}
