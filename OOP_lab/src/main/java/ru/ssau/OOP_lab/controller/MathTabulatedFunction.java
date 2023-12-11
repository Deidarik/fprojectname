package ru.ssau.OOP_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.ssau.OOP_lab.components.Components;
import ru.ssau.OOP_lab.components.MathFunctionComponent;
import ru.ssau.OOP_lab.components.SettingsComponent;
import ru.ssau.OOP_lab.functions.*;
import ru.ssau.OOP_lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.io.ArrayTabulatedFunctionSerialization;
import ru.ssau.OOP_lab.io.LinkedListTabulatedFunctionSerialization;
import ru.ssau.OOP_lab.io.TabulatedFunctionSerialization;
import ru.ssau.OOP_lab.serializable.SerializeComponents;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/createMathTabulatedFunction")
@SessionAttributes({"mathFunction"})
public class MathTabulatedFunction {
    public static Map<String,MathFunction> map = new HashMap<>();
    @RequestMapping(method = RequestMethod.GET)
    public String getForm(Model model){

        map.put("Identity Function",new IdentityFunction());
        map.put("Sqr Function",new SqrFunction());
        map.put("Unit Function",new UnitFunction());


        model.addAttribute("mathFunction",new MathFunctionComponent());
        model.addAttribute("functions",map);

        return "createMathTabulatedFunction";
    }
    @RequestMapping(name = "submit", method = RequestMethod.POST)
    public String setForm(@ModelAttribute("mathFunction") MathFunctionComponent component,
                          Model model){
        model.addAttribute("functions",map);

        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

        try{
            SettingsComponent comp = SerializeComponents.deserialize("savedFunctions/settings/settings.bin");
            factory = comp.getFactory();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        TabulatedFunction func = factory.create(map.get(component.getFunction()),
                                                component.getxFrom(),
                                                component.getxTo(),
                                                component.getSize());

        TabulatedFunctionSerialization.serialize("savedFunctions/linked list/funcCreatedWithMathFunction.bin",func);
        System.out.println(TabulatedFunctionSerialization.deserialize("savedFunctions/linked list/funcCreatedWithMathFunction.bin"));

        return "index";
    }
}
