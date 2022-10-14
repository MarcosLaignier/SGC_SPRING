package com.marcos.sgc_spring.ConfiguracaoController;

import com.marcos.sgc_spring.ConfiguracaoModel.configuracaoModel;
import com.marcos.sgc_spring.ConfiguracaoRepositorio.configuracaoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/config")
@AllArgsConstructor
@RestController
public class configuracaoController {

    configuracaoRepositorio configuracaoRepositorio;

    //    @GetMapping("/municipio")
//    public String findNameMunicipio(){
//        return configuracaoRepositorio.findName();
//    }
    @GetMapping("/municipio")
    public List<configuracaoModel> findNameMunicipio() {
        return configuracaoRepositorio.findAll();
    }

    @PutMapping("/alter/{codCliente}")
    public ResponseEntity alterNameMunicipio(@PathVariable int codCliente,@RequestBody configuracaoModel dados) {
        return configuracaoRepositorio.findById(codCliente).map(
                response -> {
                    response.setCODCLIENTE(dados.getCODCLIENTE());
                    response.setSGCMUNICIPIO(dados.getSGCMUNICIPIO());
                    response.setSGCPATHIMG(dados.getSGCPATHIMG());
                    response.setSGCPATHLOGO(dados.getSGCPATHLOGO());
                    response.setSGCTIPOMUNICIPIO(dados.getSGCTIPOMUNICIPIO());
                    configuracaoModel updateC = configuracaoRepositorio.save(response);;
                    System.out.println(updateC);

                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.badRequest().build());
    }
}
