package com.caodaxing.shopseckill.utils;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;
/**
 * @author daxing.cao
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

	private boolean userDefinedFlag = true;
	
	/**
	 * 实体类生成字段注解自定义
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable table, IntrospectedColumn column) {
		if(userDefinedFlag) {
			if(StringUtility.stringHasValue(column.getRemarks())) {
				field.addJavaDocLine("/**");
				field.addJavaDocLine(" * "+column.getRemarks());
				field.addJavaDocLine(" */");
			}
		}else {
			super.addFieldComment(field, table, column);
		}
	}

	/**
	 * mapper映射文件注解自定义
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
		if (userDefinedFlag) {
			return;
		}else {
			super.addComment(xmlElement);
		}
	}

	/**
	 * dao层接口注释自定义
	 */
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (userDefinedFlag) {
			return;
		}else {
			super.addGeneralMethodComment(method, introspectedTable);
		}
	}

	/**
	 * 实体类getter方法注解自定义
	 */
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (userDefinedFlag) {
			
		}else {
			super.addGetterComment(method, introspectedTable, introspectedColumn);
		}
	}

	/**
	 * 实体类setter方法注解自定义
	 */
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (userDefinedFlag) {
			return;
		} else {
			super.addSetterComment(method, introspectedTable, introspectedColumn);
		}
	}

}
