package com.lxy.pink.data.model.weather;

import java.util.List;

/**
 * Created by yuan on 2016/10/9.
 */

public class Forecast {

    /**
     * id : 1279945
     * name : Suzhou
     * coord : {"lon":98.517357,"lat":39.743179}
     * country : CN
     * population : 0
     * sys : {"population":0}
     */

    private CityBean city;
    /**
     * city : {"id":1279945,"name":"Suzhou","coord":{"lon":98.517357,"lat":39.743179},"country":"CN","population":0,"sys":{"population":0}}
     * cod : 200
     * message : 0.006
     * cnt : 35
     * list : [{"dt":1476025200,"main":{"temp":271.24,"temp_min":271.236,"temp_max":271.24,"pressure":792.45,"sea_level":1033.59,"grnd_level":792.45,"humidity":67,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.52,"deg":200.503},"sys":{"pod":"n"},"dt_txt":"2016-10-09 15:00:00"},{"dt":1476036000,"main":{"temp":270.66,"temp_min":270.655,"temp_max":270.66,"pressure":791.64,"sea_level":1034.16,"grnd_level":791.64,"humidity":65,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.69,"deg":193.005},"sys":{"pod":"n"},"dt_txt":"2016-10-09 18:00:00"},{"dt":1476046800,"main":{"temp":270.22,"temp_min":270.219,"temp_max":270.22,"pressure":790.51,"sea_level":1033.97,"grnd_level":790.51,"humidity":57,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.65,"deg":190.502},"sys":{"pod":"n"},"dt_txt":"2016-10-09 21:00:00"},{"dt":1476057600,"main":{"temp":270.14,"temp_min":270.137,"temp_max":270.14,"pressure":790.77,"sea_level":1035.11,"grnd_level":790.77,"humidity":49,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":1.3,"deg":193},"sys":{"pod":"d"},"dt_txt":"2016-10-10 00:00:00"},{"dt":1476068400,"main":{"temp":281.461,"temp_min":281.461,"temp_max":281.461,"pressure":791.07,"sea_level":1030.92,"grnd_level":791.07,"humidity":38,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":1.66,"deg":43.5017},"sys":{"pod":"d"},"dt_txt":"2016-10-10 03:00:00"},{"dt":1476079200,"main":{"temp":283.196,"temp_min":283.196,"temp_max":283.196,"pressure":790.6,"sea_level":1027.18,"grnd_level":790.6,"humidity":43,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":2.11,"deg":56.0039},"sys":{"pod":"d"},"dt_txt":"2016-10-10 06:00:00"},{"dt":1476090000,"main":{"temp":282.934,"temp_min":282.934,"temp_max":282.934,"pressure":790.62,"sea_level":1026.24,"grnd_level":790.62,"humidity":42,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":1.81,"deg":89.0041},"sys":{"pod":"d"},"dt_txt":"2016-10-10 09:00:00"},{"dt":1476100800,"main":{"temp":275.509,"temp_min":275.509,"temp_max":275.509,"pressure":791.56,"sea_level":1030.05,"grnd_level":791.56,"humidity":61,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.52,"deg":118.002},"sys":{"pod":"n"},"dt_txt":"2016-10-10 12:00:00"},{"dt":1476111600,"main":{"temp":272.58,"temp_min":272.58,"temp_max":272.58,"pressure":791.93,"sea_level":1033.14,"grnd_level":791.93,"humidity":52,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.31,"deg":206},"sys":{"pod":"n"},"dt_txt":"2016-10-10 15:00:00"},{"dt":1476122400,"main":{"temp":271.699,"temp_min":271.699,"temp_max":271.699,"pressure":791.88,"sea_level":1034.71,"grnd_level":791.88,"humidity":39,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.41,"deg":210.003},"sys":{"pod":"n"},"dt_txt":"2016-10-10 18:00:00"},{"dt":1476133200,"main":{"temp":270.911,"temp_min":270.911,"temp_max":270.911,"pressure":791.72,"sea_level":1035.37,"grnd_level":791.72,"humidity":42,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.42,"deg":213.005},"sys":{"pod":"n"},"dt_txt":"2016-10-10 21:00:00"},{"dt":1476144000,"main":{"temp":270.462,"temp_min":270.462,"temp_max":270.462,"pressure":792.83,"sea_level":1037.08,"grnd_level":792.83,"humidity":43,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":1.2,"deg":213.008},"sys":{"pod":"d"},"dt_txt":"2016-10-11 00:00:00"},{"dt":1476154800,"main":{"temp":282.617,"temp_min":282.617,"temp_max":282.617,"pressure":793.76,"sea_level":1033.09,"grnd_level":793.76,"humidity":42,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":0.9,"deg":284.004},"sys":{"pod":"d"},"dt_txt":"2016-10-11 03:00:00"},{"dt":1476165600,"main":{"temp":284.335,"temp_min":284.335,"temp_max":284.335,"pressure":793.33,"sea_level":1029.51,"grnd_level":793.33,"humidity":43,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":2.11,"deg":351},"sys":{"pod":"d"},"dt_txt":"2016-10-11 06:00:00"},{"dt":1476176400,"main":{"temp":283.185,"temp_min":283.185,"temp_max":283.185,"pressure":793.06,"sea_level":1028.68,"grnd_level":793.06,"humidity":44,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":1.71,"deg":334.5},"sys":{"pod":"d"},"dt_txt":"2016-10-11 09:00:00"},{"dt":1476187200,"main":{"temp":276.027,"temp_min":276.027,"temp_max":276.027,"pressure":794.26,"sea_level":1032.72,"grnd_level":794.26,"humidity":66,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":0.62,"deg":260.002},"sys":{"pod":"n"},"dt_txt":"2016-10-11 12:00:00"},{"dt":1476198000,"main":{"temp":272.091,"temp_min":272.091,"temp_max":272.091,"pressure":794.93,"sea_level":1036.09,"grnd_level":794.93,"humidity":70,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.92,"deg":201.501},"sys":{"pod":"n"},"dt_txt":"2016-10-11 15:00:00"},{"dt":1476208800,"main":{"temp":270.907,"temp_min":270.907,"temp_max":270.907,"pressure":794.77,"sea_level":1037.21,"grnd_level":794.77,"humidity":66,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.87,"deg":194.502},"sys":{"pod":"n"},"dt_txt":"2016-10-11 18:00:00"},{"dt":1476219600,"main":{"temp":270.479,"temp_min":270.479,"temp_max":270.479,"pressure":794.6,"sea_level":1038,"grnd_level":794.6,"humidity":66,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.82,"deg":195.001},"sys":{"pod":"n"},"dt_txt":"2016-10-11 21:00:00"},{"dt":1476230400,"main":{"temp":270.278,"temp_min":270.278,"temp_max":270.278,"pressure":794.74,"sea_level":1038.72,"grnd_level":794.74,"humidity":62,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":1.66,"deg":194.006},"sys":{"pod":"d"},"dt_txt":"2016-10-12 00:00:00"},{"dt":1476241200,"main":{"temp":282.782,"temp_min":282.782,"temp_max":282.782,"pressure":794.24,"sea_level":1033.24,"grnd_level":794.24,"humidity":41,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":1.42,"deg":190.504},"sys":{"pod":"d"},"dt_txt":"2016-10-12 03:00:00"},{"dt":1476252000,"main":{"temp":286.277,"temp_min":286.277,"temp_max":286.277,"pressure":792.29,"sea_level":1027.68,"grnd_level":792.29,"humidity":37,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":3.47,"deg":78.0015},"sys":{"pod":"d"},"dt_txt":"2016-10-12 06:00:00"},{"dt":1476262800,"main":{"temp":285.252,"temp_min":285.252,"temp_max":285.252,"pressure":790.84,"sea_level":1025.3,"grnd_level":790.84,"humidity":34,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":3.37,"deg":93.5011},"sys":{"pod":"d"},"dt_txt":"2016-10-12 09:00:00"},{"dt":1476273600,"main":{"temp":277.535,"temp_min":277.535,"temp_max":277.535,"pressure":790.64,"sea_level":1027.59,"grnd_level":790.64,"humidity":56,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":2.2,"deg":120.501},"sys":{"pod":"n"},"dt_txt":"2016-10-12 12:00:00"},{"dt":1476284400,"main":{"temp":272.628,"temp_min":272.628,"temp_max":272.628,"pressure":789.94,"sea_level":1029.57,"grnd_level":789.94,"humidity":52,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.31,"deg":169.511},"sys":{"pod":"n"},"dt_txt":"2016-10-12 15:00:00"},{"dt":1476295200,"main":{"temp":272.856,"temp_min":272.856,"temp_max":272.856,"pressure":789.24,"sea_level":1030.15,"grnd_level":789.24,"humidity":32,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.47,"deg":200},"sys":{"pod":"n"},"dt_txt":"2016-10-12 18:00:00"},{"dt":1476306000,"main":{"temp":271.161,"temp_min":271.161,"temp_max":271.161,"pressure":788.89,"sea_level":1030.6,"grnd_level":788.89,"humidity":36,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.48,"deg":198.504},"sys":{"pod":"n"},"dt_txt":"2016-10-12 21:00:00"},{"dt":1476316800,"main":{"temp":269.905,"temp_min":269.905,"temp_max":269.905,"pressure":789.32,"sea_level":1031.94,"grnd_level":789.32,"humidity":32,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":1.52,"deg":206.001},"sys":{"pod":"d"},"dt_txt":"2016-10-13 00:00:00"},{"dt":1476327600,"main":{"temp":284.022,"temp_min":284.022,"temp_max":284.022,"pressure":791.07,"sea_level":1029.08,"grnd_level":791.07,"humidity":30,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":2.02,"deg":264.503},"sys":{"pod":"d"},"dt_txt":"2016-10-13 03:00:00"},{"dt":1476338400,"main":{"temp":286.103,"temp_min":286.103,"temp_max":286.103,"pressure":790.54,"sea_level":1025.43,"grnd_level":790.54,"humidity":35,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":2.17,"deg":2.00018},"sys":{"pod":"d"},"dt_txt":"2016-10-13 06:00:00"},{"dt":1476349200,"main":{"temp":285.321,"temp_min":285.321,"temp_max":285.321,"pressure":790.4,"sea_level":1024.55,"grnd_level":790.4,"humidity":37,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":2.06,"deg":328.504},"sys":{"pod":"d"},"dt_txt":"2016-10-13 09:00:00"},{"dt":1476360000,"main":{"temp":276.768,"temp_min":276.768,"temp_max":276.768,"pressure":791.53,"sea_level":1028.35,"grnd_level":791.53,"humidity":64,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.07,"deg":246.01},"sys":{"pod":"n"},"dt_txt":"2016-10-13 12:00:00"},{"dt":1476370800,"main":{"temp":273.342,"temp_min":273.342,"temp_max":273.342,"pressure":791.97,"sea_level":1031.28,"grnd_level":791.97,"humidity":59,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.71,"deg":200.002},"sys":{"pod":"n"},"dt_txt":"2016-10-13 15:00:00"},{"dt":1476381600,"main":{"temp":272.413,"temp_min":272.413,"temp_max":272.413,"pressure":791.38,"sea_level":1032.1,"grnd_level":791.38,"humidity":46,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.66,"deg":199.002},"sys":{"pod":"n"},"dt_txt":"2016-10-13 18:00:00"},{"dt":1476392400,"main":{"temp":271.845,"temp_min":271.845,"temp_max":271.845,"pressure":790.76,"sea_level":1032.07,"grnd_level":790.76,"humidity":41,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":1.76,"deg":197.003},"sys":{"pod":"n"},"dt_txt":"2016-10-13 21:00:00"}]
     */

