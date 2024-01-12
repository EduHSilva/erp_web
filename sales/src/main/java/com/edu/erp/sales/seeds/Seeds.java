package com.edu.erp.sales.seeds;

import com.edu.erp.sales.models.SalesCities;
import com.edu.erp.sales.models.SalesStates;
import com.edu.erp.sales.repositories.SalesCitiesRepository;
import com.edu.erp.sales.repositories.SalesStatesRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Component
public class Seeds implements ApplicationRunner {

    private final SalesStatesRepository salesStatesRepository;
    private final SalesCitiesRepository salesCitiesRepository;

    public Seeds(SalesStatesRepository salesStatesRepository, SalesCitiesRepository salesCitiesRepository) {
        this.salesStatesRepository = salesStatesRepository;
        this.salesCitiesRepository = salesCitiesRepository;
    }

    public void seedUFS() {
        if (salesStatesRepository.count() == 0) {
            try {
                URI urlString = new URI("https://servicodados.ibge.gov.br/api/v1/localidades/estados");
                URL url = urlString.toURL();
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        JSONArray jsonArray = new JSONArray(inputLine);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String sigla = jsonObject.getString("sigla");
                            String nome = jsonObject.getString("nome");
                            SalesStates state = new SalesStates(nome, sigla);
                            salesStatesRepository.save(state);
                        }
                    }

                    in.close();
                    System.out.println("Dados de estados carregados com sucesso.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao carregar dados de estados");
            }
        }
    }

    public void seedCities() {
        if (salesCitiesRepository.count() == 0) {
            try {
                List<SalesStates> states = salesStatesRepository.findAll();
                for (SalesStates state : states) {
                    URI urlString = new URI(STR."https://servicodados.ibge.gov.br/api/v1/localidades/estados/\{state.getAcronym()}/municipios");
                    URL url = urlString.toURL();
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine;

                        while ((inputLine = in.readLine()) != null) {
                            JSONArray jsonArray = new JSONArray(inputLine);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                SalesCities city = new SalesCities();
                                city.setName(jsonObject.getString("nome"));
                                city.setState(state);
                                city.setDateCreation(new Date());
                                salesCitiesRepository.save(city);
                            }
                        }

                        in.close();
                        System.out.println(STR."Dados de cidades do estado \{state.getAcronym()} carregados com sucesso.");
                    }
                }
            } catch (Exception e) {
                System.out.println(STR."Erro ao carregar dados de cidades.\{e.getMessage()}");
            }
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        seedUFS();
        seedCities();
    }
}
