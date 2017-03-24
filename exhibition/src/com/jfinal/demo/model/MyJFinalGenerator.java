package com.jfinal.demo.model;

import javax.sql.DataSource;

import com.jfinal.demo.config.MyJFinalConfig;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * �����ݿ�����κα䶯ʱ������һ�� main ������������Ӧ�仯���д����ع�
 */
public class MyJFinalGenerator {
	public static DataSource getDataSource() {
		PropKit.use("jdbc.properties");
		C3p0Plugin c3p0Plugin = MyJFinalConfig.createC3p0Plugin();
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}
	
	public static void main(String[] args) {
		// base model ��ʹ�õİ���
		String baseModelPackageName = "com.jfinal.demo.model.base";
		// base model �ļ�����·��
		String baseModelOutputDir = PathKit.getWebRootPath() + "/../src/com/jfinal/demo/model/base";
		
		// model ��ʹ�õİ��� (MappingKit Ĭ��ʹ�õİ���)
		String modelPackageName = "com.jfinal.demo.model";
		// model �ļ�����·�� (MappingKit �� DataDictionary �ļ�Ĭ�ϱ���·��)
		String modelOutputDir = baseModelOutputDir + "/..";
		
		// ����������
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		// ��Ӳ���Ҫ���ɵı���
		gernerator.addExcludedTable("sys_code");
		gernerator.addExcludedTable("sys_menu");
		// �����Ƿ��� Model ������ dao ����
		gernerator.setGenerateDaoInModel(true);
		// �����Ƿ������ֵ��ļ�
		gernerator.setGenerateDataDictionary(false);
		// ������Ҫ���Ƴ��ı���ǰ׺��������modelName��������� "osc_user"���Ƴ�ǰ׺ "osc_"�����ɵ�model��Ϊ "User"���� OscUser
		gernerator.setRemovedTableNamePrefixes("sys_");
		// ����
		gernerator.generate();
	}
}
