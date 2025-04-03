package com.exercicio.fatec.crud.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercicio.fatec.crud.domain.usuario.UsuarioService;
import com.exercicio.fatec.crud.entities.Usuario;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService UsuarioService;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class.getName());

    @PostMapping("/criarUsuario")
    public ResponseEntity<Usuario> criarUsuarios(@RequestBody Usuario usuario) {
        Usuario novoUsuario = UsuarioService.criarUsuario(usuario);
        logger.info("Usuario criado: Nome={}, Idade={}", novoUsuario.getNome(), novoUsuario.getIdade());
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<Usuario>> listarUsuario() {
        return new ResponseEntity<>(UsuarioService.listarUsuarios(), HttpStatus.OK);
    }

    @PutMapping("/atualizarUsuario/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario UsuarioAtualizado) {
        boolean atualizado = UsuarioService.atualizarUsuario(id, UsuarioAtualizado);
        if (atualizado) {
            return ResponseEntity.ok("Usuario atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com ID " + id + " não encontrado.");
        }

    }

    @DeleteMapping("/deletarUsuario/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        boolean removido = UsuarioService.deletarUsuario(id);
        if (removido) {
            return ResponseEntity.ok("Usuario removido com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe usuario com id: " + id);
        }
    }

    @GetMapping("/buscarUsuario/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = UsuarioService.buscarUsuarioPorId(id);
        return usuario.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com ID " + id + " não encontrado."));



    }

}