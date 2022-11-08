package com.marcos.sgc_spring.JasperController;

import com.marcos.sgc_spring.JasperService.jasperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api/reports")
public class jasperController {
    @Autowired
    private jasperService service;

    @GetMapping("/rel/listagemCemiterio/{name}/{geraNovaAba}")
    public void gerarRelatorioListagemCemiterio(@PathVariable("name") String name,
                                                @PathVariable("geraNovaAba") String geraNovaAba,
                                                HttpServletResponse response) throws IOException {
        byte[] bytes = service.exportarPDF(name);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        if (geraNovaAba.equals("s")) {
            response.setHeader("Content-disposition", "inline; filename=relatorio-" + name + ".pdf");
        } else {
            response.setHeader("Content-disposition", "attachment; filename=relatorio-" + name + ".pdf");

        }
        response.getOutputStream().write(bytes);
    }
}
