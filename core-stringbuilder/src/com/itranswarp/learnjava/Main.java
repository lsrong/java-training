package com.itranswarp.learnjava;

/**
 * Learn Java from https://www.liaoxuefeng.com/
 * 
 * @author liaoxuefeng
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

	static String buildInsertSql(String table, String[] fields) {
		// TODO:  StringBuilder append, toString
		StringBuilder sqlSnytax = new StringBuilder();
		sqlSnytax.append("INSERT INTO ");
		sqlSnytax.append(table);
		sqlSnytax.append(" (");
		sqlSnytax.append(String.join(", ", fields));
		sqlSnytax.append(") VALUES (");
		for(int i =0; i<fields.length-1; i++) {
			sqlSnytax.append("?, ");
		}
		sqlSnytax.append("?");
		sqlSnytax.append(")");
		return sqlSnytax.toString();
	}

}
