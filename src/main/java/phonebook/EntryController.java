package phonebook;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EntryController {

    private final EntryRepository repository;

    EntryController(EntryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("entry", new Entry());
        model.addAttribute("entries", repository.findAllByOrderByIndexAsc());
        return "home";
    }

    @PostMapping(path = "/", params = "do=find")
    public String find(Model model, Entry entry) {
        model.addAttribute("entry", entry);
        model.addAttribute("search", true);
        model.addAttribute("entries", repository.search(entry));
        return "home";
    }

    @PostMapping(path = "/", params = "do=add")
    public String add(Entry entry) {
        repository.save(entry);
        return "redirect:/";
    }

    @PostMapping(path = "/", params = "do=remove")
    @Transactional
    public String remove(@RequestParam(name = "index", required = false) List<Long> indices) {
        if (indices != null) {
            repository.deleteByIndexIn(indices);
        }
        return "redirect:/";
    }

    @GetMapping(path = "/", params = "do=remove")
    public String remove(Long index) {
        repository.deleteById(index);
        return "redirect:/";
    }

}
