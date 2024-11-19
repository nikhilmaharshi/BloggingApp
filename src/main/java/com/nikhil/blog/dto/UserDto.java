package com.nikhil.blog.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {
    private Integer userId;

    @NotBlank(message = "User Name is mandatory")
    private String username;

    @NotBlank(message = "User Password is mandatory")
    @Size(min = 5, max = 10, message = "Password must be in between 5 and 10 characters")
    private String password;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password=password;
    }

    @NotBlank
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "About section is mandatory")
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
