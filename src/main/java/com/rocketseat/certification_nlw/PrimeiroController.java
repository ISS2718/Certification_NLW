package com.rocketseat.certification_nlw;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/primeiroController")
public class PrimeiroController {

    @GetMapping("/retornarPrimeiroController")
    public Usuario retornoPrimeiroController() {
        var user = new Usuario("Isaac", 22);
        return user;
    }

    @PostMapping("/meuPrimeiroPost")
    public String primeiroPost() {
        return "Meu Primeiro Post";
    }

    /**
     * Usuario
     */
    public record Usuario(String nome, int idade) {}
}
