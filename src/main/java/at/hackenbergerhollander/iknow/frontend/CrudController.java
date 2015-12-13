package at.hackenbergerhollander.iknow.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CrudController {

    @RequestMapping("/crud")
    public String search(Model model) {
        return "crud";
    }

}
