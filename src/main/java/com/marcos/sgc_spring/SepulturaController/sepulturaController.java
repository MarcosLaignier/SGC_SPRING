package com.marcos.sgc_spring.SepulturaController;


import com.marcos.sgc_spring.SepulturaModel.sepulturaModel;
import com.marcos.sgc_spring.SepulturaRepositorio.sepulturaRepositorio;
import com.marcos.sgc_spring.SepulturaRepositorio.sepulturaRepositorioCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    ;

    @GetMapping("/{id}")
    public Optional<sepulturaModel> listId(@PathVariable int id) {
        return sepulturarepositorio.findById(id);
    }

    @GetMapping("/cod")
    public int getCod() {
        return sepulturarepositorio.findCod();
    }

    @PostMapping
    public sepulturaModel insert(@RequestBody sepulturaModel dadosSepultura) {
        return sepulturarepositorio.save(dadosSepultura);
    }

    @PutMapping("/alter/{sepcodigo}")
    public ResponseEntity alterSepultura(@PathVariable int sepcodigo ,@RequestBody sepulturaModel sepultura){
        return sepulturarepositorio.findById(sepcodigo).map(
                response ->{
                    response.setSepcodigo(sepultura.getSepcodigo());
                    response.setSepdescricao(sepultura.getSepdescricao());
                    response.setSepcemiterio(sepultura.getSepcemiterio());
                    sepulturaModel updateS = response;
                    sepulturarepositorio.save(updateS);
                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        return sepulturarepositorio.findById(id)
                .map(response -> {
                            sepulturarepositorio.deleteById(id);
                            return ResponseEntity.ok().build();
                        }
                ).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/custom")
    public List<sepulturaModel> findCustom(@RequestParam (value = "codigo",required = false)String codigo,
                                           @RequestParam(value = "descricao",required = false)String descricao,
                                           @RequestParam(value = "cemiterio",required = false)String cemiterio){
        return sepulturaRepositorioCustom.findSepCustom(codigo,descricao,cemiterio);
    }


}
