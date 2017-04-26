/*
 * Copyright Banksiel S.p.A.
 */

package autudc.logger;

import org.apache.log4j.*;

public class UniqueFileAppender {

  private static UniqueFileAppender instance  = null;
  private static DailyRollingFileAppender app = null;

  protected UniqueFileAppender() {
    this.app = null;
  }


  public static UniqueFileAppender getInstance() {
    if (instance == null) {
      synchronized(UniqueFileAppender.class) {
        if (instance == null) {
          instance = new UniqueFileAppender();
        }
      }
    }
    return instance;
  }

  public DailyRollingFileAppender getAppender() {
      return this.app;
  }

  public void setAppender(DailyRollingFileAppender fa) {
      this.app = fa;
  }

}
