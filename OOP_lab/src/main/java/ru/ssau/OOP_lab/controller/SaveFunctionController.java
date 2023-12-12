package ru.ssau.OOP_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.ssau.OOP_lab.components.CalculatorComponent;
import ru.ssau.OOP_lab.components.DifferentialComponent;
import ru.ssau.OOP_lab.components.SaveFunctionComponent;
import ru.ssau.OOP_lab.io.TabulatedFunctionSerialization;

@Controller
@RequestMapping(value = "/saveFunction")
@SessionAttributes({"saveFunctions","component"})
public class SaveFunctionController {

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(Model model){
        SaveFunctionComponent saveFunctionComponent = new SaveFunctionComponent();
        model.addAttribute("saveFunctions",saveFunctionComponent);
        return "saveFunction";
    }
    @RequestMapping(params = "submit",method = RequestMethod.POST)
    public String postForm(@ModelAttribute("component")CalculatorComponent component,
                           @ModelAttribute("saveFunctions")SaveFunctionComponent saveFunctionComponent,
                           Model model){
        switch (component.getTypeOfFunction()) {
            case "FirstFuncMath", "FirstFuncTab" ->
                    TabulatedFunctionSerialization.serialize("savedFunctions/first functions/" + saveFunctionComponent.getFunctionName() +".bin", component.getOper1());
            case "SecondFuncMath", "SecondFuncTab" ->
                    TabulatedFunctionSerialization.serialize("savedFunctions/second functions/" + saveFunctionComponent.getFunctionName()+".bin", component.getOper2());
            case "ResultFunc" ->
                    TabulatedFunctionSerialization.serialize("savedFunctions/results/" + saveFunctionComponent.getFunctionName()+".bin", component.getResult());
        }

        return"redirect:/calculator";
    }
}
