package com.marcos.sgc_spring.Cemiterios.Controller;

import com.marcos.sgc_spring.CemiterioModel.cemiterioModel;
import com.marcos.sgc_spring.CemiterioRepositorio.cemiterioRepositorio;
import com.marcos.sgc_spring.CemiterioRepositorio.cemiterioRepositorioCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/cod")
    public int listCod(){
        return cemiterioRepositorio.findcod();
    }

    @GetMapping("/nameCemiterios")
    public List<String> listNameCemiterio(){
        return  cemiterioRepositorio.findnameCemiterio();
    }

    @PostMapping
    public cemiterioModel Create(@RequestBody cemiterioModel cemiterio) {
        cemiterioRepositorio.save(cemiterio);
        return cemiterio;
    }

    @DeleteMapping("/{undcodigo}")
    public ResponseEntity delete(@PathVariable int undcodigo) {
        return cemiterioRepositorio.findById(undcodigo)
                .map(record -> {
                    cemiterioRepositorio.deleteById(undcodigo);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/alter/{undcodigo}")
    public ResponseEntity put(@PathVariable int undcodigo, @RequestBody cemiterioModel cemiterio) {
        return cemiterioRepositorio.findById(undcodigo).map(
                data -> {
                    data.setUndcodigo(cemiterio.getUndcodigo());
                    data.setUndnome(cemiterio.getUndnome());
                    data.setUndendereco(cemiterio.getUndendereco());
                    data.setUndnumero(cemiterio.getUndnumero());
                    data.setUndcidade(cemiterio.getUndcidade());
                    data.setUndestado(cemiterio.getUndestado());
                    data.setUndresponsavel(cemiterio.getUndresponsavel());
                    cemiterioModel updateC = cemiterioRepositorio.save(data);
                    return ResponseEntity.ok().body(updateC);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/t")
    public String teste(@RequestBody int nameCemiterio){
        return cemiterioRepositorio.num(nameCemiterio);
    }

    @GetMapping("/custom")
    public List<cemiterioModel> findCustom(@RequestParam(value = "codigo",required = false)String codigo,
                                           @RequestParam(value = "nome",required = false)String nome,
                                           @RequestParam(value = "responsavel",required = false)String responsavel){
        return cemiterioRepositorioCustom.findUndCustom(codigo,nome,responsavel);
    }


}
