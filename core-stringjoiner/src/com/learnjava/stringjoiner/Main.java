package com.learnjava.stringjoiner;

import java.util.StringJoiner;

/**
 * StringJoiner practice.
 *
 * @author lsrong
 */
public class Main {

  public static void main(String[] args) {
    String[] fields = {"name", "position", "salary"};
    String table = "employee";
    String select = buildSelectSql(table, fields);
    System.out.println(select);
    System.out.println(
        "SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");
  }

  /**
   * 构建 sql 简单查询语句
   *
   * @param table 表名
   * @param fields 字段名
   * @return sql
   */
  static String buildSelectSql(String table, String[] fields) {
    StringBuilder sqlSelect = new StringBuilder();
    sqlSelect.append("SELECT ");
    StringJoiner sj = new StringJoiner(", ");
    for (String field : fields) {
      sj.add(field);
    }
    sqlSelect.append(sj.toString());
    sqlSelect.append(" FROM ");
    sqlSelect.append(table);

    return sqlSelect.toString();
  }
}
