package com.marcos.sgc_spring.Cemiterios.Controller;

import com.marcos.sgc_spring.CemiterioModel.cemiterioModel;
import com.marcos.sgc_spring.CemiterioRepositorio.cemiterioRepositorio;
import com.marcos.sgc_spring.CemiterioRepositorio.cemiterioRepositorioCustom;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/cemiterios")
@AllArgsConstructor
public class cemiterioController {

    private cemiterioRepositorio cemiterioRepositorio;
    private cemiterioRepositorioCustom cemiterioRepositorioCustom;

    @GetMapping
    public List<cemiterioModel> list() {

        return cemiterioRepositorio.findAll(Sort.by(Sort.Direction.ASC, "undcodigo"));
    }

    @GetMapping(path = {"/{id}"})
    public Optional<cemiterioModel> findById(@PathVariable int id) {
        return cemiterioRepositorio.findById(id);

    }

    @GetMapping("/nome/{undnome}")
    public cemiterioModel findByNome(@PathVariable String undnome) {
        return cemiterioRepositorio.findByUndnome(undnome);
    }

    @GetMapping("/cod")
    public int listCod() {
        return cemiterioRepositorio.findcod();
    }

    @GetMapping("/nameCemiterios")
    public List<String> listNameCemiterio() {
        return cemiterioRepositorio.findnameCemiterio();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void Create(@RequestBody cemiterioModel cemiterio) {
        try {
            cemiterioRepositorio.save(cemiterio);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{undcodigo}")
    public void delete(@PathVariable int undcodigo) {
        try {
            cemiterioRepositorio.deleteById(undcodigo);

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Cemiterio em uso, Impossivel Exclusao!"));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entidade nao encontrada"));
        }

    }


    @PutMapping("/alter/{undcodigo}")
    public ResponseEntity put(@PathVariable int undcodigo, @RequestBody cemiterioModel cemiterio) {
        return cemiterioRepositorio.findById(undcodigo).map(
                data -> {
                    data.setUndnome(cemiterio.getUndnome());
                    data.setUndendereco(cemiterio.getUndendereco());
                    data.setUndnumero(cemiterio.getUndnumero());
                    data.setUndcidade(cemiterio.getUndcidade());
                    data.setUndestado(cemiterio.getUndestado());
                    data.setUndresponsavel(cemiterio.getUndresponsavel());
                    data.setStatus(cemiterio.isStatus());
                    cemiterioModel updateC = cemiterioRepositorio.save(data);
                    return ResponseEntity.ok().body(updateC);
                }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/custom")
    public List<cemiterioModel> findCustom(@RequestParam(value = "codigo", required = false) String codigo,
                                           @RequestParam(value = "nome", required = false) String nome,
                                           @RequestParam(value = "responsavel", required = false) String responsavel,
                                           @RequestParam(value = "status", required = false) String status) {
        return cemiterioRepositorioCustom.findUndCustom(codigo, nome, responsavel, status);
    }


}
