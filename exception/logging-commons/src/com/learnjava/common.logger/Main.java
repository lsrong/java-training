package com.learnjava.common.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;

/**
 * Commons logging
 *
 * @author lsrong
 */
public class Main {

  static final Log log = LogFactory.getLog(Main.class);

  public static void main(String[] args) {
    log.info("Start process...");
    try {
      "".getBytes("invalidCharsetName");
    } catch (UnsupportedEncodingException e) {
      // 使用log.error(String, Throwable)打印异常
      log.error(e.toString());
    }
    log.info("Process end.");
  }
}
