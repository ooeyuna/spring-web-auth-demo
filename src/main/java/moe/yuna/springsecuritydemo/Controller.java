package moe.yuna.springauthdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    private Log log = LogFactory.getLog(getClass());

    @RequestMapping("/")
    public String home(HttpServletRequest request, ModelMap model) {
        return index(request, model);
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.put("is_normal", request.isUserInRole("ROLE_NORMAL"));
        model.put("is_admin", request.isUserInRole("ROLE_ADMIN"));
        model.put("test", "value");
        model.put("user", auth);
        model.put("password", new Md5PasswordEncoder());
        request.setAttribute("test2", "123321");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam Optional<String> error,
                        @RequestParam Optional<String> logout,
                        ModelMap model) {
        model.put("error", error);
        model.put("logout", logout);
        return "login";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    @ResponseBody
    @Secured("ROLE_NORMAL")
    public String success() {
        return "success";
    }

    @RequestMapping(value = "/cannotVisit", method = RequestMethod.GET)
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public String cannotVisit() {
        return "cannotVisit";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout() {
        return "logout";
    }
}
