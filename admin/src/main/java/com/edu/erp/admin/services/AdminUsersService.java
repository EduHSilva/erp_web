package com.edu.erp.admin.services;

import com.edu.erp.admin.dtos.AdminUsersRecordDTO;
import com.edu.erp.admin.enums.Status;
import com.edu.erp.admin.models.AdminUsers;
import com.edu.erp.admin.repositories.AdminUsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminUsersService {
    final AdminUsersRepository adminUsersRepository;

    public AdminUsersService(AdminUsersRepository adminUsersRepository) {
        this.adminUsersRepository = adminUsersRepository;
    }


    public AdminUsers save(AdminUsers userModel) {
        if (adminUsersRepository.findByEmail(userModel.getEmail()) == null) {
            BCryptPasswordEncoder cripto = new BCryptPasswordEncoder();

            userModel.setPassword(cripto.encode(userModel.getPassword()));
            userModel.setDateCreated(new Date());
            userModel = adminUsersRepository.save(userModel);
            checkSeller(userModel);
            return userModel;
        }
        return null;
    }


    public Page<AdminUsers> findAll(Pageable pageable) {
        return adminUsersRepository.findByDateDeletionIsNull(pageable);
    }

    public Optional<AdminUsers> findById(UUID id) {
        return adminUsersRepository.findById(id);
    }

    @Transactional
    public boolean delete(UUID id) {
        if (adminUsersRepository.existsById(id)) {
            Optional<AdminUsers> user = adminUsersRepository.findById(id);
            if (user.isPresent()) {
                user.get().setDateDeletion(new Date());
                adminUsersRepository.save(user.get());
                return true;
            }
        }
        return false;
    }

    @Transactional
    public AdminUsers update(UUID id, AdminUsersRecordDTO dto) {
        if (adminUsersRepository.existsById(id)) {
            Optional<AdminUsers> user = adminUsersRepository.findById(id);
            if (user.isPresent()) {
                AdminUsers adminUsers = user.get();
                BeanUtils.copyProperties(dto, adminUsers);
                checkSeller(adminUsers);
                adminUsersRepository.save(user.get());
                return adminUsers;
            }
        }
        return null;
    }

    private void checkSeller(AdminUsers user) {
        try {
            Seller seller = new Seller(user.getName(), user.getCpf(), user.getStatus(), user.getEmail(), user.getPhone(), "SELLER");
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

            URI urlString = new URI("http://localhost:8081/sales/persons/sellers/" + user.isSeller());
            URL url = urlString.toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);

            String json = ow.writeValueAsString(seller);
            osw.write(json);

            osw.flush();
            osw.close();
            os.close();
            con.connect();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                in.close();
                System.out.println("Dados de vendedores sincronizados.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao sincronizar dados de vendedores" + e.getMessage());
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Seller {

        private String name;

        private String cpf_cnpj;

        private Status status;

        private String email;

        private String phone;

        private String typePerson;
    }

}
