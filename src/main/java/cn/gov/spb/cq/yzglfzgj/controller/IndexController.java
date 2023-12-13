package cn.gov.spb.cq.yzglfzgj.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/09/20
 * Time: 15:46
 */
@Controller
@RequestMapping(value = "/admin")
@Slf4j
public class IndexController {

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping(value = "/index")
    public ModelAndView indexContent(){

        return new ModelAndView("index_content");
    }


    @GetMapping(value = "/error/500")
    public ModelAndView error(){
        return new ModelAndView("/error/500");
    }

    @GetMapping(value = "/error/403")
    @ResponseBody
    public String permissionDecline(){
        return "权限不足";
    }


    /*@GetMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, Map<String, Object> map){
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        log.error("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                log.info("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                log.info("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                log.info("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                log.info("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return new ModelAndView("login", map);
    }*/

    /*@PostMapping("/login")
    public ModelAndView loginCheck(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()){
            //使用shiro来验证
            token.setRememberMe(true);
            try {
                currentUser.login(token);//验证角色和权限
            }
            catch (IncorrectCredentialsException e){
                String msg = "密码错误";
                Map<String,Object> map = new HashMap<>();
                map.put("msg", msg);
                // 此方法不处理登录成功,由shiro进行处理
                return new ModelAndView("login", map);
            }
            catch (UnknownAccountException exception){
                String msg = "账号不存在";
                Map<String,Object> map = new HashMap<>();
                map.put("msg", msg);
                // 此方法不处理登录成功,由shiro进行处理
                return new ModelAndView("login", map);
            }

        }
        return new ModelAndView("redirect:/admin/");
    }*/

    @GetMapping("/logout")
    public ModelAndView logout(){
        return new ModelAndView("redirect:/admin/");
    }

}
