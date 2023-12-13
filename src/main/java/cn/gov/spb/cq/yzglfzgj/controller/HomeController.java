package cn.gov.spb.cq.yzglfzgj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/09/20
 * Time: 15:46
 */
@org.springframework.stereotype.Controller
@RequestMapping
@Slf4j
public class HomeController {

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("redirect:/business/");
    }


}
