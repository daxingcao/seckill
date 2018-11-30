package com.caodaxing.shopseckill.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig {

	@Autowired
	private FreeMarkerProperties properties;

	@Bean
	public FreeMarkerConfigurer setConfig(@Value("${auto_import}") String auto_imports) {
		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
		writeProperties(config);
		Configuration configuration = null;
		try {
			configuration = config.createConfiguration();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		setAutoImports(configuration, auto_imports);
		config.setConfiguration(configuration);
		return config;
	}

	private void writeProperties(FreeMarkerConfigurer config) {
		config.setTemplateLoaderPaths(this.properties.getTemplateLoaderPath());
		config.setDefaultEncoding(this.properties.getCharsetName());
		config.setPreferFileSystemAccess(this.properties.isPreferFileSystemAccess());
		Properties settings = new Properties();
		settings.putAll(this.properties.getSettings());
		config.setFreemarkerSettings(settings);
	}

	private void setAutoImports(Configuration configuration, String auto_imports) {
		if ("".equals(auto_imports.trim()) || "_".equals(auto_imports.trim())) {
			return;
		}
		String[] split = auto_imports.split(";");
		Map<String, String> imports = new HashMap<>(split.length);
		for (String string : split) {
			String[] key_value = string.split("as");
			if (key_value.length != 2) {
				throw new RuntimeException("freemarker config is error,maybe is formatter error about auto_import!");
			}
			imports.put(key_value[1].trim(), key_value[0].trim());
		}
		configuration.setAutoImports(imports);
	}

}
