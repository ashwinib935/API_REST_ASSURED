package com.qa.data;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    public String name;
    public String job;
    public String id;
    public String createdAt;


    public Users(String name, String job) {
        this.name = name;
        this.job = job;
    }

}
