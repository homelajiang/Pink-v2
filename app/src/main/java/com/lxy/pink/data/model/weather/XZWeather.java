package com.lxy.pink.data.model.weather;

import android.text.TextUtils;

import com.lxy.pink.BuildConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by yuan on 2016/10/9.
 */

public class XZWeather {

    public Weather toWeather() {
        if (results == null || results.size() < 0)
            return null;
        ResultsEntity res = results.get(0);
        if (res == null)
            return null;
        Date lastUpdate = null;
        //2017-02-14T10:25:00+08:00
        String lastUpdateString = res.getLast_update();
        if (!TextUtils.isEmpty(lastUpdateString)) {
            try {
                lastUpdateString = lastUpdateString.replaceAll(":[^:]*$", "00");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
                lastUpdate = sdf.parse(lastUpdateString);
            } catch (ParseException e) {
                if (BuildConfig.DEBUG)
                    e.printStackTrace();
            }
        }
        return new Weather(0,res.getLocation().getId(),
                res.getLocation().getName(),
                res.getLocation().getCountry(),
                res.getLocation().getPath(),
                res.getLocation().getTimezone(),
                res.getLocation().getTimezone_offset(),
                res.getNow().getTemperature(),
                res.getNow().getCode(),
                res.getNow().getText(),
                lastUpdate);
    }

    private List<ResultsEntity> results;

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public static class ResultsEntity {
        /**
         * location : {"id":"WX4FBXXFKE4F","name":"北京","country":"CN","path":"北京,北京,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"}
         * now : {"text":"晴","code":"0","temperature":"12"}
         * last_update : 2017-02-13T15:05:00+08:00
         */

        private LocationEntity location;
        private NowEntity now;
        private String last_update;

        public LocationEntity getLocation() {
            return location;
        }

        public void setLocation(LocationEntity location) {
            this.location = location;
        }

        public NowEntity getNow() {
            return now;
        }

        public void setNow(NowEntity now) {
            this.now = now;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public static class LocationEntity {
            /**
             * id : WX4FBXXFKE4F
             * name : 北京
             * country : CN
             * path : 北京,北京,中国
             * timezone : Asia/Shanghai
             * timezone_offset : +08:00
             */

            private String id;
            private String name;
            private String country;
            private String path;
            private String timezone;
            private String timezone_offset;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getTimezone_offset() {
                return timezone_offset;
            }

            public void setTimezone_offset(String timezone_offset) {
                this.timezone_offset = timezone_offset;
            }
        }

        public static class NowEntity {
            /**
             * text : 晴
             * code : 0
             * temperature : 12
             */

            private String text;
            private String code;
            private String temperature;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }
        }
    }
}
