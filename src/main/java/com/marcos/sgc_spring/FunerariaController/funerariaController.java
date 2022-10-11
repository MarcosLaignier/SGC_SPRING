package com.marcos.sgc_spring.FunerariaController;

import com.marcos.sgc_spring.FunerariaModel.funerariaModel;
import com.marcos.sgc_spring.FunerariaRepositorio.funerariaRepositorio;
import com.marcos.sgc_spring.FunerariaRepositorio.funerariaRepositorioCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/funerarias")
@RestController
@AllArgsConstructor
public class funerariaController {

    private funerariaRepositorio funerariaRepositorio;
    private funerariaRepositorioCustom funerariaRepositorioCustom;

    @GetMapping
    public List<funerariaModel> list(){
        return funerariaRepositorio.findAll(Sort.by(Sort.Direction.ASC,"funcodigo"));
    };

    @GetMapping("/{funcodigo}")
    public Optional<funerariaModel> listId(@PathVariable int funcodigo){
        return funerariaRepositorio.findById(funcodigo);
    }

    @PostMapping
    public funerariaModel insert(@RequestBody funerariaModel funeraria){
        return funerariaRepositorio.save(funeraria);
    }

    @PutMapping("/alter/{funcodigo}")
    public ResponseEntity alter(@PathVariable int funcodigo , @RequestBody funerariaModel funeraria){
        return funerariaRepositorio.findById(funcodigo).map(
                response ->{
                    response.setFuncodigo(funeraria.getFuncodigo());
                    response.setFundescricao(funeraria.getFundescricao());
                    response.setFuncidade(funeraria.getFuncidade());
                    response.setFunendereco(funeraria.getFunendereco());
                    response.setFunnumero(funeraria.getFunnumero());
                    funerariaModel updateFun = funeraria;
                    funerariaRepositorio.save(updateFun);
                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{funcodigo}")
    public ResponseEntity delete(@PathVariable int funcodigo){
        return funerariaRepositorio.findById(funcodigo).map(
                response ->{
                    funerariaRepositorio.deleteById(funcodigo);
                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/codfun")
    public int lastCod(){
        return funerariaRepositorio.findCodFun();
    }

    @GetMapping("/custom")
    public List<funerariaModel> findFunCustom(@RequestParam(value = "codigo",required = false) String codigo,
                                              @RequestParam(value = "nome",required = false)String nome,
                                              @RequestParam(value = "cidade",required = false)String cidade
    ){
        return funerariaRepositorioCustom.findFunCustom(codigo,nome,cidade);
    }

}
