package com.travelnotes.lms1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActive {

    private String userName;
    private int ActiveDays;
    private int txtNum;
}
