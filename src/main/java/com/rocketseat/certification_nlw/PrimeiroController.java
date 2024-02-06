package com.rocketseat.certification_nlw;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/primeiroController")
public class PrimeiroController {
    public String retornoPrimeiroController() {
        return "Criando o Primeiro Controller";
    }
}
