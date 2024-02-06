package com.rocketseat.certification_nlw;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/primeiroController")
public class PrimeiroController {

    @GetMapping("/retornarPrimeiroController")
    public Usuario retornoPrimeiroController() {
        var user = new Usuario("Isaac", 22);
        return user;
    }

    /**
     * Usuario
     */
    public record Usuario(String nome, int idade) {}
}
