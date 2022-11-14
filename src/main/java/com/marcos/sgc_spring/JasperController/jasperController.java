package com.marcos.sgc_spring.JasperController;

import com.marcos.sgc_spring.JasperModel.relatorioModel;
import com.marcos.sgc_spring.JasperService.jasperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api/reports")
public class jasperController {
    @Autowired
    private jasperService service;

    @GetMapping("/rel/{name}/{geraNovaAba}")
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


    @GetMapping("/rel/{name}/{geraNovaAba}/{NAME_FILTER}={FILTER_CEMITERIO}")
    public void gerarRelatorioFiltro(@PathVariable("name") String name,
                                     @PathVariable("geraNovaAba") String geraNovaAba,
                                     @PathVariable(name = "NAME_FILTER", required = false) String NAMEFILTER,
                                     @PathVariable(name = "FILTER_CEMITERIO", required = false) String FILTER,
                                     HttpServletResponse response) throws IOException {
        service.addParams(NAMEFILTER, FILTER.isEmpty() ? null : FILTER);
        byte[] bytes = service.exportarPDF(name);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        if (geraNovaAba.equals("s")) {
            response.setHeader("Content-disposition", "inline; filename=relatorio-" + name + ".pdf");
        } else {
            response.setHeader("Content-disposition", "attachment; filename=relatorio-" + name + ".pdf");

        }
        response.getOutputStream().write(bytes);

    }

    @GetMapping("/rel/{name}/{geraNovaAba}/f")
    public void gerarRelatorioFiltro2(@PathVariable("name") String name,
                                      @PathVariable("geraNovaAba") String geraNovaAba,
                                      @RequestBody relatorioModel[] FILTER,
                                      HttpServletResponse response) throws IOException {


//        System.out.println(FILTER[1]);
//        service.addParams(FILTER[0].getNameFilter(),FILTER[0].getNameRelatorio().isEmpty()?null:FILTER[0].getNameRelatorio());
//        service.addParams(FILTER[1].getNameFilter(),FILTER[0].getNameRelatorio().isEmpty()?null:FILTER[1].getNameRelatorio());
        for (int i = 0; i < FILTER.length; i++) {
            System.out.println(FILTER[i]);
            service.addParams(FILTER[i].getNameFilter(), FILTER[i].getNameRelatorio().isEmpty() ? null : FILTER[i].getNameRelatorio());
        }

        byte[] bytes = service.exportarPDF(name);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        if (geraNovaAba.equals("s")) {
            response.setHeader("Content-disposition", "inline; filename=relatorio-" + name + ".pdf");
        } else {
            response.setHeader("Content-disposition", "attachment; filename=relatorio-" + name + ".pdf");

        }
        response.getOutputStream().write(bytes);

    }

    @PostMapping("/rel/{name}/{geraNovaAba}/f2")
    public void gerarRelatorioFiltro3(@PathVariable("name") String name,
                                      @PathVariable("geraNovaAba") String geraNovaAba,
                                      @RequestBody relatorioModel[] FILTER,
                                      HttpServletResponse response) throws IOException {


//        System.out.println(FILTER[1]);
//        service.addParams(FILTER[0].getNameFilter(),FILTER[0].getNameRelatorio().isEmpty()?null:FILTER[0].getNameRelatorio());
//        service.addParams(FILTER[1].getNameFilter(),FILTER[0].getNameRelatorio().isEmpty()?null:FILTER[1].getNameRelatorio());
        for (int i = 0; i < FILTER.length; i++) {
            System.out.println(FILTER[i]);
            service.addParams(FILTER[i].getNameFilter(), FILTER[i].getNameRelatorio().isEmpty() ? null : FILTER[i].getNameRelatorio());
        }

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