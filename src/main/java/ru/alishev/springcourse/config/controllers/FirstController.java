package ru.alishev.springcourse.config.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FirstController {

    @GetMapping
    public String startingPage() {
        return "first/start-page";
    }

    @GetMapping("/hello") // By absent params response will be 200 and params will be null
    public String helloPage(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        model.addAttribute("message", "Model from controller " + name + " " + surname);

//        System.out.println("Hello " + name + " " + surname);
        return "first/hello";
    }


//    @GetMapping("/calculator")
//    public String calculate(HttpServletRequest httpServletRequest, Model model) {
//        Double a = Double.valueOf(httpServletRequest.getParameter("a"));
//        Double b = Double.valueOf(httpServletRequest.getParameter("b"));
//        String sign = httpServletRequest.getParameter("action");
//        String result = switch (sign) {
//            case "+" -> String.valueOf((a + b));
//            case "-" -> String.valueOf(a - b);
//            case "*" -> String.valueOf(a * b);
//            case "/" -> String.valueOf(a / b);
//            default -> "0";
//        };
//        model.addAttribute("result", "The answer is " + result);
//        return "first/calculator";
//    }

    @GetMapping("/calculator")
    public String calculate(@RequestParam(required = false, name = "a") Double a,
                            @RequestParam(required = false, name = "b") Double b,
                            @RequestParam(required = false, name = "action") String sign,
                            Model model) {
        if (null == a || null == b || null == sign) return "first/calculator";
        String result = switch (sign) {
            case "+" -> String.valueOf((a + b));
            case "-" -> String.valueOf(a - b);
            case "*" -> String.valueOf(a * b);
            case "/" -> String.valueOf(a / b);
            default -> "0";
        };
        model.addAttribute("result", result);
        return "first/calculator";
    }

//    @GetMapping("/hello") // By absent params response will be 400, so have to add required = false to have 200 and params will be null
//    public String hello(@RequestParam(required = false, name = "name") String name,
//                        @RequestParam(required = false, name = "surname") String surname) {
//        System.out.println(name + " " + surname);
//        return "first/hello";
//    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }
}
