package restapi.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.codingbat.entity.Category;
import restapi.codingbat.entity.Problems;
import restapi.codingbat.payload.ProblemsDto;
import restapi.codingbat.payload.Result;
import restapi.codingbat.repository.CategoryRepository;
import restapi.codingbat.repository.ProblemsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemsService {
    @Autowired
    ProblemsRepository problemsRepository;
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * BARCHA PROBLEMS NI BAZADAN OLIB KELISH.
     * @return
     */
    public List<Problems> getProblemsService(){
        return problemsRepository.findAll();
    }

    /**
     * PROBLEMSNI ID BO'YICHA OLIB KELISH.
     * @param id
     * @return
     */
    public Problems getProblemService(Integer id){
        Optional<Problems> optionalProblems = problemsRepository.findById(id);
        if (!optionalProblems.isPresent()) return null;
        return optionalProblems.get();
    }

    /**
     * YANGI PROBLEMS QO'SHISH.
     * @param problemsDto
     * @return
     */
    public Result addProblemsService(ProblemsDto problemsDto){
       boolean exists = problemsRepository.existsProblemsByBodyAndTitleAndCategoryId(problemsDto.getBody(),problemsDto.getTitle(),problemsDto.getCategoryId());
       if (exists) return new Result("Bunday savol oldin kiritildi.",false);
       Problems problems = new Problems();
       problems.setTitle(problemsDto.getTitle());
       problems.setBody(problemsDto.getBody());
       problems.setAnswer(problemsDto.getAnswer());

        Optional<Category> optionalCategory = categoryRepository.findById(problemsDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new Result("Bunday category mavjud emas.",false);
        Category category = new Category();
        Category category1 = optionalCategory.get();
        category.setName(category1.getName());
        category.setParentCategory(category1.getParentCategory());
        Category save = categoryRepository.save(category);

        problems.setCategory(save);
        problemsRepository.save(problems);
        return new Result("Muvaffaqiyatli qo'shildi.",true);
    }

    /**
     * PROBLEMS MALUMOTLARINI YANGILASH.
     * @param problemsDto
     * @param id
     * @return
     */
    public Result editProblemsService(ProblemsDto problemsDto,Integer id){
        Optional<Problems> optionalProblems = problemsRepository.findById(id);
        if (!optionalProblems.isPresent()) return new Result("Bunday masala mavjud emas.",false);
        Problems problems = optionalProblems.get();
        problems.setTitle(problemsDto.getTitle());
        problems.setBody(problemsDto.getBody());
        problems.setAnswer(problemsDto.getAnswer());

        Optional<Category> optionalCategory = categoryRepository.findById(problemsDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new Result("Bunday category mavjud emas.",false);
        Category category = optionalCategory.get();
        problems.setCategory(category);
        problemsRepository.save(problems);
        return new Result("Muvaffaqiyatli yangilandi.",true);
    }

    /**
     * PROBLEMSNI ID BO'YICHA O'CHIRISH.
     * @param id
     * @return
     */
    public Result deleteProblemsService(Integer id){
        Optional<Problems> optionalProblems = problemsRepository.findById(id);
        if (!optionalProblems.isPresent()) return new Result("Bunday masala mavjud emas.",false);
        problemsRepository.deleteById(id);
        return new Result("Masala o'chirildi.",true);
    }
}
