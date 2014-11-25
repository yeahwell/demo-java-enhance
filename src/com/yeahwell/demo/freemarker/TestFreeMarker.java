package com.yeahwell.demo.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreeMarker {

	public static void main(String[] args) throws IOException, TemplateException {

		// 1.创建FreeMarker配置实例
		Configuration cfg = new Configuration();
		// 相对路径,相对于项目
		cfg.setDirectoryForTemplateLoading(new File("template"));
		// 2. 创建数据模型，通常为树状结构
		Map root = new HashMap();
		root.put("user", "颜佳伟");
		// 3. 加载模板文件
		Template t1 = cfg.getTemplate("a.ftl","UTF-8");
		// 4. 显示生成的数据
		Writer out = new OutputStreamWriter(System.out);
		t1.process(root, out);
		out.flush();
		out.close();
		
	}
}
