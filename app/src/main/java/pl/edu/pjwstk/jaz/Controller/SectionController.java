package pl.edu.pjwstk.jaz.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.Entity.SectionEntity;
import pl.edu.pjwstk.jaz.ObjectNotFoundException;
import pl.edu.pjwstk.jaz.Request.SectionRequest;
import pl.edu.pjwstk.jaz.Service.SectionService;

@RestController
public class SectionController {

    SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/sections/{name}")
    public ResponseEntity<?> getSectionName(@PathVariable("name") String name) {
        SectionEntity sectionEntity = sectionService.getSectionByName(name).orElseThrow(() -> new ObjectNotFoundException("Section not found"));
        return ResponseEntity.ok(sectionEntity);
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/addSection")
    public void addSection(@RequestBody SectionRequest sectionRequest) {
        sectionService.createSection(sectionRequest.getName());
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PatchMapping("/updateSection/{oldName}")
    public void updateSection(@PathVariable("oldName") String oldName, @RequestBody SectionRequest sectionRequest) {
        sectionService.updateSection(oldName, sectionRequest.getName());
    }

}
