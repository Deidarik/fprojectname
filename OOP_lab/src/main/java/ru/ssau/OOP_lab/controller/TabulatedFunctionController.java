package ru.ssau.OOP_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ssau.OOP_lab.components.AmountOfPointsComponent;
import ru.ssau.OOP_lab.functions.Point;
import ru.ssau.OOP_lab.functions.TabulatedFunction;
import ru.ssau.OOP_lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.OOP_lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.OOP_lab.io.LinkedListTabulatedFunctionSerialization;
import ru.ssau.OOP_lab.operations.TabulatedFunctionOperationService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/createTabulatedFunction")
public class TabulatedFunctionController {

    private Integer size = 0;
    private List<Point> pointList = new ArrayList<>();
    private AmountOfPointsComponent amountOfPoints = new AmountOfPointsComponent();

    @RequestMapping(method = RequestMethod.GET)
    public String amountOfPointsForm(Model model){
        model.addAttribute("amountOfPointsComponent",amountOfPoints );
        model.addAttribute("point", new Point());
        return "createTabulatedFunction";
    }

    @RequestMapping(params = "submit", method = RequestMethod.POST)
    public String amountOfPointsSubmit(@ModelAttribute AmountOfPointsComponent amountOfPointsComponent,
                                       @ModelAttribute Point point,
                                       Model model){
        if( amountOfPoints.getAmount() == 0) {
            model.addAttribute("amountOfPointsComponent", amountOfPointsComponent);
            amountOfPoints = amountOfPointsComponent;
        }else
            model.addAttribute("amountOfPointsComponent", amountOfPoints);
        return "createTabulatedFunction";

    }
    @RequestMapping(params = "add", method = RequestMethod.POST)
    public String pointAdd(@ModelAttribute AmountOfPointsComponent amountOfPointsComponent,
                           @ModelAttribute Point point,
                           Model model){
        if(amountOfPoints!= null && size < amountOfPoints.getAmount()) {
            if( pointList.size() < 1
                ||
                point.getX() >= pointList.get(pointList.size()-1).getX()
                &&
                point.getY() >= pointList.get(pointList.size()-1).getY() ) {
                pointList.add(point);
                size++;
            }
            else{
                model.addAttribute("message", "Wrong values, the list of points should be sorted");
            }
        }else if(!pointList.isEmpty()) model.addAttribute("message", "Your List is full");

        assert amountOfPoints != null;
        if(amountOfPoints.getAmount() != 0)
            model.addAttribute("points",pointList);

        model.addAttribute("amountOfPointsComponent", amountOfPoints);
        return "createTabulatedFunction";
    }
    @RequestMapping(params = "reset",method = RequestMethod.POST)
    public String valuesReset(@ModelAttribute AmountOfPointsComponent amountOfPointsComponent,
                              @ModelAttribute Point point,
                              Model model){
        pointList = new ArrayList<>();
        amountOfPoints.setAmount(0);
        size = 0;
        model.addAttribute("amountOfPointsComponent", amountOfPoints);
        model.addAttribute("points", pointList);
        return "createTabulatedFunction";
    }
    @RequestMapping(params = "create",method = RequestMethod.POST)
    public String createTabulatedFunction(@ModelAttribute AmountOfPointsComponent amountOfPointsComponent,
                                          @ModelAttribute Point point,
                                          Model model){
        if(!pointList.isEmpty()) {
            TabulatedFunctionFactory funcFactory = new LinkedListTabulatedFunctionFactory();
            double[][] values = TabulatedFunctionOperationService.listOfPointsAsMassive(pointList);
            TabulatedFunction func = funcFactory.create(values[0],values[1]);

            LinkedListTabulatedFunctionSerialization.serialize("savedFunctions/linked list/funcCreatedWithMassive.bin",func);
            System.out.println(LinkedListTabulatedFunctionSerialization.deserialize("savedFunctions/linked list/funcCreatedWithMassive.bin"));

            model.addAttribute("message", "Tabulated function created");
            valuesReset(amountOfPointsComponent,point,model);
        } else if (amountOfPoints.getAmount() != 0){
            model.addAttribute("amountOfPointsComponent", amountOfPoints);
            model.addAttribute("points", pointList);
            model.addAttribute("notification", "Your list is empty");
        }
        return "createTabulatedFunction";
    }

}
