package pl.edu.pjwstk.jaz.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.Entity.CategoryEntity;
import pl.edu.pjwstk.jaz.Repository.CategoryRepository;
import pl.edu.pjwstk.jaz.Repository.SectionRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final EntityManager entityManager;
    private final CategoryRepository categoryRepository;
    private final SectionRepository sectionRepository;


    public CategoryService(CategoryRepository categoryRepository, SectionRepository sectionRepository, EntityManager entityManager) {
        this.categoryRepository = categoryRepository;
        this.sectionRepository = sectionRepository;
        this.entityManager = entityManager;
    }

    public Optional<CategoryEntity> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public void createCategory(String categoryName, String sectionName){
        var category = new CategoryEntity();
        var section = sectionRepository.findByName(sectionName).get();
        category.setSection(section);
        category.setName(categoryName);
        entityManager.persist(category);
    }

    public void updateCategory(String oldName, String newName){
        var category = categoryRepository.findByName(oldName).get();
        category.setName(newName);
        entityManager.merge(category);
    }

}
