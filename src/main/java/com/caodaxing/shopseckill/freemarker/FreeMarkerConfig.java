package com.caodaxing.shopseckill.freemarker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.alibaba.druid.util.StringUtils;
import com.caodaxing.shopseckill.autoconfigure.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * @author daxing.cao
 * @description 自定义freemarker配置
 */
@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig {

	@Autowired
	private FreeMarkerProperties properties;

	@Bean
	public FreeMarkerConfigurer setConfig(SystemProperties systemProperties) {
		Assert.notNull(systemProperties,"SystemProperties is must not null!");
		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
		writeProperties(config);
		Configuration configuration = null;
		try {
			configuration = config.createConfiguration();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		String autoImport = systemProperties.getFreemarkerImport();
		if(!StringUtils.isEmpty(autoImport)){
			this.setAutoImports(configuration, autoImport);
		}
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

	private void setAutoImports(Configuration configuration, String autoImport) {
		Assert.notNull(autoImport,"auto_import must not null!");
		String[] split = autoImport.split(";");
		Map<String, String> imports = new HashMap<>(split.length);
		for (String string : split) {
			String[] keyValue = string.split("as");
			if (keyValue.length != 2) {
				throw new RuntimeException("freemarker config is error,maybe is formatter error about auto_import!");
			}
			imports.put(keyValue[1].trim(), keyValue[0].trim());
		}
		configuration.setAutoImports(imports);
	}

}
