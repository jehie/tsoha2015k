/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ostoskassi.Lento;

import Ostoskassi.tavara.Tavara;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jesse
 */
@Controller
public class LentoController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @ResponseBody
    @RequestMapping("/lento")
    public String greeting() {
        return "Toimii";
    }

    @ResponseBody
    @RequestMapping("*")
    public String greeting1() {
        return "Toimii";
    }

}
