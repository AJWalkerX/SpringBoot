package com.ajwalker.controller;

import static com.ajwalker.constans.RestAPIs.*;

import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.request.UpdateUserProfileRequestDto;
import com.ajwalker.dto.response.BaseResponse;
import com.ajwalker.entity.EGender;
import com.ajwalker.entity.User;
import com.ajwalker.service.UserService;
import com.ajwalker.views.VwUser;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

/*
 * Control sınıfları son kullanıcı ile iletişime geçiş için gelen istekleri handle edecek sınıftır.
 * Burada web için gerekli erişim tanımlanır ve istekler işlenir.
 * @Controller -> web MCV için kullanılır.
 * @RestController -> restAPI kullanımları için
 *
 * Bir diğer önemli nokta ise, bu sınıf bir web iletişim sınıfı olduğu için internet ya da intranet
 * üzerinden gelen isteklerin yakalanabilmesi için PATH yapılması gereklidir. İlgili sınıf için tanımlama
 * @RequestMapping("[Path name]") şeklinde yapılır.
 * bu işlem şunu ifade eder:
 * http://[IP adres yada DomainName]:[PORT]/[PATH_NAME]
 * userController için erişim
 * http://localhost:9090/user
 */
@RestController
@RequestMapping(USER)
public class UserController {
    private final UserService userService;
    private final DataSourceAutoConfiguration dataSourceAutoConfiguration;

    public UserController(UserService userService, DataSourceAutoConfiguration dataSourceAutoConfiguration) {
        this.userService = userService;
        this.dataSourceAutoConfiguration = dataSourceAutoConfiguration;
    }

    @GetMapping(ADD)
    public String addUser() {
        userService.addUser("Alex", "http://picsum.photos/100/100", EGender.MALE);
        userService.addUser("Banu", "http://picsum.photos/100/100", EGender.FEMALE);
        userService.addUser("Cansu", "http://picsum.photos/100/100", EGender.FEMALE);
        userService.addUser("Umay", "http://picsum.photos/100/100", EGender.FEMALE);
        return "success";
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<VwUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("get-all-view")
    public ResponseEntity<BaseResponse<List<VwUser>>> getAllViewUsers() {
        return ResponseEntity.ok(
                BaseResponse.<List<VwUser>>builder()
                        .success(true)
                        .message("success")
                        .code(200)
                        .data(userService.getAllUsers())
                        .build()
        );
    }


    @GetMapping(FIND_ALL_FEMALE)
    public List<User> getAllFemaleUsers() {
        return userService.getFemaleUsersList();
    }

    @PostMapping(LOGIN)
    public void doLogin(String username, String password) {
        System.out.println(username + " " + password);
    }

    /*
     * create-user adında bir method oluşturun
     * username, password, email, name
     * method kullanıcı oluşturma işlevini yapacak
     */


    // 1- kullanıcı adını alarak liste dönen bir method (GET) ismin bir kısmı da olur(ören: kullanıcı adında ha geçenler)
    @GetMapping(FIND_BY_USERNAME)
    public List<User> searchByUsername(String username) {
        return userService.searchByUsername(username);
    }

    // 2- id'si veriler kullanıcıyı dönen method.
    @GetMapping(FIND_BY_ID)
    public User searchById(Long id) {
        return userService.searchById(id);
    }

    /*
     * Data Transfer Object - DTO
     * Bilgi alırken yada gönderirken -> Request-Response
     * Bilgileri alırken kısıtlı tutmak ve ihtiyaca göre talep etmek zorundayız.
     * Gelen verilerini tutarlılığını Controller katmanında incelemek zorundayız.
     * Kullanıcıya dönüş yapacağımız bilgileri kısıtlamak zorundayız zira,
     * güvenlik için gizlenmesi gereken bilgiler vardır. Ayrıca ne kadar
     * çok veri gönderimi o kadar kaynak kullanımı demektir.
     */

    //    @PostMapping(REGISTER)
//    public void createUser(String username, String password, String email, String name) {
//        userService.createUser(username,password,email,name);
//
//    }
    @PostMapping(REGISTER)
    public ResponseEntity<User> registerUser(@RequestBody @Valid RegisterRequestDto dto) {
        if (!dto.getPassword().equals(dto.getRePassword())) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = userService.registerUser(dto);
        return ResponseEntity.ok(user);
    }
    /*
    * HTTP Request tiplerini araştırarak ne işe yaradığını terimsel olarak ifade ediniz.
    * Idempotent, Idempotency bu kavram nedir?
    */
    @PutMapping("/update-user")
    public ResponseEntity<BaseResponse<Boolean>> updateUserProfile(@RequestBody @Valid UpdateUserProfileRequestDto dto) {
        userService.updateUserProfile(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .data(true)
                .message("Güncelleme başarılı!")
                .code(200)
                .success(true)
                .build());
    }

//    @GetMapping("/get-all-view-users")
//    public ResponseEntity<BaseResponse<List<VwUser>>> getAllViewUsers() {
//        return  ResponseEntity.ok(
//                BaseResponse<List<VwUser>>
//        )
//    }

}
