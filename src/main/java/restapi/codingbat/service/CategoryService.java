package restapi.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.codingbat.entity.Category;
import restapi.codingbat.payload.CategoryDto;
import restapi.codingbat.payload.Result;
import restapi.codingbat.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * BAZADAN BARCHA CATEGORYLARNI OLIB KELADI.
     * @return CATEGORYLAR LISTINI QAYTARADI.
     */
    public List<Category> getCategoriesService(){
        return categoryRepository.findAll();
    }

    /**
     * BAZADAN CATEGORYNI ID BO'YICHA OLIB KELADI.
     * @param id
     * @return CATEGORY QAYTARADI.
     */
    public Category getCategoryService(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    /**
     * YANGI CATEGORY QO'SHADI.
     * @param categoryDto
     * @return RESULT TOIFASIDA MALUNOT QAYTARADI. MESSAGE VA SUCCESS
     */
    public Result addCategoryService(CategoryDto categoryDto){
        boolean exist = categoryRepository.existsByName(categoryDto.getName());
        if (exist) return new Result("Bunday Category mavjud!",false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        Optional<Category> optional = categoryRepository.findById(categoryDto.getParentCategoryId());
        if (!optional.isPresent()) return new Result("Bundya category mavjud emas.",false);
        category.setParentCategory(optional.get());
        categoryRepository.save(category);
        return new Result("Yangi category qo'shildi.",true);
    }

    /**
     * CATEGORY MALUNOTLARINI YANGILASH.
     * @param categoryDto
     * @param id
     * @return
     */
    public Result editCategoryService(CategoryDto categoryDto,Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new Result("Bunday Category mavajud emas.",false);
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists) return new Result("Bunday Category mavjud!",false);

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
        if (!optionalParentCategory.isPresent()) return new Result("Bunday category mavjud emas.",false);
        category.setParentCategory(optionalParentCategory.get());
        categoryRepository.save(category);
        return new Result("Category yangilandi.",true);
    }

    /**
     * CATEGORYNI O'CHIRISH, ID BO'YICHA.
     * @param id
     * @return
     */
    public Result deleteCategoryService(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new Result("Bunday Category mavjud emas!",false);
        categoryRepository.deleteById(id);
        return new Result("Category o'chirildi.",true);
    }
}
