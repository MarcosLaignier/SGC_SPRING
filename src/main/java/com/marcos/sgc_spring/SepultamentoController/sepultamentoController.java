package com.marcos.sgc_spring.SepultamentoController;

import com.marcos.sgc_spring.SepultamentoModel.sepultamentoModel;
import com.marcos.sgc_spring.SepultamentoRepositorio.sepultamentoRepositorio;
import com.marcos.sgc_spring.SepultamentoRepositorio.sepultamentoRepositorioCustom;
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

@AllArgsConstructor
@RestController
@RequestMapping("/api/sepultamentos")
public class sepultamentoController {

    private sepultamentoRepositorio sepultamentoRepositorio;
    private sepultamentoRepositorioCustom sepultamentorepositorioCustom;

    @GetMapping()
    public List<sepultamentoModel> listAll() {
        return sepultamentoRepositorio.findAll(Sort.by(Sort.Direction.ASC, "sepulcodigo"));
    }

    @GetMapping("/{sepulcodigo}")
    public Optional<sepultamentoModel> listById(@PathVariable int sepulcodigo) {
        return sepultamentoRepositorio.findById(sepulcodigo);
    }

    @GetMapping("/nameFun")
    public List nameFun() {
        return sepultamentoRepositorio.nameFun();
    }

    @GetMapping("/nameCem")
    public List nameCem() {
        return sepultamentoRepositorio.nameCem();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody sepultamentoModel sepultamento) {
        try {
            sepultamentoRepositorio.save(sepultamento);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/alter/{sepulcodigo}")
    public ResponseEntity alterSepultamento(@PathVariable int sepulcodigo, @RequestBody sepultamentoModel sepultamento) {
        return sepultamentoRepositorio.findById(sepulcodigo).map(
                data -> {
//                    data.setSepulcodigo(sepultamento.getSepulcodigo());
                    data.setSepulfalecido(sepultamento.getSepulfalecido());
                    data.setSepulcpffal(sepultamento.getSepulcpffal());
                    data.setSepdatafalecimento(sepultamento.getSepdatafalecimento());
                    data.setSepdatasepultamento(sepultamento.getSepdatasepultamento());
                    data.setSepulfuneraria(sepultamento.getSepulfuneraria());
                    data.setSepulcemiterio(sepultamento.getSepulcemiterio());
                    data.setSepulsepultura(sepultamento.getSepulsepultura());
                    data.setPessoa(sepultamento.getPessoa());
                    data.setCemiterio(sepultamento.getCemiterio());
                    data.setFuneraria(sepultamento.getFuneraria());
                    sepultamentoModel updateS = data;
                    sepultamentoRepositorio.save(updateS);
                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{sepulcodigo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSepultamento(@PathVariable int sepulcodigo) {
        try {
            sepultamentoRepositorio.deleteById(sepulcodigo);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entidade não encontrada!"));
        }
    }

    @GetMapping("/cod")
    public int lastCodSepul() {
        return sepultamentoRepositorio.lasCod();
    }

//    @GetMapping("/t")
//    public List<sepultamentoModel> listParam(@RequestParam int id){
//        return sepultamentoRepositorio.filterParam(id);
//    }

    @GetMapping("/Filter")
    public List<sepultamentoModel> findCustom(
            @RequestParam(value = "pessoa", required = false) String pessoa,
            @RequestParam(value = "cpf", required = false) String cpf,
            @RequestParam(value = "Cemiterio", required = false) String Cemiterio
    ) {
        return sepultamentorepositorioCustom.find(pessoa, cpf, Cemiterio);
    }

}
