package it.itsvolta.corsobackend2026.studentmanager.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;

import okhttp3.Request;

import okhttp3.Response;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;
import java.util.ArrayList;
import java.util.List;
import it.itsvolta. corsobackend2026.studentmanager.dto.BirreriaDTO;

@Service
public class BirreriaService {
    OkHttpClient client = new OkHttpClient();


String url = "https://api.openbrewerydb.org/v1/breweries";

Request request = new Request.Builder()
        .url(url)       
        .get()           
        .build();

        public List<BirreriaDTO> getBirrerie() throws IOException
         {

            try (Response response = client.newCall(request).execute()) 
            {

                if (response.isSuccessful()) {          // codice 2xx
                         String body = response.body().string();   // JSON ricevuto
                         List<BirreriaDTO> birrerie = new ArrayList<>();              // lista da restituire

                         JsonMapper mapper = new JsonMapper();
                         JsonNode root = mapper.readTree(body);

                         for (JsonNode item : root) {
                                    String name = item.get("name").asString();
                                    String country = item.get("country").asString();
                                    BirreriaDTO birreria = new BirreriaDTO(name, country);
                                    birrerie.add(birreria);
                                }
                            return birrerie;

                } else {                                // errori 4xx / 5xx
                         System.err.println("HTTP " + response.code());
                         return new ArrayList<>();
                          // opz. leggere response.body() per messaggi d’errore}

                    } 

        } 
}
}