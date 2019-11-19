package com.heyi.erciyuan.erciyuan.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Value;

public class EntityGenerator {
	@Test
	public  void  generator() throws Exception, XMLParserException {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile =new File(".\\src\\main\\resources\\generator.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator batisGenerator =new MyBatisGenerator(config, callback, warnings);
		batisGenerator.generate(null);
		
	}

}
