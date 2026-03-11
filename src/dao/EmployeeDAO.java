/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.opencsv.*;
import java.io.*;
import java.util.*;

/**
 * EmployeeDAO — Data Access Object for employee records.
 * ONLY responsibility: read and write employee.csv.
 * No business logic here — that belongs in EmployeeService.
 */
public class EmployeeDAO {

    private final String folderPath = "data";
    private final String EMPLOYEE_FILE = folderPath + "/employee.csv";

    private final List<String> headers = new ArrayList<>();
    private final List<String[]> data = new ArrayList<>();

    // ── Read ────────────────────────────────────────────────────────────────
    public void load() {
        headers.clear();
        data.clear();

        File file = new File(EMPLOYEE_FILE);
        if (!file.exists()) {
            System.out.println("❌ employee.csv not found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] fields = line.split("(?=(?:[^\"]*\"[^\"]*\")*[^\"]*)\\;");
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].replaceAll("^\"|\"$", "").trim();
                }
                if (isFirstLine) {
                    headers.addAll(Arrays.asList(fields));
                    isFirstLine = false;
                } else {
                    data.add(adjustRowLength(fields, headers.size()));
                }
            }
            System.out.println("✅ employee.csv loaded: " + data.size() + " rows");
        } catch (IOException e) {
            System.out.println("❌ Error reading employee.csv: " + e.getMessage());
        }
    }

    // ── Write ───────────────────────────────────────────────────────────────
    public void save() {
        try (ICSVWriter writer = new CSVWriterBuilder(new FileWriter(EMPLOYEE_FILE))
                .withSeparator(';')
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .build()) {
            writer.writeNext(headers.toArray(String[]::new));
            for (String[] row : data) writer.writeNext(row);
            System.out.println("✅ employee.csv saved.");
        } catch (IOException e) {
            System.out.println("❌ Error writing employee.csv: " + e.getMessage());
        }
    }

    // ── Append ──────────────────────────────────────────────────────────────
    public boolean append(String[] row) {
        try (ICSVWriter writer = new CSVWriterBuilder(new FileWriter(EMPLOYEE_FILE, true))
                .withSeparator(';')
                .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .build()) {
            writer.writeNext(row);
            data.add(row);
            return true;
        } catch (IOException e) {
            System.out.println("❌ Error appending employee: " + e.getMessage());
            return false;
        }
    }

    // ── Update ──────────────────────────────────────────────────────────────
    public boolean updateField(String employeeId, String columnName, String newValue) {
        int idIndex = headers.indexOf("Employee #");
        int colIndex = headers.indexOf(columnName);
        if (idIndex == -1 || colIndex == -1) return false;

        for (String[] row : data) {
            if (row[idIndex].equals(employeeId)) {
                row[colIndex] = newValue;
                save();
                return true;
            }
        }
        return false;
    }

    // ── Delete ──────────────────────────────────────────────────────────────
    public boolean deleteById(String employeeId) {
        int idIndex = headers.indexOf("Employee #");
        boolean removed = data.removeIf(row -> row[idIndex].equals(employeeId));
        if (removed) save();
        return removed;
    }

    // ── Queries ─────────────────────────────────────────────────────────────
    public String[] findById(String employeeId) {
        int idIndex = headers.indexOf("Employee #");
        for (String[] row : data) {
            if (row[idIndex].equals(employeeId)) return row;
        }
        return null;
    }

    public List<String[]> findAll() { return data; }
    public List<String> getHeaders() { return headers; }

    // ── Utility ─────────────────────────────────────────────────────────────
    private String[] adjustRowLength(String[] fields, int expectedSize) {
        if (fields.length < expectedSize) {
            String[] padded = Arrays.copyOf(fields, expectedSize);
            Arrays.fill(padded, fields.length, expectedSize, "");
            return padded;
        }
        return fields.length > expectedSize ? Arrays.copyOf(fields, expectedSize) : fields;
    }
}
