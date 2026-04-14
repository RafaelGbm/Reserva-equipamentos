package com.fiap.reserva_equipamentos.api.service;

import com.fiap.reserva_equipamentos.api.domain.Equipamento;
import com.fiap.reserva_equipamentos.api.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipamentoService {
    private final EquipamentoRepository repo;
    public EquipamentoService(EquipamentoRepository repo) { this.repo = repo; }

    public List<Equipamento> listarAtivos() { return repo.findByAtivoTrue(); }
    public List<Equipamento> buscar(String termo) { return repo.findByDescricaoContainingIgnoreCase(termo); }
}