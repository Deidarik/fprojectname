package ru.ssau.OOP_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.OOP_lab.components.TabulatedFunctionComponent;
import ru.ssau.OOP_lab.components.SettingsComponent;
import ru.ssau.OOP_lab.functions.Point;
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.io.TabulatedFunctionSerialization;
import ru.ssau.OOP_lab.operations.TabulatedFunctionOperationService;
import ru.ssau.OOP_lab.serializable.SerializeComponents;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/createTabulatedFunction")
@SessionAttributes({"tabulatedFunctionComponent"})
public class TabulatedFunctionController {
    private Integer size;

    @RequestMapping(method = RequestMethod.GET)
    public String amountOfPointsForm(Model model){

        size = 0;

        TabulatedFunctionComponent functionComponent = new TabulatedFunctionComponent();
        model.addAttribute("tabulatedFunctionComponent",functionComponent );

        return "createTabulatedFunction";
    }

    @RequestMapping(params = "submit", method = RequestMethod.POST)
    public String amountOfPointsSubmit(@ModelAttribute("tabulatedFunctionComponent") TabulatedFunctionComponent tabulatedFunctionComponent,
                                       @ModelAttribute("point")Point pt,
                                       Model model){
        model.addAttribute("tabulatedFunctionComponent", tabulatedFunctionComponent);
        return "createTabulatedFunction";

    }
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String pointAdd(@ModelAttribute("tabulatedFunctionComponent") TabulatedFunctionComponent tabulatedFunctionComponent,

                           Model model){
        Integer amountOfPoints = tabulatedFunctionComponent.getAmount();
        List<Point> pointList= tabulatedFunctionComponent.getPointList();
        if(size < amountOfPoints) {
            if( pointList.size() < 1
                ||
                    tabulatedFunctionComponent.getPoint().getX() >= pointList.get(pointList.size()-1).getX()
                &&
                tabulatedFunctionComponent.getPoint().getY() >= pointList.get(pointList.size()-1).getY() ) {

                pointList.add(tabulatedFunctionComponent.getPoint());
                tabulatedFunctionComponent.setPointList(pointList);
                size++;
            }
            else{
                model.addAttribute("message", "Wrong values, the list of points should be sorted");
            }
        }else if(!pointList.isEmpty()) model.addAttribute("message", "Your List is full");

        model.addAttribute("tabulatedFunctionComponent", tabulatedFunctionComponent);
        return "createTabulatedFunction";
    }
    @RequestMapping(params = "reset",method = RequestMethod.POST)
    public String valuesReset(@ModelAttribute("tabulatedFunctionComponent") TabulatedFunctionComponent tabulatedFunctionComponent,
                              @ModelAttribute("point") Point point,
                              Model model){
        tabulatedFunctionComponent.setPointList(new ArrayList<>());
        tabulatedFunctionComponent.setAmount(0);
        size = 0;
        model.addAttribute("tabulatedFunctionComponent", tabulatedFunctionComponent);
        return "createTabulatedFunction";
    }
    @RequestMapping(params = "create",method = RequestMethod.POST)
    public String createTabulatedFunction(@ModelAttribute("tabulatedFunctionComponent") TabulatedFunctionComponent tabulatedFunctionComponent,
                                          @ModelAttribute Point point,
                                          Model model){
        List<Point> pointList = tabulatedFunctionComponent.getPointList();
        Integer amountOfPoints = tabulatedFunctionComponent.getAmount();
        if(!pointList.isEmpty()) {
            tabulatedFunctionComponent.createTabulatedFunction();

            System.out.println(tabulatedFunctionComponent.getFunc());

            model.addAttribute("message", "Tabulated function created");
        } else if (amountOfPoints != 0){
            model.addAttribute("tabulatedFunctionComponent", tabulatedFunctionComponent);
            model.addAttribute("notification", "Your list is empty");
        }
        return "index";
    }

}
