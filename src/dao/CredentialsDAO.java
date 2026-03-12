/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import java.io.*;
import java.util.*;

/**
 * CredentialsDAO — Handles access to login credentials.
 * The Service layer calls this to verify administrative roles.
 */
/**
 *
 * @author SunnyEljohn
 */
public class CredentialsDAO {
    private final String filePath = "credentials.csv";

    public Map<String, String[]> loadCredentials() {
        Map<String, String[]> credentialsMap = new HashMap<>();
        
        File file = new File(filePath);
        if (!file.exists()) return credentialsMap;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length >= 2) {
                    // Key: Username, Value: [Password, Role]
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    String role = (parts.length >= 3) ? parts[2].trim().toUpperCase() : "ADMIN";
                    
                    credentialsMap.put(username, new String[]{password, role});
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error reading credentials: " + e.getMessage());
        }
        return credentialsMap;
    }
}
