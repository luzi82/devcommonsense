import java.lang.System;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Example {
    public static void main(String[] argv){
        ZoneId chinaZone = ZoneId.of("Asia/Shanghai");
        ZoneId gmtZone = ZoneId.of("GMT");

        // DateTime+TimeZone method

        // China have DST in 1989, so it is UTC+9 instead of UTC+8
        ZonedDateTime t8964china = ZonedDateTime.of(1989,6,4,0,0,0,0,chinaZone);
        System.out.println("t8964china = "+t8964china.toString());
        // t8964china = 1989-06-04T00:00+09:00[Asia/Shanghai]

        // Convert to GMT
        ZonedDateTime t8964gmt   = t8964china.withZoneSameInstant(gmtZone);
        System.out.println("t8964gmt   = "+t8964gmt.toString());
        // t8964gmt   = 1989-06-03T15:00Z[GMT]

        // China is UTC+8 in winter
        ZonedDateTime t8914china = ZonedDateTime.of(1989,1,4,0,0,0,0,chinaZone);
        System.out.println("t8914china = "+t8914china.toString());
        // t8914china = 1989-01-04T00:00+08:00[Asia/Shanghai]

        // China do not have DST after 1991, it is UTC+8
        ZonedDateTime t1864china = ZonedDateTime.of(2018,6,4,0,0,0,0,chinaZone);
        System.out.println("t1864china = "+t1864china.toString());
        // t1864china = 2018-06-04T00:00+08:00[Asia/Shanghai]

        // Number of hour between 8964 and 8914, odd number
        long diff = t8914china.until(t8964china,ChronoUnit.HOURS);
        System.out.println("t8964china - t8914china = "+diff+" hours");
        // t8964china - t8914china = 3623 hours

        // Compare between gmt and china time, same result
        diff = t8914china.until(t8964gmt,ChronoUnit.HOURS);
        System.out.println("t8964gmt   - t8914china = "+diff+" hours");
        // t8964gmt   - t8914china = 3623 hours

        // Timezone independent epoch timestamp method

        // Convert ZonedDateTime to epoch second
        long t8964Sec = t8964china.toInstant().getEpochSecond();
        System.out.println("t8964Sec   = "+t8964Sec);
        // t8964Sec   = 612889200

        // Convert epoch second to GMT
        t8964gmt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(t8964Sec),gmtZone);
        System.out.println("t8964gmt   = "+t8964gmt.toString());
        // t8964gmt   = 1989-06-03T15:00Z[GMT]

        // Number of hour between 8964 and 8914
        long t8914Sec = t8914china.toInstant().getEpochSecond();
        diff = t8964Sec - t8914Sec;
        diff /= 60*60; // number of sec in hour
        System.out.println("t8964china - t8914china = "+diff+" hours");
        // t8964china - t8914china = 3623 hours
    }
}
