package com.exercicio.fatec.crud.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercicio.fatec.crud.entities.Usuario;

public interface UsuarioRepository 
extends JpaRepository<Cliente, Long>{
    
}
