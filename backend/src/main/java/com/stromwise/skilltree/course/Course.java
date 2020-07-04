package com.stromwise.skilltree.course;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private String name;
    private String description;
    private String url;
    private CourseType type;
    private boolean premium;
}
