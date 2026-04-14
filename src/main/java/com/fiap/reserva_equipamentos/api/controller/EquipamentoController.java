package com.fiap.reserva_equipamentos.api.controller;

import com.fiap.reserva_equipamentos.api.domain.Equipamento;
import com.fiap.reserva_equipamentos.api.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Equipamento> criar(@Valid @RequestBody Equipamento equipamento) {
        Equipamento criado = service.criar(equipamento);
        return ResponseEntity.created(URI.create("/api/equipamentos/" + criado.getId())).body(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @Valid @RequestBody Equipamento equipamento) {
        return service.atualizar(id, equipamento).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean ok = service.excluir(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

