package com.yeahwell.demo.freemarker;

import java.awt.print.Book;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 使用Freemarker生成html文件
 * @author yeahwell
 *
 */
public class MakeFileManager {
	
/*	public String make(Book book, BookFtl bookFtl) {
        Configuration cfg = new Configuration();//配制

        String realPath = bookFtl.getRealPath();
        String templatePath = realPath + "/WEB-INF/templates/book";
        String cDateStr = "book/" +
                          new SimpleDateFormat("yyyyMMdd").format(new java.util.
                Date());
        String filePostfix = ".html";
        String path = realPath + "/" + cDateStr;
        String fileTimeName = new SimpleDateFormat("yyyyMMddhhmmss").format(new
                java.util.Date());
        String returnFileName = cDateStr + "/" + fileTimeName + filePostfix;
        String fileName = "";
        File bookDir = new File(path);
        if (bookDir.exists()) {
            fileName = path + "/" + fileTimeName + filePostfix;
        } else {
            bookDir.mkdirs();
            fileName = path + "/" + fileTimeName + filePostfix;
        }
        try {
            //设置freemarker的参数
            cfg.setNumberFormat("0.##########");//生成html文件时web.xml配制无效
            //cfg.setEncoding();
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Template bookTemplate = cfg.getTemplate(bookFtl.getTemplateName());//模板对象
            Map root = new HashMap();
            root.put("book", book);
            root.put("book2",book);
            Writer out = new OutputStreamWriter(new FileOutputStream(fileName));
            try {
                bookTemplate.process(root, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }*/
}
