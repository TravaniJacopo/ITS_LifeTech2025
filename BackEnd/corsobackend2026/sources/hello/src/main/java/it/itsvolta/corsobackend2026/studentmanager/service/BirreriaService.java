package it.itsvolta.corsobackend2026.studentmanager.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.itsvolta.corsobackend2026.studentmanager.dto.BirreriaDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

@Service
public class BirreriaService {

    private final OkHttpClient client = new OkHttpClient();

    public List<BirreriaDTO> getBirrerie(Integer page) throws IOException {

        if (page == null || page < 1) {
            page = 1;
        }

        String url = "https://api.openbrewerydb.org/v1/breweries?page=" + page;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful()) {

                String body = response.body().string();

                List<BirreriaDTO> birrerie = new ArrayList<>();

                JsonMapper mapper = new JsonMapper();
                JsonNode root = mapper.readTree(body);

                for (JsonNode item : root) {

                    String name = item.get("name").asString();
                    String country = item.get("country").asString();

                    BirreriaDTO birreria =
                            new BirreriaDTO(name, country);

                    birrerie.add(birreria);
                }

                return birrerie;

            } else {

                
                System.err.println("HTTP " + response.code());
                return new ArrayList<>();
            }
        }
    }
}