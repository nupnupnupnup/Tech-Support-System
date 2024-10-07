import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * The responder class represents a response generator object.
 * It is used to generate an automatic response, based on specified input.
 * Input is presented to the responder as a set of words, and based on those
 * words the responder will generate a String that represents the response.
 *
 * Internally, the reponder uses a HashMap to associate words with response
 * strings and a list of default responses. If any of the input words is found
 * in the HashMap, the corresponding response is returned. If none of the input
 * words is recognized, one of the default responses is randomly chosen.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    1.0 (2011.07.31)
 */
public class Responder
{
    // Used to map key words to responses.
    private HashMap<String, String> responseMap;
    // Default responses to use if we don't recognise a word.
    private ArrayList<String> defaultResponses;
    private Random randomGenerator;

    /**
     * Construct a Responder
     */
    public Responder()
    {
        responseMap = new HashMap<String, String>();
        defaultResponses = new ArrayList<String>();
        fillResponseMap();
        fillDefaultResponses();
        randomGenerator = new Random();
    }

    /**
     * Generate a response from a given set of input words.
     * 
     * @param words  A set of words entered by the user
     * @return       A string that should be displayed as the response
     */
    public String generateResponse(HashSet<String> words)
    {
        for (String word : words) {
            String response = responseMap.get(word);
            if(response != null) {
                return response;
            }
        }
        
        // If we get here, none of the words from the input line was recognized.
        // In this case we pick one of our default responses (what we say when
        // we cannot think of anything else to say...)
        return pickDefaultResponse();
    }

    /**
     * Enter all the known keywords and their associated responses
     * into our response map.
     */
private void fillResponseMap()
{
    responseMap.put("crash", 
                    "Yah, ini tidak pernah crash di sistem kami. Mungkin ada sesuatu\n" +
                    "yang berhubungan dengan sistem Anda. Ceritakan lebih lanjut tentang konfigurasi Anda.");
    responseMap.put("crashes", 
                    "Yah, ini tidak pernah crash di sistem kami. Mungkin ada sesuatu\n" +
                    "yang berhubungan dengan sistem Anda. Ceritakan lebih lanjut tentang konfigurasi Anda.");
    responseMap.put("slow", 
                    "Saya rasa ini berkaitan dengan perangkat keras Anda. Meningkatkan prosesor Anda\n" +
                    "harusnya menyelesaikan semua masalah kinerja. Apakah ada masalah dengan\n" +
                    "perangkat lunak kami?");
    responseMap.put("performance", 
                    "Kinerja cukup memadai di semua pengujian kami. Apakah Anda menjalankan\n" +
                    "proses lain di latar belakang?");
    responseMap.put("bug", 
                    "Anda tahu, semua perangkat lunak memiliki beberapa bug. Tapi para insinyur perangkat lunak\n" +
                    "kami bekerja sangat keras untuk memperbaikinya. Bisakah Anda menjelaskan masalahnya lebih lanjut?");
    responseMap.put("buggy", 
                    "Anda tahu, semua perangkat lunak memiliki beberapa bug. Tapi para insinyur perangkat lunak\n" +
                    "kami bekerja sangat keras untuk memperbaikinya. Bisakah Anda menjelaskan masalahnya lebih lanjut?");
    responseMap.put("windows", 
                    "Ini adalah bug yang diketahui berkaitan dengan sistem operasi Windows. Silakan\n" +
                    "laporkan ke Microsoft. Tidak ada yang bisa kami lakukan terkait hal ini.");
    responseMap.put("mac", 
                    "Ini adalah bug yang diketahui berkaitan dengan sistem operasi Mac. Silakan\n" +
                    "laporkan ke Apple. Tidak ada yang bisa kami lakukan terkait hal ini.");
    responseMap.put("expensive", 
                    "Biaya produk kami cukup kompetitif. Apakah Anda sudah membandingkan fitur-fitur kami?");
    responseMap.put("installation", 
                    "Proses instalasi sebenarnya cukup sederhana. Kami memiliki banyak wizard\n" +
                    "yang akan melakukan semua pekerjaan untuk Anda. Apakah Anda sudah membaca\n" +
                    "petunjuk instalasi?");
    responseMap.put("memory", 
                    "Jika Anda membaca persyaratan sistem dengan teliti, Anda akan melihat bahwa\n" +
                    "persyaratan memori yang disebutkan adalah 1,5 gigabyte. Anda benar-benar perlu\n" +
                    "meningkatkan memori Anda. Ada hal lain yang ingin Anda ketahui?");
    responseMap.put("linux", 
                    "Kami sangat serius dalam mendukung Linux. Tapi ada beberapa masalah.\n" +
                    "Sebagian besar terkait dengan versi glibc yang tidak kompatibel. Bisakah Anda\n" +
                    "menjelaskannya dengan lebih detail?");
    responseMap.put("bluej", 
                    "Ahhh, BlueJ, ya. Kami sudah mencoba membeli perusahaan mereka sejak lama, tetapi\n" +
                    "mereka tidak mau menjualnya... Orang-orangnya cukup keras kepala. Tidak ada yang bisa\n" +
                    "kami lakukan terkait hal itu.");
}


    /**
     * Build up a list of default responses from which we can pick one
     * if we don't know what else to say.
     */
private void fillDefaultResponses()
{
    defaultResponses.add("Itu terdengar aneh. Bisakah Anda menjelaskan masalah itu dengan lebih detail?");
    defaultResponses.add("Tidak ada pelanggan lain yang pernah mengeluh tentang ini sebelumnya. \n" +
                         "Bagaimana konfigurasi sistem Anda?");
    defaultResponses.add("Itu terdengar menarik. Ceritakan lebih lanjut...");
    defaultResponses.add("Saya butuh sedikit lebih banyak informasi tentang itu.");
    defaultResponses.add("Sudahkah Anda memeriksa bahwa Anda tidak memiliki konflik dll?");
    defaultResponses.add("Itu dijelaskan di manual. Apakah Anda sudah membaca manual?");
    defaultResponses.add("Deskripsi Anda agak samar. Apakah ada ahli di sana \n" +
                         "yang dapat menjelaskan ini dengan lebih rinci?");
    defaultResponses.add("Itu bukan bug, itu fitur!");
    defaultResponses.add("Bisakah Anda menjelaskan lebih lanjut?");
}

    /**
     * Randomly select and return one of the default responses.
     * @return     A random default response
     */
    private String pickDefaultResponse()
    {
        // Pick a random number for the index in the default response list.
        // The number will be between 0 (inclusive) and the size of the list (exclusive).
        int index = randomGenerator.nextInt(defaultResponses.size());
        return defaultResponses.get(index);
    }
}