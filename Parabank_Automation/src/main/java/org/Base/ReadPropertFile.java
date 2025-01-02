package org.Base;

import java.io.FileReader;

import java.util.Properties;

public class ReadPropertFile {
	Properties pro;
	public ReadPropertFile() {
		try {
			FileReader fr = new FileReader("./configuration/config.properties");
			pro = new Properties();
			pro.load(fr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getBrowser() {
		String browserName = pro.getProperty("browser");
		return browserName;
	}

	public String getUrl() {
		String url = pro.getProperty("testUrl");
		return url;
	}
}
