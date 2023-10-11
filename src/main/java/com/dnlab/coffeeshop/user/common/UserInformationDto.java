package com.dnlab.coffeeshop.user.common;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserInformationDto {
    private String name;
    private String nickname;
    private Integer mileage;
    private Timestamp createdDate;
}
