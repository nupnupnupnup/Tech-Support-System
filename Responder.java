import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;

public class Responder
{
    private HashMap<String, String> responseMap;
    private ArrayList<String> defaultResponses;
    private Random randomGenerator;
    private String lastResponse = "";  

    public Responder()
    {
        responseMap = new HashMap<>();
        defaultResponses = new ArrayList<>();
        fillResponseMap();
        fillDefaultResponses();
        randomGenerator = new Random();
    }

    public String generateResponse(HashSet<String> words)
{
    if (words.isEmpty()) {
        return "Maaf, saya tidak dapat memahami apa yang Anda masukkan.";
    }

    try {
        Thread.sleep(1000); 
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    String bestMatch = null;
    for (String word : words) {
        String match = findBestMatch(word);
        if (match != null) {

            if (bestMatch == null || responseMap.containsKey(word)) {
                bestMatch = match;
            }
        }
    }
    
    if (bestMatch != null) {
        String response = responseMap.get(bestMatch);


        if (!response.equals(lastResponse)) {
            lastResponse = response;  
            return response;
        }
    }
    
    String defaultResponse = pickDefaultResponse();
    if (!defaultResponse.equals(lastResponse)) { 
        lastResponse = defaultResponse;
        return defaultResponse;
    }

    return "Silakan berikan pertanyaan yang lebih jelas.";
}

    private String findBestMatch(String inputWord) {
        for (String key : responseMap.keySet()) {
            if (key.contains(inputWord)) {
                return key;
            }
        }
        return null;
    }

    private void fillResponseMap()
    {
        responseMap.put("crash", "Anda dapat merestart perangkat anda");
        responseMap.put("slow", "Tolong cek koneksi anda ke internet");
        responseMap.put("performance", "Tolong cek apakah perangkat anda kompatibel ");
        responseMap.put("bug", "Klik support untuk mendapatkan bantuan lebih lanjut");
        responseMap.put("installation", "Untuk instalasi ikuti langkah-langkah yang telah diberikan");
        responseMap.put("memory", "Clear memory anda untuk mendapatkan ruang lebih");
    }

    private void fillDefaultResponses()
    {
        defaultResponses.add("Itu terdengar aneh. Bisakah Anda menjelaskan masalah itu dengan lebih detail?");
        defaultResponses.add("Tidak ada pelanggan lain yang pernah mengeluh tentang ini sebelumnya...");
        defaultResponses.add("Itu terdengar menarik. Ceritakan lebih lanjut...");
        defaultResponses.add("Saya butuh sedikit lebih banyak informasi tentang itu.");
        defaultResponses.add("Sudahkah Anda memeriksa bahwa Anda tidak memiliki konflik dll?");
        defaultResponses.add("Itu dijelaskan di manual. Apakah Anda sudah membaca manual?");
        defaultResponses.add("Deskripsi Anda agak samar. Apakah ada ahli di sana...");
        defaultResponses.add("Itu bukan bug, itu fitur!");
        defaultResponses.add("Bisakah Anda menjelaskan lebih lanjut?");
    }

    private String pickDefaultResponse()
    {
        int index = randomGenerator.nextInt(defaultResponses.size());
        return defaultResponses.get(index);
    }
}
