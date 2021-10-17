package pl.edu.pjwstk.jaz.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class ReadinessController {

    @PersistenceContext
    private final EntityManager em;

    public ReadinessController(EntityManager em) {
        this.em = em;
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/is-ready")
    @Transactional
    public void isReady() {
//        var entity = new Test1Entity();
//        entity.setName("sdavsda");
//        em.persist(entity);
    }
}
