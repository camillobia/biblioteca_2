package biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblioteca.model.Livro;

@RestController
public class LivroController {

    private static final List<Livro> livros = new ArrayList<>();
    private static Long proximoId = 1L;

    @GetMapping("/")
    public String inicio() {
        return "Bem-vindo ao sistema de gerenciamento da Biblioteca CloudTech Solutions";
    }

    @GetMapping("/livros")
    public List<Livro> listarLivros() {
        return livros;
    }

    @PostMapping("/livros/cadastrar")
    public Livro cadastrarLivro(@RequestBody Livro livro) {
        livro.setId(proximoId);
        proximoId++;

        livros.add(livro);

        return livro;
    }

    @PutMapping("/livros/alterar/{id}")
    public Livro alterarLivro(@PathVariable Long id, @RequestBody Livro livroAtualizado) {

        for (Livro livro : livros) {

            if (livro.getId().equals(id)) {

                livro.setTitulo(livroAtualizado.getTitulo());
                livro.setAutor(livroAtualizado.getAutor());
                livro.setEditora(livroAtualizado.getEditora());
                livro.setAno(livroAtualizado.getAno());
                livro.setCategoria(livroAtualizado.getCategoria());
                livro.setStatus(livroAtualizado.getStatus());

                return livro;
            }
        }

        return null;
    }

    @GetMapping("/livros/{id}")
    public Livro consultarLivro(@PathVariable Long id) {

        for (Livro livro : livros) {

            if (livro.getId().equals(id)) {
                return livro;
            }
        }

        return null;
    }

    @GetMapping("/documentacao")
    public List<String> documentacao() {

        List<String> endpoints = new ArrayList<>();

        endpoints.add("/");
        endpoints.add("/livros");
        endpoints.add("/livros/cadastrar");
        endpoints.add("/livros/alterar/{id}");
        endpoints.add("/livros/{id}");
        endpoints.add("/documentacao");

        return endpoints;
    }
}