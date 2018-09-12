package cn.connie.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("webview")
public class WebViewController {

    @RequestMapping("/agreement.html")
    public ModelAndView information() {
        return new ModelAndView("agreement");
    }
}