    private String cod;
    private double message;
    private int cnt;
    /**
     * dt : 1476025200
     * main : {"temp":271.24,"temp_min":271.236,"temp_max":271.24,"pressure":792.45,"sea_level":1033.59,"grnd_level":792.45,"humidity":67,"temp_kf":0}
     * weather : [{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}]
     * clouds : {"all":0}
     * wind : {"speed":1.52,"deg":200.503}
     * sys : {"pod":"n"}
     * dt_txt : 2016-10-09 15:00:00
     */

    private List<ListBean> list;

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class CityBean {
        private int id;
        private String name;
        /**
         * lon : 98.517357
         * lat : 39.743179
         */

        private CoordBean coord;
        private String country;
        private int population;
        /**
         * population : 0
         */

        private SysBean sys;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public CoordBean getCoord() {
            return coord;
        }

        public void setCoord(CoordBean coord) {
            this.coord = coord;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public SysBean getSys() {
            return sys;
        }

        public void setSys(SysBean sys) {
            this.sys = sys;
        }

        public static class CoordBean {
            private double lon;
            private double lat;

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }

        public static class SysBean {
            private int population;

            public int getPopulation() {
                return population;
            }

            public void setPopulation(int population) {
                this.population = population;
            }
        }
    }

    public static class ListBean {
        private int dt;
        /**
         * temp : 271.24
         * temp_min : 271.236
         * temp_max : 271.24
         * pressure : 792.45
         * sea_level : 1033.59
         * grnd_level : 792.45
         * humidity : 67
         * temp_kf : 0
         */

        private MainBean main;
        /**
         * all : 0
         */

        private CloudsBean clouds;
        /**
         * speed : 1.52
         * deg : 200.503
         */

        private WindBean wind;
        /**
         * pod : n
         */

        private SysBean sys;
        private String dt_txt;
        /**
         * id : 800
         * main : Clear
         * description : clear sky
         * icon : 01n
         */

        private List<WeatherBean> weather;

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public MainBean getMain() {
            return main;
        }

        public void setMain(MainBean main) {
            this.main = main;
        }

        public CloudsBean getClouds() {
            return clouds;
        }

        public void setClouds(CloudsBean clouds) {
            this.clouds = clouds;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public SysBean getSys() {
            return sys;
        }

        public void setSys(SysBean sys) {
            this.sys = sys;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        public List<WeatherBean> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherBean> weather) {
            this.weather = weather;
        }

        public static class MainBean {
            private double temp;
            private double temp_min;
            private double temp_max;
            private double pressure;
            private double sea_level;
            private double grnd_level;
            private int humidity;
            private int temp_kf;

            public double getTemp() {
                return temp;
            }

            public void setTemp(double temp) {
                this.temp = temp;
            }

            public double getTemp_min() {
                return temp_min;
            }

            public void setTemp_min(double temp_min) {
                this.temp_min = temp_min;
            }

            public double getTemp_max() {
                return temp_max;
            }

            public void setTemp_max(double temp_max) {
                this.temp_max = temp_max;
            }

            public double getPressure() {
                return pressure;
            }

            public void setPressure(double pressure) {
                this.pressure = pressure;
            }

            public double getSea_level() {
                return sea_level;
            }

            public void setSea_level(double sea_level) {
                this.sea_level = sea_level;
            }

            public double getGrnd_level() {
                return grnd_level;
            }

            public void setGrnd_level(double grnd_level) {
                this.grnd_level = grnd_level;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public int getTemp_kf() {
                return temp_kf;
            }

            public void setTemp_kf(int temp_kf) {
                this.temp_kf = temp_kf;
            }
        }

        public static class CloudsBean {
            private int all;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }
        }

        public static class WindBean {
            private double speed;
            private double deg;

            public double getSpeed() {
                return speed;
            }

            public void setSpeed(double speed) {
                this.speed = speed;
            }

            public double getDeg() {
                return deg;
            }

            public void setDeg(double deg) {
                this.deg = deg;
            }
        }

        public static class SysBean {
            private String pod;

            public String getPod() {
                return pod;
            }

            public void setPod(String pod) {
                this.pod = pod;
            }
        }

        public static class WeatherBean {
            private int id;
            private String main;
            private String description;
            private String icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
