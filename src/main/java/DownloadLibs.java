import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * DownloadLibs
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 13/06/2020 - 04:31 pm
 */
public class DownloadLibs {

    private static class Library {

        private String name;
        private String repo;
    }

    private static class Libraries {

        private String version;
        private List<Library> libraries = new ArrayList();

    }

    public static void runDownloader() {
        Gson gson = new Gson();

        Libraries libraries = gson.fromJson(new BufferedReader(new InputStreamReader(DownloadLibs.class.getResourceAsStream("/libs.json"))), Libraries.class);

        libraries.libraries.forEach(e -> System.out.println(e.name + ":" + e.repo));
    }

    public static void main(String[] args) {
        runDownloader();
    }


}
