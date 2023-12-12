package ru.ssau.OOP_lab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.ssau.OOP_lab.components.DifferentialComponent;
import ru.ssau.OOP_lab.components.MathFunctionComponent;
import ru.ssau.OOP_lab.components.SettingsComponent;
import ru.ssau.OOP_lab.components.TabulatedFunctionComponent;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.serializable.SerializeComponents;

@Controller
@RequestMapping(value = "/differentiate")
@SessionAttributes({"mathFunction","tabulatedFunctionComponent","differentiateComponent"})
public class DifferentiateController {
    DifferentialComponent differentialComponent = new DifferentialComponent();
    @RequestMapping(method = RequestMethod.GET)
    public String getForm(@ModelAttribute("mathFunction") MathFunctionComponent mathFunctionComponent,
                          @ModelAttribute("tabulatedFunctionComponent") TabulatedFunctionComponent tabulatedFunctionComponent,
                          Model model){
//        if(differentialComponent != null || differentialComponent.getTypeOfFunction() != null) {
//            switch (differentialComponent.getTypeOfFunction()){
//                case "FirstFuncTab":
//                    tabulatedFunctionComponent.createTabulatedFunction();
//                    differentialComponent.setOper(tabulatedFunctionComponent.getFunc());
//                    break;
//                case "FirstFuncMath":
//                    mathFunctionComponent.createTabulatedFunction();
//                    differentialComponent.setOper(mathFunctionComponent.getFunc());
//                    break;
//            }
//        }else {
//            SettingsComponent comp = SerializeComponents.deserialize("savedFunctions/settings/settings.bin");
//            TabulatedFunctionFactory factory = comp.getFactory();
//
//            differentialComponent.setDifferentialOperator(factory);
//        }
//        model.addAttribute("differentiateComponent", differentialComponent);
        return "/differentiate";
    }
    @RequestMapping(params = "createFirstFuncTab",method = RequestMethod.POST)
    public String createFirstFuncTab (@ModelAttribute("differentiateComponent") DifferentialComponent differentialComponent,
                                    Model model){
        differentialComponent.setTypeOfFunction("FirstFuncTab");
        return "/createTabulatedFunction";
    }
    @RequestMapping(params = "createFirstFuncMath",method = RequestMethod.POST)
    public String createFirstFuncMath (@ModelAttribute("differentiateComponent") DifferentialComponent differentialComponent,
                                      Model model){
        differentialComponent.setTypeOfFunction("createFirstFuncTab");
        return "/createTabulatedFunction";
    }
    @RequestMapping(params = "submit",method = RequestMethod.POST)
    public String submitDifferential(@ModelAttribute("differentialComponent")DifferentialComponent differentialComponent,
                                     Model model){
        differentialComponent.doDefferentiate();
        model.addAttribute("differentialComponent", differentialComponent);
        return "differentiate";
    }


}
