package com.fiap.reserva_equipamentos.api.controller;

import com.fiap.reserva_equipamentos.api.domain.Equipamento;
import com.fiap.reserva_equipamentos.api.service.EquipamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {
    private final EquipamentoService service;
    public EquipamentoController(EquipamentoService service) { this.service = service; }

    @GetMapping("/ativos")
    public List<Equipamento> ativos() { return service.listarAtivos(); }

    @GetMapping
    public List<Equipamento> buscar(@RequestParam(required = false, defaultValue = "") String q) {
        if (q.isBlank()) return service.listarAtivos();
        return service.buscar(q);
    }
}