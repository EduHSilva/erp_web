package com.edu.erp.admin.seeds;

import com.edu.erp.admin.enums.Language;
import com.edu.erp.admin.enums.Status;
import com.edu.erp.admin.models.AdminModules;
import com.edu.erp.admin.models.AdminProfileAccess;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.repositories.AdminModulesRepository;
import com.edu.erp.admin.repositories.AdminProfileAccessRepository;
import com.edu.erp.admin.repositories.AdminUsersRepository;

import com.edu.erp.admin.services.AdminUsersService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class Seeds implements ApplicationRunner {

    private final AdminUsersRepository usersRepository;
    private final AdminModulesRepository modulesRepository;
    private final AdminProfileAccessRepository profileRepository;

    private final AdminUsersService service;

    public Seeds(AdminUsersRepository usersRepository, AdminModulesRepository modulesRepository, AdminProfileAccessRepository profileRepository, AdminUsersService service) {
        this.usersRepository = usersRepository;
        this.modulesRepository = modulesRepository;
        this.profileRepository = profileRepository;
        this.service = service;
    }

    @Override
    public void run(ApplicationArguments args) {
        seedUser();
    }

    private List<AdminModules> seedModules() {
        List<AdminModules> adminModules = new ArrayList<>();
        if (modulesRepository.count() == 0) {
            adminModules.add(modulesRepository.save(new AdminModules(UUID.randomUUID(), "Vendas", new Date(), null)));
            adminModules.add(modulesRepository.save(new AdminModules(UUID.randomUUID(), "Compras", new Date(), null)));
            adminModules.add(modulesRepository.save(new AdminModules(UUID.randomUUID(), "Admin", new Date(), null)));
            System.out.println("Dados de modulos carregados com sucesso.");
        } else {
            System.out.println("Dados de modulos já existem. Nenhum dado adicionado.");
        }
        return adminModules;
    }

    private AdminProfileAccess seedProfile() {
        List<AdminModules> modulesList = seedModules();
        if (profileRepository.count() == 0) {
            System.out.println("Dados de profile carregados com sucesso.");
            return profileRepository.save(new AdminProfileAccess(UUID.randomUUID(), "Admin", new Date(), null, modulesList));
        } else {
            System.out.println("Dados de profile já existem. Nenhum dado adicionado.");
        }
        return null;
    }


    private void seedUser() {
        AdminProfileAccess profileAccess = seedProfile();
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
            adminUser.setSeller(false);
            adminUser.setProfile(profileAccess);
            service.save(adminUser);
            System.out.println("Dados de users carregados com sucesso.");
        } else {
            System.out.println("Dados de users já existem. Nenhum dado adicionado.");
        }
    }

}
