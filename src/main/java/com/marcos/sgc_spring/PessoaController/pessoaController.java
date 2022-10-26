package com.marcos.sgc_spring.PessoaController;

import com.marcos.sgc_spring.PessoaModel.pessoaModel;
import com.marcos.sgc_spring.PessoaRepositorio.pessoaRepositorio;
import com.marcos.sgc_spring.PessoaRepositorio.pessoaRepositorioCustom;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoa")
@AllArgsConstructor
public class pessoaController {

    private pessoaRepositorio pessoaRepositorio;
    private pessoaRepositorioCustom pessoaRepositorioCustom;

    @GetMapping
    public List<pessoaModel> list() {
        return pessoaRepositorio.findAll(Sort.by(Sort.Direction.ASC, "falcodigo"));
    }

    @GetMapping("/{falcodigo}")
    public Optional<pessoaModel> listById(@PathVariable int falcodigo) {
        return pessoaRepositorio.findById(falcodigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody pessoaModel pessoa) {
        try {
            pessoaRepositorio.save(pessoa);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/alter/{falcodigo}")
    public ResponseEntity alter(@PathVariable int falcodigo, @RequestBody pessoaModel pessoa) {
        return pessoaRepositorio.findById(falcodigo).map(
                response -> {
                    response.setFalcodigo(pessoa.getFalcodigo());
                    response.setFalnome(pessoa.getFalnome());
                    response.setFalcpf(pessoa.getFalcpf());
                    response.setFalsexo(pessoa.getFalsexo());
                    response.setFalnascimento(pessoa.getFalnascimento());
                    response.setFalnaturalidade(pessoa.getFalnaturalidade());
                    response.setFalfalecimento(pessoa.getFalfalecimento());
                    response.setFalmedresp(pessoa.getFalmedresp());
                    pessoaModel updatePessoa = response;
                    pessoaRepositorio.save(updatePessoa);
                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{falcodigo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable int falcodigo) {
        try {
            pessoaRepositorio.deleteById(falcodigo);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Pessoa ja em uso, Impossivel Exclusao"));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entidade nao encontrada"));
        }
    }

    @GetMapping("/name/{falnome}")
    public pessoaModel getByNome(@PathVariable String falnome) {
        return pessoaRepositorio.findByFalnome(falnome);
    }

    @GetMapping("/cod")
    public int lastCod() {
        return pessoaRepositorio.lastCod();
    }

    @GetMapping("/nameFal")
    public List<pessoaModel> nameFal(@RequestParam String falnome) {
        return pessoaRepositorio.findByFalnomeContains(falnome);
    }

    @GetMapping("/custom")
    public List<pessoaModel> findCustom(@RequestParam(value = "nome", required = false) String nome,
                                        @RequestParam(value = "sexo", required = false) String sexo,
                                        @RequestParam(value = "dtNasc", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dtNasc
    ) {

        return pessoaRepositorioCustom.findCustom(nome, dtNasc, sexo);
    }


}
