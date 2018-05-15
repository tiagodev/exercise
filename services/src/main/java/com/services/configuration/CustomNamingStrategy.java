package com.services.configuration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy implements PhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return name;
	}

	@Override
	public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		return name;
	}
	
	@Override
	public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		String seq = name.getText();
		
		if (!name.getText().toLowerCase().endsWith("_seq")) {
			seq = name.getText() + "_seq";
		}
		
		final List<String> parts = splitAndReplace(seq);
		return jdbcEnvironment.getIdentifierHelper().toIdentifier(
				joinToOracle(parts),
				name.isQuoted()
		);
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		final List<String> parts = splitAndReplace(name.getText());
		return jdbcEnvironment.getIdentifierHelper().toIdentifier(
				joinToOracle(parts),
				name.isQuoted()
		);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		final List<String> parts = splitAndReplace(name.getText());
		return jdbcEnvironment.getIdentifierHelper().toIdentifier(
				joinToOracle(parts),
				name.isQuoted()
		);
	}

	private LinkedList<String> splitAndReplace(String name) {
		LinkedList<String> result = new LinkedList<>();
		for (String part : StringUtils.splitByCharacterTypeCamelCase(name)) {
			if (part == null || part.trim().isEmpty() || part.equals("_")) {
				continue;
			}
			result.add(part.toLowerCase(Locale.ROOT));
		}
		return result;
	}

	private String join(List<String> parts) {
		boolean firstPass = true;
		String separator = "";
		StringBuilder joined = new StringBuilder();
		for (String part : parts) {
			joined.append(separator).append(part);
			if (firstPass) {
				firstPass = false;
				separator = "_";
			}
		}
		return joined.toString();
	}
	
	// oracle <= 30 chars
	private String joinToOracle(List<String> parts) {
		String name = join(parts);
		
		if(StringUtils.length(name) > 30){
			List<String> split = Arrays.asList(StringUtils.split(name, "_"));
			
			for(int i = 0; i < split.size(); i++){
				split.set(i, split.get(i).substring(0, 1));
				name = join(split);
				if(name.length() <= 30){
					break;
				}
			}
		}
		
		return name;
	}
}

