package com.yeahwell.demo.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreeMarker2 {

	public static void main(String[] args) throws IOException, TemplateException {

		// 1.创建FreeMarker配置实例
		Configuration cfg = new Configuration();
		// 相对路径,相对于项目
		cfg.setDirectoryForTemplateLoading(new File("template"));
		// 2. 创建数据模型，通常为树状结构
		Map root = new HashMap();
		root.put("user", "老高");
		root.put("random", new Random().nextInt(100));
		
		List addresses = new ArrayList();
		addresses.add(new Address("中国","北京"));
		addresses.add(new Address("中国","上海"));
		addresses.add(new Address("美国","纽约"));
		root.put("addresses", addresses);

		root.put("date1", new Date());
		root.put("htm2", "<b>粗体</b>");
		
		// 3. 加载模板文件
		Template t1 = cfg.getTemplate("a.ftl");
		// 4. 显示生成的数据
		Writer out = new OutputStreamWriter(System.out);
		t1.process(root, out);
		out.flush();
		out.close();
		
	}
}
