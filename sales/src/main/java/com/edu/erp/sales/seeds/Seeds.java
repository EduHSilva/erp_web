package com.edu.erp.sales.seeds;

import com.edu.erp.sales.models.SalesStates;
import com.edu.erp.sales.repositories.SalesStatesRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeds implements ApplicationRunner {

    private final SalesStatesRepository salesStatesRepository;

    public Seeds(SalesStatesRepository salesStatesRepository) {
        this.salesStatesRepository = salesStatesRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        seedSalesStates();
    }

    private void seedSalesStates() {
        if (salesStatesRepository.count() == 0) {

            salesStatesRepository.save(new SalesStates("Acre", "AC"));
            salesStatesRepository.save(new SalesStates("Alagoas", "AL"));
            salesStatesRepository.save(new SalesStates("Amapá", "AP"));
            salesStatesRepository.save(new SalesStates("Amazonas", "AM"));
            salesStatesRepository.save(new SalesStates("Bahia", "BA"));
            salesStatesRepository.save(new SalesStates("Ceará", "CE"));
            salesStatesRepository.save(new SalesStates("Distrito Federal", "DF"));
            salesStatesRepository.save(new SalesStates("Espírito Santo", "ES"));
            salesStatesRepository.save(new SalesStates("Goiás", "GO"));
            salesStatesRepository.save(new SalesStates("Maranhão", "MA"));
            salesStatesRepository.save(new SalesStates("Mato Grosso", "MT"));
            salesStatesRepository.save(new SalesStates("Mato Grosso do Sul", "MS"));
            salesStatesRepository.save(new SalesStates("Minas Gerais", "MG"));
            salesStatesRepository.save(new SalesStates("Pará", "PA"));
            salesStatesRepository.save(new SalesStates("Paraíba", "PB"));
            salesStatesRepository.save(new SalesStates("Paraná", "PR"));
            salesStatesRepository.save(new SalesStates("Pernambuco", "PE"));
            salesStatesRepository.save(new SalesStates("Piauí", "PI"));
            salesStatesRepository.save(new SalesStates("Rio de Janeiro", "RJ"));
            salesStatesRepository.save(new SalesStates("Rio Grande do Norte", "RN"));
            salesStatesRepository.save(new SalesStates("Rio Grande do Sul", "RS"));
            salesStatesRepository.save(new SalesStates("Rondônia", "RO"));
            salesStatesRepository.save(new SalesStates("Roraima", "RR"));
            salesStatesRepository.save(new SalesStates("Santa Catarina", "SC"));
            salesStatesRepository.save(new SalesStates("São Paulo", "SP"));
            salesStatesRepository.save(new SalesStates("Sergipe", "SE"));
            salesStatesRepository.save(new SalesStates("Tocantins", "TO"));

            System.out.println("Dados de SalesStates carregados com sucesso.");
        } else {
            System.out.println("Dados de SalesStates já existem. Nenhum dado adicionado.");
        }
    }

}
