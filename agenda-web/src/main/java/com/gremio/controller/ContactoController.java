package com.gremio.controller;

import com.gremio.model.Contacto;
import com.gremio.repository.ContactoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Controller
public class ContactoController {

    private final ContactoRepository repo;

    // Spring inyecta el repository automáticamente
    public ContactoController(ContactoRepository repo) {
        this.repo = repo;
    }

    // GET / y /contactos → muestra la lista
    @GetMapping({"/", "/contactos"})
    public String listar(Model model) {
        model.addAttribute("contactos", repo.findAll());
        return "contactos";
    }

    // GET /nuevo → muestra el formulario vacío
    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "nuevo";
    }

    // GET /editar/{id} → muestra el formulario con los datos existentes
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("contacto", buscarContacto(id));
        return "nuevo";
    }

    // POST /guardar → recibe el formulario y guarda en BD
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Contacto contacto) {
        repo.save(contacto);
        return "redirect:/contactos";
    }

    // POST /actualizar → actualiza un contacto existente
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Contacto contacto) {
        buscarContacto(contacto.getId());
        repo.save(contacto);
        return "redirect:/contactos";
    }

    // POST /eliminar/{id} → elimina un contacto existente
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        buscarContacto(id);
        repo.deleteById(id);
        return "redirect:/contactos";
    }

    private Contacto buscarContacto(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Contacto no encontrado: " + id));
    }
}