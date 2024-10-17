package com.ajwalker.service;

import com.ajwalker.dto.request.RegisterRequestDto;
import com.ajwalker.dto.request.UpdateUserProfileRequestDto;
import com.ajwalker.entity.EGender;
import com.ajwalker.entity.User;
import com.ajwalker.mapper.UserMapper;
import com.ajwalker.repository.IUserRepository;
import com.ajwalker.views.VwUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * DİKKAT!! spring boot bir sınıfın referansını oluşturmak için mutlaka
 * o sınıfın işaretli olmasını talep eder, yani bir sınıftan nesne türetilecek mi
 * bilmek ister. Bunu yapabilmek için anatasyon kullanır. Böylece spring
 * componentScan sistemi ile işaretli sınıfları Bean olarak işaretler ve
 * sevris sınıfları için @Service anatasyonu kullanılır.
 */
@Service
public class UserService {
    /*
     * Servis temel işlevleri yerine getirir. Gerekli gördüğü bilgiler repository'e aktarır.
     * Bu nedenle servis sınıfında repository nesnelerine ihtiyaç duyulur.
     * Burada repository değişkeni tanımlanmalı ve nesneleri oluşturulmalıdır.
     */

    /*
     * Dependency Injection /DI
     * Spring boot'ta bağlılıkları sağlamak, injekte etmek için farklı yöntemler kullanılır. En bilinenler:
     * 1- Autowired anatasyonu
     * bağlılık atfedilen değişkenin üzerine eklenir ise spring boot ApplicationContext
     * içerisinde ilgili sınıftan oluşturulmuş olan nesne referansını olgili değişkene atar.*/

    //    @Autowired
    //    private IUserRepository userRepository;

    /*
     * 2- Constructor Injection ile kullanım
     * Spring boot bir sınıfın oluşturlabilmesi için gerekli olan Constructor mothodlarını
     * incelerken eğer method nesne talep ediyor ise, ApplicationContext içindeki
     * nesnelerden bunu sağlamaya çalışır ve nesne referanslarını bu constructor'a parametre olarak geçer.
     */
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String name, String avatar, EGender gender) {
        User user = User.builder().name(name).avatar(avatar).gender(gender).build();
        userRepository.save(user);
    }

    public List<VwUser> getAllUsers() {
        return  userRepository.tumKullanicilariGetir();
    }

    public List<User> getFemaleUsersList() {
        return userRepository.findAllByGender(EGender.FEMALE);
    }

    public void createUser(String username, String password, String email, String name) {
        User user = User.builder().username(username).password(password).email(email).name(name).build();
        userRepository.save(user);
    }

    public List<User> searchByUsername(String username) {
//        return userRepository.findAllByUsernameLikeIgnoreCase(username+"%");
        return userRepository.findAllByUsernameContaining(username);
    }

    public User searchById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User registerUser(RegisterRequestDto dto) {
        return userRepository.save(User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build());
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    public Optional<User> getOptionalUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAllByIdIn(List<Long> takipcilerIdList) {
        return userRepository.findAllByIdIn(takipcilerIdList);
    }

    public void updateUserProfile(UpdateUserProfileRequestDto dto) {
//        User user = User.builder()
//                .id(dto.getId())
//                .name(dto.getName())
//                .avatar(dto.getAvatar())
//                .username(dto.getUsername())
//                .email(dto.getEmail())
//                .password(dto.getPassword())
//                .phone(dto.getPhone())
//                .age(dto.getAge())
//                .build();
//        userRepository.save(user);

        User user = UserMapper.INSTANCE.fromUpdateProfileDto(dto);
        userRepository.save(user);
    }
}
