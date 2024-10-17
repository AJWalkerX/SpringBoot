package com.ajwalker.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateUserProfileRequestDto {
    @NotNull
    Long id;
    @Size(min = 2, max = 100)
    String name;
    String avatar;
    @Size(min = 2, max = 64)
    String username;
    @NotEmpty
    @Size(min = 8, max = 64)
    @Pattern(
            message="Şifreniz 8-64 karakter uzunluğunda olmalıdır!",
            regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,64}$"
    )
    String password;
    String email;
    String phone;
    Integer age;
}
