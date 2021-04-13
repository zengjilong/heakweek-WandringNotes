package com.travelsnotes.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
//    int articleId;
    String title;
    String content;
    int tag;
    String img;
    Date date;
    int userId;

}
