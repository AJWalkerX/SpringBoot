package com.ajwalker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddFollowRequestDto {
    /*
    * String username;
    * kullanıcı tarafından bu değerin hiç gönderilmemesi!
    * NotNull
    * body{
    *   //burada hiç bir keyword yok ise
    * }
    * username = null
    * body{
    *   username = " " -> NotEmpty yakalayamaz - NotBlank yakalayabilir(white-space yakalar!)
    * }
    * NotEmpty -> username = ""
    */
    @NotNull
    Long userId;
    @NotNull
    Long followingId;
    @NotNull
    String notNullField;
    @NotEmpty
    String notEmptyField;
    @NotBlank
    String notBlankField;
}
