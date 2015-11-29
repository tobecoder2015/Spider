package com.hiberate.demo;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;





public class ExportDB {
	public static void main(String[] args) {
		
		//通过configure方法读取xmL文件  默认是读取配置文件
		Configuration cfg=new Configuration().configure();
	
		SchemaExport export=new SchemaExport(cfg);
		
		export.create(true, true);
	}
	
}
