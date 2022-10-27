package com.marcos.sgc_spring.SepulturaController;


import com.marcos.sgc_spring.SepulturaModel.sepulturaModel;
import com.marcos.sgc_spring.SepulturaRepositorio.sepulturaRepositorio;
import com.marcos.sgc_spring.SepulturaRepositorio.sepulturaRepositorioCustom;
import lombok.AllArgsConstructor;
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
@RequestMapping("/api/sepulturas")
@AllArgsConstructor
public class sepulturaController {

    private sepulturaRepositorio sepulturarepositorio;
    private sepulturaRepositorioCustom sepulturaRepositorioCustom;

    @GetMapping
    public List<sepulturaModel> list() {
        return sepulturarepositorio.findAll(Sort.by(Sort.Direction.ASC, "sepcodigo"));
    }

    @GetMapping("/{id}")
    public Optional<sepulturaModel> listId(@PathVariable int id) {
        return sepulturarepositorio.findById(id);
    }

    @GetMapping("/cod")
    public int getCod() {
        return sepulturarepositorio.findCod();
    }

    @GetMapping("/nome/{sepdescricao}")
    public sepulturaModel findByName(@PathVariable String sepdescricao) {
        return sepulturarepositorio.findBySepdescricao(sepdescricao);
    }

    @GetMapping("/sep/{sepcemiterio}")
    public sepulturaModel[] findByCemiterio(@PathVariable String sepcemiterio){
        return sepulturarepositorio.findBySepcemiterio(sepcemiterio);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody sepulturaModel dadosSepultura) {
        try {
            sepulturarepositorio.save(dadosSepultura);
//            return ResponseEntity.accepted().build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/alter/{sepcodigo}")
    public ResponseEntity alterSepultura(@PathVariable int sepcodigo, @RequestBody sepulturaModel sepultura) {
        return sepulturarepositorio.findById(sepcodigo).map(
                response -> {
//                    response.setSepcodigo(sepultura.getSepcodigo());
                    response.setSepdescricao(sepultura.getSepdescricao());
                    response.setSepcemiterio(sepultura.getSepcemiterio());
                    response.setCemiterio(sepultura.getCemiterio());
                    sepulturaModel updateS = response;
                    sepulturarepositorio.save(updateS);
                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable int id) {
        try {
            sepulturarepositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Sepultura ja em uso, Impossivel Exclusao!"));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entidade não encontrada!"));
        }
    }

    @GetMapping("/custom")
    public List<sepulturaModel> findCustom(@RequestParam(value = "codigo", required = false) String codigo,
                                           @RequestParam(value = "descricao", required = false) String descricao,
                                           @RequestParam(value = "cemiterio", required = false) String cemiterio) {
        return sepulturaRepositorioCustom.findSepCustom(codigo, descricao, cemiterio);
    }


}
