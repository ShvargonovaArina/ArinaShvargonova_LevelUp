package ru.levelp.at.homework6.data.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
