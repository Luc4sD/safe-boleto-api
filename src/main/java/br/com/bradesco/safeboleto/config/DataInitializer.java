package br.com.bradesco.safeboleto.config;

import br.com.bradesco.safeboleto.model.TrustedBank;
import br.com.bradesco.safeboleto.model.User;
import br.com.bradesco.safeboleto.repositories.TrustedBankRepository;
import br.com.bradesco.safeboleto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TrustedBankRepository trustedBankRepository;

    @Value("${app.admin.initial-password:}") // Pega da variável de ambiente, com um valor padrão vazio
    private String adminInitialPassword;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, TrustedBankRepository trustedBankRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.trustedBankRepository = trustedBankRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cria um usuário 'admin' se ele não existir e uma senha inicial foi fornecida
        if (adminInitialPassword != null && !adminInitialPassword.isBlank() && userRepository.findByUsername("admin").isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode(adminInitialPassword));
            userRepository.save(adminUser);
        }
        
        // Popula a tabela de bancos confiáveis, caso esteja vazia
        if (trustedBankRepository.count() == 0) {
            List<TrustedBank> banks = List.of(
                new TrustedBank("237", "Bradesco"),
                new TrustedBank("341", "Itaú Unibanco"),
                new TrustedBank("001", "Banco do Brasil"),
                new TrustedBank("104", "Caixa Econômica Federal"),
                new TrustedBank("033", "Santander")
            );
            trustedBankRepository.saveAll(banks);
        }
    }
}