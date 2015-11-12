package moe.yuna.springauthdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@org.springframework.stereotype.Controller
@EnableAutoConfiguration
@ComponentScan
public class Controller {

    private Log log = LogFactory.getLog(getClass());

    @RequestMapping("/")
    String home() {
        return "index";
    }

    @RequestMapping("/index")
    String index(HttpServletRequest request, ModelMap model) {
        model.put("test", "value");
        request.setAttribute("test2", "123321");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login(@RequestParam Optional<String> error,
                 @RequestParam Optional<String> logout,
                 ModelMap model) {
        model.put("error", error);
        model.put("logout", logout);
        return "login";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    @ResponseBody
    @Secured("ROLE_normal")
    String success() {
        return "success";
    }

    @RequestMapping(value = "/cannotVisit", method = RequestMethod.GET)
    @ResponseBody
    @Secured("ROLE_admin")
    String cannotVisit() {
        return "cannotVisit";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    String error() {
        return "index";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Controller.class, args);
    }
}
