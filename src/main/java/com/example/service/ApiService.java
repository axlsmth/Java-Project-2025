package com.example.service;

import com.example.model.DroneDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ApiService {
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private static final String BASE_URL = "http://dronesim.facets-labs.com/api";
    private static final String AUTH_TOKEN = "b21d6e711b46b37a0af4266dd75f70dc645bb140";

    public ApiService() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<DroneDTO> getDrones() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "/drones/")
                .addHeader("Authorization", "Token " + AUTH_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            
            JsonNode root = objectMapper.readTree(response.body().string());
            JsonNode results = root.get("results");
            
            List<DroneDTO> drones = new ArrayList<>();
            for (JsonNode droneNode : results) {
                DroneDTO drone = objectMapper.treeToValue(droneNode, DroneDTO.class);
                drones.add(drone);
            }
            return drones;
        }
    }
}
