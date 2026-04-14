package com.fiap.reserva_equipamentos.api.service;

import com.fiap.reserva_equipamentos.api.domain.Equipamento;
import com.fiap.reserva_equipamentos.api.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {
    private final EquipamentoRepository repo;
    public EquipamentoService(EquipamentoRepository repo) { this.repo = repo; }

    public List<Equipamento> listarAtivos() { return repo.findByAtivoTrue(); }
    public List<Equipamento> buscar(String termo) { return repo.findByDescricaoContainingIgnoreCase(termo); }

    public Equipamento criar(Equipamento equipamento) {
        if (equipamento.getAtivo() == null) equipamento.setAtivo(true);
        return repo.save(equipamento);
    }

    public Optional<Equipamento> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Optional<Equipamento> atualizar(Long id, Equipamento dados) {
        return repo.findById(id).map(existing -> {
            existing.setDescricao(dados.getDescricao());
            existing.setAtivo(dados.getAtivo() != null ? dados.getAtivo() : existing.getAtivo());
            return repo.save(existing);
        });
    }

    /**
     * Exclusao logica (soft delete): mantem o registro e marca como inativo.
     */
    public boolean excluir(Long id) {
        return repo.findById(id).map(existing -> {
            existing.setAtivo(false);
            repo.save(existing);
            return true;
        }).orElse(false);
    }
}
