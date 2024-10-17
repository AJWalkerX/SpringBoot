package com.ajwalker.repository;


import com.ajwalker.entity.EGender;
import com.ajwalker.entity.User;
import com.ajwalker.views.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/*
* Spring DATA JPA, spring boot'un yapısı gereği, her bir interface için gerekli olan
* IMPL sınıflarını generate ederek onların nesne referansınmı(yani bean'ler)
* application context'inin içerisine atar. Gerekli olduğunda buradan kullanırız.
*/

public interface IUserRepository extends JpaRepository<User, Long> {

    // Kadın kullanıcıların listesini bul.
//    List<User> getFemaleUsersList(String email);
    //Yanlış!!!
    /*
    * JPA Repository belli keywordler ile sorgu methodlarını oluşturmanıza izin verir.
    * -- JPA kendine ait methodların görevlerini yazabilir.
    * -- JPA kelimeleri ile oluşturulan methodların da gövdelerini yazabilir, ancak kuralına uygun olmalıdır.
    * ÖRN: cinsiyete göre kullanıcı bulma işlemi
    * SELECT * FROM tbl_user WHERE gender = ?
    * 1- find(sorgu türünü belirler) - All(Opsiyonel)
    * 2- by - where komutuna denk gelir.
    * 3- Entity içerisinde tanımlanmış değişken adını yazıyoruz ancak ilk harfi büyük olmalıdır.
    * 4- Eğer sorgu bir değer talep ediyor ise talep edilen değişken türünde bir değer değişkeni
    * method parametresi olarak tanımlanır.
    */
    List<User> findAllByGender(EGender gender);

    /*
    * birden fazla alan için sorgu yapmak istersek,
    * SELECT * FROM tbl_user WHERE gender = ? AND age = ?
    * SELECT * FROM tbl_user WHERE gender = ? OR name = ?
    */
    List<User> findAllByGenderAndAge(EGender gender,Integer age);
    List<User> findAllByGenderOrName(EGender gender,String name);

    /*
    * sorgu sadece tek bir sonuç dönecek ise return type
    * Entity ya da Optional<Enitity> şeklinde kullanılır.
    * SELECT * FROM tbl_user WHERE username = ?
    */
    User findByUsername(String username);
    // Bu kullanım null değere karşı hata verme imkanının önüne geçicektir.
    Optional<User> findOptionalByUsername(String username);

    /*
    * belli bir yaşın üzerindeki kullanıcıları bulmak
    * SELECT * FROM tbl_user WHERE age > ?
    * yada
    * SELECT * FROM tbl_user WHERE age >= ?
    */
    List<User> findAllByAgeGreaterThan(Integer age);
    List<User> findAllByAgeGreaterThanEqual(Integer age);
    List<User> findAllByAgeLessThan(Integer age);
    List<User> findAllByAgeLessThanEqual(Integer age);

    /*
    * kullanıc adlarını belli bir kelimeye göre içerikte arama yapmak
    * SELECT * FROM tbl_user WHERE userName LIKE '%[VALUE]%'
    * SELECT * FROM tbl_user WHERE userName LIKE '[VALUE]%'
    * SELECT * FROM tbl_user WHERE userName LIKE '%[VALUE]'
    * SELECT * FROM tbl_user WHERE userName LIKE '___ [VALUE]%'
    * like ile Muhammed == muhammed olmaz onun yerine ILIKE kullanılır
    */
    //findAllByUsernameLike("__m%") şeklinde olacak
    List<User>findAllByUsernameLike(String username);
    List<User>findAllByUsernameLikeIgnoreCase(String username);

    List<User>findAllByUsernameContainingIgnoreCaseAndAgeGreaterThanAndEmailEndingWith(String usernameContains, Integer age, String endingWith);

    /*
    * sorgu neticesinde dönen verilere göre sıralama yapmak.
    * SELECT * FROM tbl_user ORDERBY AGE
    */
    List<User>findAllByOrderByAge();
    List<User> findAllByOrderByAgeDesc();

    /*
    * SELECT * FROM tbl_user WHERE name = ? ORDERBY age desc
    */
    List<User> findAllByNameContainingOrderByAgeDesc(String name);
    List<User> findAllByNameOrderByAgeDesc(String name);
    //ikisi de olur.
    /*
    * bununla başlayanlar, -> startWith
    * bununla bitenler, -> endWith
    * bunu içerenler -> containing
    */
    List<User>findAllByUsernameStartingWith(String username);
    List<User> findAllByUsernameEndingWith(String username);
    List<User>findAllByUsernameContaining(String username);

