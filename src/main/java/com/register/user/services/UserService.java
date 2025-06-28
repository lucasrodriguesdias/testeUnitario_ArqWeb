package com.register.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.user.models.User;
import com.register.user.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository usuarioRepository;

    public List<User> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public User save(User user) {
        return usuarioRepository.save(user);
    }

    public Optional<User> update(Long id, User user) {
        return usuarioRepository.findById(id).map(existingUsuario -> {
            existingUsuario.setNome(user.getNome());
            existingUsuario.setEmail(user.getEmail());
            return usuarioRepository.save(existingUsuario);
        });
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public int sum(int a, int b) {
        return a + b;
    }
}
