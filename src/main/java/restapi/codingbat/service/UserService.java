package restapi.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.codingbat.entity.User;
import restapi.codingbat.payload.Result;
import restapi.codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * Bazadan barcha foydalanuvchilarni olib keladi.
     * @return Foydalanuvchilar listini qaytaradi.
     */
    public List<User> getUsersService(){
        return userRepository.findAll();
    }

    /**
     * Bazadan id bo'yicha foydalanuvchini olib keladi.
     * @param id
     * @return So'ralgan id li foydalanuvchini qaytaradi
     * bunday idli foydalanuvhi bo'lmasa null qiymat qaydaradi.
     */
    public User getUserService(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return null;
        return optionalUser.get();
    }

    /**
     * YANGII FOYDALANUVCHI QO'SHISH.
     * @param user
     * @return RESULT TOIFASIDA QIYMAT QAYTARADI.
     */
    public Result addUserService(User user) {
        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists) return new Result("Bu email oldin ro'yxaddan o'tgan!",false);
        userRepository.save(user);
        return new Result("Ro'yxatdan muvaffaqiyatli o'tdingiz.",true);
    }

    /**
     * FOYDALANUVCHI MALUMOTLARINI YANGILASH.
     * @param user
     * @param id
     * @return RESULT TOIFASIDA MALUMOTLAR QAYTARILADI.
     */
    public Result editUserService(User user,Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new Result("Bunday foydalanuvchi mavjud emas!",false);
        User user1 = optionalUser.get();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return new Result("Foydalnuvchi malumotlari yangilandi.",true);
    }

    /**
     * ID BO'YICHA FOYDALANUVCHINI O'CHRISH.
     * @param id
     * @return
     */
    public Result deleteUserService(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new Result("Bunday foydalanuvchi mavjud emas.",false);
        userRepository.deleteById(id);
        return new Result("Foydalanuvchi o'chirildi!",true);
    }
}
