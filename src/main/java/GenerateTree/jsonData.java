package GenerateTree;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class jsonData {

    class Location {
        String latitude;
        String longitude;
        String city;
        String country;

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    class LocationData {
        Location[] data;

        public Location[] getData() {
            return data;
        }

        public void setData(Location[] data) {
            this.data = data;
        }
    }

    class NameData {
        String[] data;

        public String[] getData() {
            return data;
        }

        public void setData(String[] data) {
            this.data = data;
        }
    }

    static LocationData locData = null;

    static NameData fnameData = null;
    static NameData mnameData = null;
    static NameData snameData = null;

    static {
        try {
            locData = getLocationData();
            fnameData = getNameData("json/fnames.json");
            mnameData = getNameData("json/mnames.json");
            snameData = getNameData("json/snames.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static LocationData getLocationData() throws FileNotFoundException {
        Gson gson = new Gson();

        //transfer
        Reader reader = new FileReader("json/locations.json");
        jsonData.LocationData locData = (jsonData.LocationData)gson.fromJson(reader, jsonData.LocationData.class);

        return locData;
    }

    static NameData getNameData(String fileLoc) throws FileNotFoundException {
        Gson gson = new Gson();

        //transfer
        Reader reader = new FileReader(fileLoc);
        jsonData.NameData nameData = (jsonData.NameData) gson.fromJson(reader, jsonData.NameData.class);

        return nameData;
    }



}
