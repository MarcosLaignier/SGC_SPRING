package com.marcos.sgc_spring.PessoaController;

import com.marcos.sgc_spring.PessoaModel.pessoaModel;
import com.marcos.sgc_spring.PessoaRepositorio.pessoaRepositorio;
import com.marcos.sgc_spring.PessoaRepositorio.pessoaRepositorioCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public pessoaModel insert(@RequestBody pessoaModel pessoa) {

        return pessoaRepositorio.save(pessoa);
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
    public ResponseEntity delete(@PathVariable int falcodigo){
        return pessoaRepositorio.findById(falcodigo).map(
                response ->{
                    pessoaRepositorio.deleteById(falcodigo);
                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cod")
    public int lastCod(){
        return pessoaRepositorio.lastCod();
    }

    @GetMapping("/nameFal")
    public List<pessoaModel> nameFal(@RequestParam String falnome){
        return pessoaRepositorio.findByFalnomeContains(falnome);
    }

    @GetMapping("/custom")
    public List<pessoaModel> findCustom(@RequestParam (value = "nome",required = false) String nome , @RequestParam (value = "sexo",required = false)String sexo){
        return pessoaRepositorioCustom.findCustom(nome,sexo);
    }



}
