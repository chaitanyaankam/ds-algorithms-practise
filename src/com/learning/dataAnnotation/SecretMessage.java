package com.learning.dataAnnotation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecretMessage {

    public static void printGrid(String url) throws Exception {
        // Step 1: Fetch the HTML content of the published Google Doc
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String html = response.body();

        // Step 2: Extract all table rows <tr>...</tr>
        // Each row (except header) has: x-coordinate | character | y-coordinate
        Map<String, String> grid = new HashMap<>();
        int maxX = 0;
        int maxY = 0;

        // Extract rows from the HTML table
        Pattern rowPattern = Pattern.compile("<tr[^>]*>(.*?)</tr>", Pattern.DOTALL);
        Pattern cellPattern = Pattern.compile("<td[^>]*>(.*?)</td>", Pattern.DOTALL);
        Pattern tagPattern  = Pattern.compile("<[^>]+>");

        Matcher rowMatcher = rowPattern.matcher(html);
        boolean firstRow = true;

        while (rowMatcher.find()) {
            String row = rowMatcher.group(1);

            // Skip header row
            if (firstRow) {
                firstRow = false;
                continue;
            }

            // Extract cells
            Matcher cellMatcher = cellPattern.matcher(row);
            String[] cells = new String[3];
            int cellIndex = 0;

            while (cellMatcher.find() && cellIndex < 3) {
                // Strip HTML tags to get plain text
                String cellContent = tagPattern.matcher(cellMatcher.group(1)).replaceAll("").trim();
                cells[cellIndex++] = cellContent;
            }

            if (cellIndex < 3) continue; // skip incomplete rows

            try {
                int x = Integer.parseInt(cells[0]);
                String character = cells[1];
                int y = Integer.parseInt(cells[2]);

                if (character.isEmpty()) character = " ";

                grid.put(x + "," + y, character);

                if (x > maxX) maxX = x;
                if (y > maxY) maxY = y;

            } catch (NumberFormatException e) {
                // Skip malformed rows
            }
        }

        // Step 3: Print the grid
        // In this doc format, y grows upward from the bottom-left origin.
        // Print maxY -> 0 so the rendered letters are correctly oriented.
        for (int y = maxY; y >= 0; y--) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x <= maxX; x++) {
                String key = x + "," + y;
                line.append(grid.getOrDefault(key, " "));
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) throws Exception {
        //String url = "https://docs.google.com/document/d/e/2PACX-1vTMOmshQe8YvaRXi6gEPKKlsC6UpFJSMAk4mQjLm_u1gmHdVVTaeh7nBNFBRlui0sTZ-snGwZM4DBCT/pub";
        String url = "https://docs.google.com/document/d/e/2PACX-1vSvM5gDlNvt7npYHhp_XfsJvuntUhq184By5xO_pA4b_gCWeXb6dM6ZxwN8rE6S4ghUsCj2VKR21oEP/pub";
        printGrid(url);
    }
}