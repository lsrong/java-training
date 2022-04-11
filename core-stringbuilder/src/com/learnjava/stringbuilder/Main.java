package com.learnjava.stringbuilder;

import java.util.StringJoiner;

/**
 * class StringBuilder 
 * 
 * @author lsrong
 *
 */
public class Main {
	public static void main(String[] args) {
		String[] fields = { "name", "position", "salary", "created_at"};
		String table = "employee";
		String insert = buildInsertSql(table, fields);
		System.out.println(insert);
		System.out.println(
				"INSERT INTO employee (name, position, salary, created_at) VALUES (?, ?, ?, ?)".equals(insert) ? "测试成功" : "测试失败");
	}

	/**
	 * 通过 StringBuiler 构建 sql 插入字符串
	 * 
	 * @param table
	 * @param fields
	 * 
	 * @return
	 */
	static String buildInsertSql(String table, String[] fields) {
		// StringBuilder append, toString
		StringBuilder sqlSnytax = new StringBuilder();
		sqlSnytax.append("INSERT INTO ");
		sqlSnytax.append(table);
		sqlSnytax.append(" (");
		
		// String.join
//		sqlSnytax.append(String.join(", ", fields));
		
		// StringJoiner
		StringJoiner sj = new StringJoiner(", ");
		for(String field:fields) {
			sj.add(field);
		}
		sqlSnytax.append(sj.toString());
		
		sqlSnytax.append(") VALUES (");
		for(int i =0; i<fields.length; i++) {
			sqlSnytax.append("?, ");
		}
		// 去除最后一个 “, ”
		sqlSnytax.delete(sqlSnytax.length()- 2, sqlSnytax.length());
		
		sqlSnytax.append(")");
		return sqlSnytax.toString();
	}

}
