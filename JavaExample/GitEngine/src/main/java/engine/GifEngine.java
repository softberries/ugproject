package engine;

import java.io.IOException;

/**
 * Created by Maciek on 2015-11-13.
 */
public interface GifEngine {

    void zaladujPlik(String fileUrl);

    void wygenerujWynik() throws IOException;

    void pokazWyniki();

}