    /*
    * sorgular genellikle kısıtlanarak kullanılır, böylece sorgu performansı artırmış olur.
    * SELECT * FROM tbl_user LIMIT 5 (Bazı veritabanları[TOP] kullanıyor)
    * yaşı en büyük olan kullanıcı kim ?
    * SELECT * FROM tbluser ORDERBY age desc LIMIT 1
    */
    Optional<User>findTopByOrderByAgeDesc();
    List<User> findTop5ByOrderByAge();

    /*
    * belli aralıkları sorgulamak,
    * SELECT * FROM tbl_user WHERE age > 5 AND age < 25 -> (5,25)
    * SELECT * FROM tbl_user WHERE age => 5 AND age =< 25 -> [5,25]
    */
    List<User>findAllByAgeBetween(Integer minAge, Integer maxAge); //[start, end]
    List<User> findAllByUsernameContainingAndAgeBetween(String usernameContains, Integer minAge, Integer maxAge);

    /*
    * boolean değeri sorgularken kullanılacak keyword
    * hesabı aktif olan kullanıcılar
    */
    List<User>findAllByIsActive(boolean isActive);
    List<User>findAllByIsActiveTrue();
    List<User>findAllByIsActiveFalse();

    /*
    *  Tekrar eden kayıtları tekilleştirme
    * uygulamamızda kullanılarn farklı isimlerin listesi
    */
    List<User>findDistinctByName(String name);

    List<User>findAllByIsActiveIsNotNull(); // aktif bilgisi null olmayanlar
    List<User> findAllByIsActiveIsNull(); // aktif bilgisi null olanlar

    /*
    * Belli kullanıcıların listesine ihtiyaç duymaktayız, burada id kullanılacak diyelim
    * id si 5-9-98-541-24-5889-44-85
    * bir kişinin takipçilerinin id'ler elimizde olsun, bu kullanıcıların bilgilerine ihtiyaç var.
    * SELECT * FROM tbl_user WHERE id IN(5,9,98,...)
    * SELECT * FROM tbl_user WHERE id IN (SELECT follower_id FROM tbl_follow WHERE id = 5)
    */
    List<User>findAllByIdIn(List<Long> ids);

    /*
    * JPQL -> Jakarta Persistence Query Language
    * HQL -> Hibernate Query Language
    * NATIVE SQL -> bildiğimiz SQL sorgoları
    *
    * JPQL -> SELECT u FROM User u
    * HQL -> FROM User
    * NATIVE SQL -> SELECT * FROM User
    *
    * Aşağıdaki method içi Spring Spring gövde oluştururken, Query içerisine
    * yazılan sorguyu kullanacak ve method'a geçilen değerler sorguya işlenerek sorgunun
    * sonucunun methodun return type'ı ile dönecektir.
    */
    @Query("SELECT u FROM User u WHERE u.name=?1") //JPQL
    List<User> banaAdiSuOlanKullanıcılarıGetir(String ad);

    @Query("SELECT u FROM User u WHERE u.age = ?1 AND u.email LIKE ?2") //JPQL
    List<User> bulYasiSuOlanVeMailAdresiBuOlan(int yas, String mailAdres);

    @Query(nativeQuery = true,
            value = "SELECT * FROM User WHERE age>?")//native sql
    List<User> yasiBuyukOlanlariGetir(int yas);

    /*
    * Doğru login sonucunda, yani kulanıcı adı ve şifre doğru ise o kullanıcı giriş yapsın.
    * Değilse hata vermesi sağlansın
    * SELECT COUNT(*) > 0 FROM tbl_user UserName = ? AND password = ?
    */
    @Query("SELECT COUNT(*)>0 FROM User u WHERE u.username = ?1 AND u.password = ?2")
    Boolean buKullaniciVarMi(String username, String password);

    Boolean existsByUsernameAndPassword(String username, String password);

    /*
    * select return elemanları ile result type uyumlu olmalıdır.
    */
    @Query("SELECT new com.ajwalker.views.VwUser(u.username,u.name,u.avatar) FROM User u")
//    @Query(value = "SELECT u.username, u.name, u.avatar FROM tbl_user u", nativeQuery = true)
    List<VwUser> tumKullanicilariGetir();
    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Long findIdByUsername(String username);

    String findUsernameById(Long id);

    /*
    * JpaRepository -> içerisindeki tüm methodların kodları standarttır.
    * Bu nedenle Spring ilgili sınıfların mesela UserRepository için
    * UserRepositoryImpl sınıfı oluşturulabilir.
    */

}
