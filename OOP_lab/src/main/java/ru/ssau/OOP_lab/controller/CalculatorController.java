package ru.ssau.OOP_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.ssau.OOP_lab.components.CalculatorComponent;
import ru.ssau.OOP_lab.components.MathFunctionComponent;
import ru.ssau.OOP_lab.components.SettingsComponent;
import ru.ssau.OOP_lab.components.TabulatedFunctionComponent;
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.io.TabulatedFunctionSerialization;
import ru.ssau.OOP_lab.serializable.SerializeComponents;



@Controller
@RequestMapping(value = "/calculator")
@SessionAttributes({"mathFunction","tabulatedFunctionComponent","component"})
public class CalculatorController {

    CalculatorComponent component = new CalculatorComponent();
    @RequestMapping(method = RequestMethod.GET)
    public String getFunctions(@ModelAttribute("mathFunction")MathFunctionComponent mathFunctionComponent,
                                @ModelAttribute("tabulatedFunctionComponent")TabulatedFunctionComponent tabulatedFunctionComponent,
                                Model model)
    {
        if(component != null && component.getTypeOfFunction()!= null){
            switch (component.getTypeOfFunction()) {
                case "createFirstFuncTab" -> {
                    tabulatedFunctionComponent.createTabulatedFunction();
                    component.setOper1(tabulatedFunctionComponent.getFunc());
                }
                case "createSecondFuncTab" -> {
                    tabulatedFunctionComponent.createTabulatedFunction();
                    System.out.println(tabulatedFunctionComponent.getFunc());
                    component.setOper2(tabulatedFunctionComponent.getFunc());
                }
                case "createFirstFuncMath" -> {
                    mathFunctionComponent.createTabulatedFunction();
                    component.setOper1(mathFunctionComponent.getFunc());
                }
                case "createSecondFuncMath" -> {
                    mathFunctionComponent.createTabulatedFunction();
                    component.setOper2(mathFunctionComponent.getFunc());
                }
            }
        }
        else {

            SettingsComponent comp = SerializeComponents.deserialize("savedFunctions/settings/settings.bin");
            TabulatedFunctionFactory factory = comp.getFactory();

            component.setOperationService(factory);
        }
        model.addAttribute("component", component);

        return "calculator";
    }
    @RequestMapping(params="submit",method = RequestMethod.POST)
    public String getResult(@ModelAttribute("component") CalculatorComponent comp,
                            @ModelAttribute("mathFunction") MathFunctionComponent component,
                            Model model){

        comp.doOperation();

        model.addAttribute("component",comp);
        return "calculator";
    }
    @RequestMapping(params = "createSecondFuncTab", method = RequestMethod.POST)
    public String createSecondFuncTab(@ModelAttribute("component")CalculatorComponent component,
                                      Model model){
        component.setTypeOfFunction("createSecondFuncTab");
        return "redirect:/createTabulatedFunction";
    }
    @RequestMapping(params = "createSecondFuncMath", method = RequestMethod.POST)
    public String createSecondFuncMath(@ModelAttribute("component")CalculatorComponent component,
                                      Model model){
        component.setTypeOfFunction("createSecondFuncMath");
        return "redirect:/createMathTabulatedFunction";
    }
    @RequestMapping(params = "createFirstFuncTab", method = RequestMethod.POST)
    public String createFirstFuncTab(@ModelAttribute("component")CalculatorComponent component,
                                      Model model){
        component.setTypeOfFunction("createFirstFuncTab");
        return "redirect:/createTabulatedFunction";
    }
    @RequestMapping(params = "createFirstFuncMath", method = RequestMethod.POST)
    public String createFirstFuncMath(@ModelAttribute("component")CalculatorComponent component,
                                       Model model){
        component.setTypeOfFunction("createFirstFuncMath");
        return "redirect:/createMathTabulatedFunction";
    }
}
