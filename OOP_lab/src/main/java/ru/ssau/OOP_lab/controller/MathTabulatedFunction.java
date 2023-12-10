package ru.ssau.OOP_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ssau.OOP_lab.components.MathFunctionComponent;
import ru.ssau.OOP_lab.functions.IdentityFunction;
import ru.ssau.OOP_lab.functions.MathFunction;
import ru.ssau.OOP_lab.functions.SqrFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/createMathTabulatedFunction")
public class MathTabulatedFunction {
    Map<String,MathFunction> map = new HashMap<>();
    @RequestMapping(method = RequestMethod.GET)
    public String getForm(Model model){

        map.put("Identity Function",new IdentityFunction());
        map.put("Sqr Function",new SqrFunction());

        System.out.println(2);

        model.addAttribute("mathFunction",new MathFunctionComponent());
        model.addAttribute("functions",map);

        return "createMathTabulatedFunction";
    }
    @RequestMapping(name = "submit", method = RequestMethod.POST)
    public String setForm(@ModelAttribute("mathFunction") MathFunctionComponent component,
                          Model model){
        model.addAttribute("message", component.getFunction());
        System.out.println(1);
        model.addAttribute("functions",map);
        return "createMathTabulatedFunction";
    }
}
