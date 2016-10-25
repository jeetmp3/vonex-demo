package demo.vonex.controllers;

import demo.vonex.models.AskResponse;
import demo.vonex.services.VonexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Jitendra Singh.
 */
@Controller
public class DashboardController {

    @Autowired
    VonexService vonexService;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/initiate")
    @ResponseBody
    public String initiate() {
        return vonexService.initiateRequest().getRequest();
    }

    @RequestMapping("/ask")
    @ResponseBody
    public String ask(String token, HttpServletResponse response) {
        if(!ObjectUtils.isEmpty(token)) {
            AskResponse resp = vonexService.ask(token);
            if(!resp.isSuccess()) {
                response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
            }
            return resp.isSuccess() ? resp.getAnswer() : token;
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "you must provide a token";
        }
    }

    @RequestMapping("/answer")
    @ResponseBody
    public String answer(String token, HttpServletResponse response) {
        if(!ObjectUtils.isEmpty(token)) {
            AskResponse resp = vonexService.answer(token);
            if(!resp.isSuccess()) {
                response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
            }
            return resp.isSuccess() ? resp.getAnswer() : token;
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "you must provide a token";
        }
    }
}
