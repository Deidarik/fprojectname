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
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.io.TabulatedFunctionSerialization;
import ru.ssau.OOP_lab.serializable.SerializeComponents;

import static ru.ssau.OOP_lab.controller.MathTabulatedFunction.map;


@Controller
@RequestMapping(value = "/calculator")
@SessionAttributes({"mathFunction"})
public class CalculatorController {

    @RequestMapping(method = RequestMethod.GET)
    public String getFunctions(Model model)
    {
        CalculatorComponent component = new CalculatorComponent();
        SettingsComponent comp = SerializeComponents.deserialize("savedFunctions/settings/settings.bin");
        TabulatedFunctionFactory factory = comp.getFactory();

        model.addAttribute("component", component);

        return "calculator";
    }
    @RequestMapping(params="submit",method = RequestMethod.POST)
    public String getResult(@ModelAttribute("component") CalculatorComponent comp,
                            @ModelAttribute("mathFunction") MathFunctionComponent component,
                            Model model){
        SettingsComponent component1 = SerializeComponents.deserialize("savedFunctions/settings/settings.bin");
        TabulatedFunctionFactory factory = component1.getFactory();

        TabulatedFunction func = factory.create(map.get(component.getFunction()),
                component.getxFrom(),
                component.getxTo(),
                component.getSize());

        comp.setOper1(func);
        comp.setOper2(func);

        comp.doOperation();

        model.addAttribute("component",comp);
        return "calculator";
    }
}
