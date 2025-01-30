import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class APIClient {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/08a3a5a56c04afd299635b64/latest/USD"; // Substitua SUA_CHAVE pela sua chave da API

    public static Map<String, Double> getExchangeRates() {
        Map<String, Double> taxas = new HashMap<>();
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();


            String resposta = content.toString();
            System.out.println("Resposta da API: " + resposta);


            int startIndex = resposta.indexOf("\"conversion_rates\":{") + "\"conversion_rates\":{".length();
            int endIndex = resposta.indexOf("}", startIndex);
            String conversionRates = resposta.substring(startIndex, endIndex);


            String[] pares = conversionRates.split(",");
            for (String par : pares) {
                String[] partes = par.split(":");
                if (partes.length == 2) {
                    String moeda = partes[0].replace("\"", "").trim();
                    double taxa = Double.parseDouble(partes[1].trim());
                    taxas.put(moeda, taxa);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taxas;
    }
}

// Essa classe e responasvel por obter os dados da API, e a segunda parte, transforma em um documento de texto
// Separando o nome da moeda da sua taxa atual, como a respota da API retorna nao apenas as moedas e os valores,
// Foi necessario cuidar do resultado da API.
