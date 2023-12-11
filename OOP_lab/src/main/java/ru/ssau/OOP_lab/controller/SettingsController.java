package ru.ssau.OOP_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ssau.OOP_lab.components.SettingsComponent;
import ru.ssau.OOP_lab.serializable.SerializeComponents;

import java.io.Serializable;

@Controller
@RequestMapping(value = "/settings")
public class SettingsController {


    @RequestMapping(method = RequestMethod.GET)
    public String setSettings(Model model){
        model.addAttribute("settings", new SettingsComponent());
        return "/settings";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String getSettings(@ModelAttribute("settings") SettingsComponent settings,
                                Model model){
        settings.makeFactory();
        model.addAttribute("message",settings.getFactory());
        SerializeComponents.serialize("savedFunctions/settings/settings.bin",settings);
        System.out.println(SerializeComponents.deserialize("savedFunctions/settings/settings.bin"));
        return "/index";
    }
}
