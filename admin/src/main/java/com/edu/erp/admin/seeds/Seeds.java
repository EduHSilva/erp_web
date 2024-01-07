package com.edu.erp.admin.seeds;

import com.edu.erp.admin.enums.Language;
import com.edu.erp.admin.enums.Status;
import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.repositories.AdminModulesRepository;
import com.edu.erp.admin.repositories.AdminUsersRepository;

import com.edu.erp.admin.services.AdminUsersService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class Seeds implements ApplicationRunner {

    private final AdminUsersRepository usersRepository;
    private final AdminModulesRepository modulesRepository;

    private final AdminUsersService service;

    public Seeds(AdminUsersRepository usersRepository, AdminModulesRepository modulesRepository, AdminUsersService service) {
        this.usersRepository = usersRepository;
        this.modulesRepository = modulesRepository;
        this.service = service;
    }

    @Override
    public void run(ApplicationArguments args) {
        seedUser();
        seedModules();
    }

    private void seedModules() {
        if (modulesRepository.count() == 0) {
            modulesRepository.save(new AdminModules(UUID.randomUUID(), "Vendas", new Date(), null));
            modulesRepository.save(new AdminModules(UUID.randomUUID(), "Compras", new Date(), null));
            modulesRepository.save(new AdminModules(UUID.randomUUID(), "Admin", new Date(), null));
            System.out.println("Dados de modulos carregados com sucesso.");
        } else {
            System.out.println("Dados de modulos já existem. Nenhum dado adicionado.");
        }
    }

    private void seedUser() {
        if (usersRepository.count() == 0) {
            AdminUsers adminUser = new AdminUsers();
            adminUser.setName("Admin");
            adminUser.setPassword("12345678");
            adminUser.setEmail("admin@gmail.com");
            adminUser.setCpf("44647862860");
            adminUser.setDateCreated(new Date());
            adminUser.setAdmin(true);
            adminUser.setLanguage(Language.PT);
            adminUser.setStatus(Status.ACTIVE);
            service.save(adminUser);
            System.out.println("Dados de users carregados com sucesso.");
        } else {
            System.out.println("Dados de users já existem. Nenhum dado adicionado.");
        }
    }

}
