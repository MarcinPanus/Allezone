package pl.edu.pjwstk.jaz.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.Entity.SectionEntity;
import pl.edu.pjwstk.jaz.Repository.SectionRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SectionService {

    private final EntityManager entityManager;
    private final SectionRepository sectionRepository;

    public SectionService(EntityManager entityManager, SectionRepository sectionRepository) {
        this.entityManager = entityManager;
        this.sectionRepository = sectionRepository;
    }

    public Optional<SectionEntity> getSectionByName(String sectionName) {
        return sectionRepository.findByName(sectionName);
    }

    public void createSection(String sectionName){
        var section = new SectionEntity();
        section.setName(sectionName);
        entityManager.persist(section);
    }

    public void updateSection(String oldName, String newName){
        var section = sectionRepository.findByName(oldName).get();
        section.setName(newName);
        entityManager.merge(section);
    }


}
