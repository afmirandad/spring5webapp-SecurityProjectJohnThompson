package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jt on 12/24/19.
 */
@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String getAuthrors(Model model){
        model.addAttribute("authors", authorRepository.findAll());

        return "authors/list";
    }

    @GetMapping("/authors/new")
    public String readdata(Model model){
        model.addAttribute("authors", authorRepository.findAll());
        return "authors/list";
    }

    @GetMapping("/authors/new/{nombre}/{apellido}")
    public String createAuthor(Model model, @PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido) {
        // Aquí debes guardar el nuevo autor en tu repositorio
        String name = StringUtils.isEmpty(nombre) ? "Andres" : nombre;
        String lastname = StringUtils.isEmpty(apellido) ? "Miranda" : apellido;
        model.addAttribute("author", new Author());
        Author author = new Author(name,lastname);
        authorRepository.save(author);
        model.addAttribute("authors", authorRepository.findAll());
        // Redirige a la página de lista de autores o a donde desees después de crearlo
        return "authors/list";
    }
}
