package org.delaware.util;

import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

public class DateUtil {

      public static long getNumberofDays(java.util.Date fromDate, java.util.Date toDate)
      {
            long results = ChronoUnit.DAYS.between(fromDate.toInstant(),toDate.toInstant());
            return results;
      }
}
