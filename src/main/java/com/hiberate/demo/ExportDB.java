package com.hiberate.demo;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;





public class ExportDB {
	public static void main(String[] args) {
		
		//ͨ��configure������ȡxmL�ļ�  Ĭ���Ƕ�ȡ�����ļ�
		Configuration cfg=new Configuration().configure();
	
		SchemaExport export=new SchemaExport(cfg);
		
		export.create(true, true);
	}
	
}
