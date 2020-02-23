package phonebook;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EntryController {

    private ArrayList<Entry> entries = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("entry", new Entry());
        model.addAttribute("entries", entries);
        return "home";
    }

    @PostMapping(path = "/", params = "do=add")
    public String add(Entry entry) {
        entries.add(entry);
        return "redirect:/";
    }

    @PostMapping(path = "/", params = "do=remove")
    public String remove(@RequestParam(name = "index", required = false) int[] indices) {
        if (indices != null) {
            for (int k = indices.length - 1; k >= 0; k--) {
                entries.remove(indices[k]);
            }
        }
        return "redirect:/";
    }

    @GetMapping(path = "/", params = "do=remove")
    public String remove(int index) {
        entries.remove(index);
        return "redirect:/";
    }

}
