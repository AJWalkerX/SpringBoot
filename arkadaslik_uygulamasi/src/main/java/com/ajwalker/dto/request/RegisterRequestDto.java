package com.ajwalker.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Parent;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequestDto {
    @NotNull(message = "Kullanıcı adi boş geçilmez!")
    @Size(min = 3, max = 64, message = "Kullanıcı adı 3-64 karakter uzunluğunda olmalıdır.")
    String username;
    @NotEmpty
    @Size(min = 8, max = 64)
    @Pattern(
            message="Şifreniz 8-64 karakter uzunluğunda olmalıdır!",
            regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,64}$"
    )
    String password;
    @NotEmpty
    String rePassword;
    @Email(message = "Lütfen geçerli email adresi ile giriş yapınız!")
    String email;
}
