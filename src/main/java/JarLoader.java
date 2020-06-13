import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

/**
 * LoadJar
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 13/06/2020 - 04:35 pm
 */
public class JarLoader {

    private URLClassLoader classLoader;

    public JarLoader(URLClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    private void loadJar(URL url) {
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            addURL.invoke(classLoader, url);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void loadJar(JarLoader jarLoader, String path) throws Exception {
        File libraryDirectory = new File(path);
        if (libraryDirectory.isDirectory()) {
            for (File file : Objects.requireNonNull(libraryDirectory.listFiles(file -> file.exists() && file.isFile() && file.getName().endsWith(".jar")))) {
                jarLoader.loadJar(file.toURI().toURL());
            }
        } else {
            System.exit(0);
        }
    }


}
