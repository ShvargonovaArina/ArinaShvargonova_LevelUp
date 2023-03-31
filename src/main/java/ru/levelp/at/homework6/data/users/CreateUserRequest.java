package ru.levelp.at.homework6.data.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {
    private String name;
    private String email;
    private String gender;
    private String status;
}
