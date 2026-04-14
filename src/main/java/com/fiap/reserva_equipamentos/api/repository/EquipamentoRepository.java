package com.fiap.reserva_equipamentos.api.repository;

import com.fiap.reserva_equipamentos.api.domain.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    List<Equipamento> findByAtivoTrue();
    List<Equipamento> findByDescricaoContainingIgnoreCase(String termo);
}