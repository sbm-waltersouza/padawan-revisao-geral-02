package com.example.canais;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canais")
public class CanalController {

    private final CanalRepository canalRepository;

    public CanalController(CanalRepository canalRepository) {
        this.canalRepository = canalRepository;
    }

    @GetMapping
    public List<Canal> listar() {
        return canalRepository.listar();
    }

    @GetMapping("/{id}")
    public Canal buscarPorId(@PathVariable int id) {
        return canalRepository.buscarPorId(id);
    }

    @PostMapping
    public void adicionar(@RequestBody Canal canal) {
        canalRepository.adicionar(canal);
    }

    @PutMapping("/{id}")
    public void atualizar(@RequestBody Canal canal) {
        canalRepository.atualizar(canal);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable int id) {
        canalRepository.excluir(id);
    }

    @GetMapping("/filtrar")
    public List<Canal> filtrar(
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false, defaultValue = "") String descricao,
            @RequestParam(required = false) Integer quantidadeInscritos) {
        return canalRepository.filtrar(nome, descricao, quantidadeInscritos);
    }
}
