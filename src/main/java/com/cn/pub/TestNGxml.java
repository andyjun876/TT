package com.cn.pub;
import java.io.*;

import org.jdom.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class TestNGxml {
	//����testngxml��
	public void xmlnew(String[] list,String[] listurl) throws FileNotFoundException, IOException{
		//�����ĵ�
        Document document = new Document();
        //������Ԫ��
        Element suite = new Element("suite");
        document = new Document(suite,new DocType("suite","http://testng.org/testng-1.0.dtd"));
        //�Ѹ�Ԫ�ؼ��뵽document��
        //document.addContent(people); 
        
        //����ע��
        //Comment rootComment = new Comment("�����ݴӳ��������XML�У�");      
        //people.addContent(rootComment);
        
        suite.setAttribute("name", "���Լƻ�");
        suite.setAttribute("parallel", "classes");
        suite.setAttribute("thread-count", "5");
        //���ݴ��ݲ������� ģ�� ��url ����xml�ű�
        for(int i = 0;i<list.length;i++){
        	testcase(suite,list[i],listurl[i]);
        }
        //testcase(suite,"��½","com.dwr.test.ApiTestNG");
        listeners(suite);
        
        
        //����xml�����ʽ
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");//���ñ���
        format.setIndent("    ");//��������
        
        
        //�õ�xml�����
        XMLOutputter out = new XMLOutputter(format);
        //�����������xml��
        out.output(document, new FileOutputStream("webapps/AutoTesting/WEB-INF/classes/com/dwr/test/testng.xml"));//����FileWriter
        //out.output(document, new FileOutputStream("e:/jdom.xml"));//����FileWriter

	}
	
	//testng listeners����
	public void listeners(Element suite){
		Element listeners = new Element("listeners");
		suite.addContent(listeners);
        
		Element listener1 = new Element("listener");
		listeners.addContent(listener1);
        
		listener1.setAttribute("class-name", "org.uncommons.reportng.HTMLReporter");
        
        Element listener2 = new Element("listener");
        //person2_name.setText("��־ӱ");
        listeners.addContent(listener2);
        
        listener2.setAttribute("class-name", "org.uncommons.reportng.JUnitXMLReporter");       
	}
	
	//testng test����
	public void testcase(Element suite,String name,String classurl){
		//������Ԫ��
        Element test1 = new Element("test");
        //��Ԫ�ؼ��뵽��Ԫ����
        suite.addContent(test1);
        //����person1Ԫ������
        test1.setAttribute("name", name);
        test1.setAttribute("preserve-order", "true");
        test1.setAttribute("verbose", "2");
        
        Element parameter = new Element("parameter");
        //person1_address.setText("���");
        test1.addContent(parameter);
        parameter.setAttribute("name", "parametername");
        parameter.setAttribute("value", name);

        
        Element test1_classes = new Element("classes");
        //person1_name.setText("���»�");
        test1.addContent(test1_classes);
        
        Element test1_classes_class = new Element("class");
        //person1_address.setText("���");
        test1_classes.addContent(test1_classes_class);
        test1_classes_class.setAttribute("name", classurl);
	}
}
