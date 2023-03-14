package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class util {
    /**
     * create string for personID
     * @return String with personID
     */
    public String createID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    /**
		The readString method shows how to read a String from an InputStream.
	*/
    public String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
