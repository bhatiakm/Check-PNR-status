package com.example.projectsecond;

import java.util.ArrayList;

public class MyClass {
	String tno;
	String tname;
	String dep;
	String arr;
	ArrayList<String> days;
	ArrayList<String> classes;
	String availability;/////CHANNGGEEEEEE
	
	MyClass(String tno,String tname,String dep,String arr,ArrayList<String> days,ArrayList<String> classes,String av){
		this.tno=tno;
		this.tname=tname;
		this.dep=dep;
		this.arr=arr;
		this.days=days;
		this.classes=classes;
		availability=av;
	}

}
