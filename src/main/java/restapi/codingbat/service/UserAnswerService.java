package restapi.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.codingbat.entity.Problems;
import restapi.codingbat.entity.User;
import restapi.codingbat.entity.UserAnswer;
import restapi.codingbat.payload.Result;
import restapi.codingbat.payload.UserAnswerDto;
import restapi.codingbat.repository.ProblemsRepository;
import restapi.codingbat.repository.UserAnswerRepository;
import restapi.codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAnswerService {
    @Autowired
    UserAnswerRepository userAnswerRepository;
    @Autowired
    ProblemsRepository problemsRepository;
    @Autowired
    UserRepository userRepository;


    public List<UserAnswer> getUserAnswersService(){
        return userAnswerRepository.findAll();
    }
    public UserAnswer getUserAnswerService(Integer id){
        Optional<UserAnswer> optionalUserAnswer = userAnswerRepository.findById(id);
        return optionalUserAnswer.orElse(null);
    }
    public Result addUserAnswerService(UserAnswerDto userAnswerDto){
        Optional<User> optionalUser = userRepository.findById(userAnswerDto.getUserId());
        if (!optionalUser.isPresent()) return new Result("Bunday Foydalanuvchi mavjud emas!",false);
        Optional<Problems> optionalProblems = problemsRepository.findById(userAnswerDto.getProblemId());
        if (!optionalProblems.isPresent()) return new Result("Bunday masala mavjud eams.",false);
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setAnswer(userAnswerDto.getAnswer());
        userAnswer.setUser(optionalUser.get());
        userAnswer.setProblems(optionalProblems.get());
        userAnswerRepository.save(userAnswer);
        return new Result("Javob qabul qilindi.",false);
    }
    public Result deleteUserAnswerService(Integer id){
        Optional<UserAnswer> optionalUserAnswer = userAnswerRepository.findById(id);
        if (!optionalUserAnswer.isPresent()) return new Result("Bunday javob yuq",false);
        userAnswerRepository.deleteById(id);
        return new Result("O'chirildi.",true);
    }
}
