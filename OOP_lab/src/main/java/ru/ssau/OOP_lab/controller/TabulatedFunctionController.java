package ru.ssau.OOP_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ssau.OOP_lab.components.AmountOfPointsComponent;


@Controller
public class TabulatedFunctionController {

    @RequestMapping(value = "/createTabulatedFunction")
    public String createTabulatedFunctionController(
            @RequestParam(name = "message", required = false,defaultValue = "Enter your values") String message,
            Model model){
        model.addAttribute("message",message);
        return "createTabulatedFunction";
    }
    @RequestMapping(value = "/createTabulatedFunction", method = RequestMethod.GET)
    public String amountOfPointsForm(@RequestParam(name = "error", required = false,defaultValue = "")String error, Model model){
        model.addAttribute("amountOfPointsComponent", new AmountOfPointsComponent());
        return "createTabulatedFunction";
    }
    @RequestMapping(value = "/createTabulatedFunction", method = RequestMethod.POST)
    public String amountOfPointsSubmit(@ModelAttribute AmountOfPointsComponent amountOfPointsComponent, Model model){
        model.addAttribute("amountOfPointsComponent",amountOfPointsComponent);
        System.out.println(amountOfPointsComponent.getAmount());
        return "createTabulatedFunction";
    }
}
