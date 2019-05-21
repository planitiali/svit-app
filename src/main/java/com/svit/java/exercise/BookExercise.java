package com.svit.java.exercise;

/**
 * 
 * @author sv-it
 * 
 * 
 * 
 * Please finish to do in each class and uncomment statements in main and make output as following
 * 

		JDK User's Guide
		BY MageLang
		Preface
		1. Overview of JDK
		2. Getting Started
		
		The Preface
		
		1. Overview of JDK
		
		Intro paragraph of chapter 1
		
		Introduction
		1st para of intro section of chapter 1
		2nd para of intro section of chapter 1
		
		Features
		1st para of features section of chapter 1
		
		Help
		1st para of help section of chapter 1
		2nd para of help section of chapter 1
		
		2. Getting Started
		
		
		Installation
		1st para of install section of chapter 2
		
		Unzipping
		1st para of unzip subsection of install section chapter 2
		
		Setting CLASSPATH
		1st para of classpath subsection of install section chapter 2
		
		Setting PATH
		1st para of setting PAHT subsection of install chapter 2
		2nd para of setting PATH subsection of install chapter 2
		
		the index


 */


interface TextEntity{
	public static final int MAX_CONTAINED = 20;	
	public void print();	
}

abstract class TextContainer implements TextEntity{
	private int n=0;
	private TextEntity[] elements;
	
	public void add(TextEntity e){
		if(elements == null)
			elements = new TextEntity[MAX_CONTAINED];
			
		elements[n] = e;
		n += 1;
	}

	public void print(){
		for (int i=0; i<n; i++)
			elements[i].print();	
	} 	
}

class Chapter extends TextContainer{
	private String title;
	
	//to do
}

class Section extends TextContainer{
	private String title;
	
	//to do
}

class Paragraph extends TextContainer{
	private String text;
	
	//to do
}

class Book extends TextContainer{
	private String TOC;
	private String index;
	private String title;
	private String author;
	
	//to do 
}



public class BookExercise {
    
	public static void main(String args[]) 
	{ 
// 		Book b = new Book("JDK User's Guide", "MageLang");
// 		b.setTOC("Preface\n"+
// 					"1. Overview of JDK\n" +
// 					"2. Getting Started\n");
// 		b.setIndex("the index");
// 		
// 		Paragraph preface = new Paragraph("The Preface");
// 		Chapter overview = new Chapter("1. Overview of JDK");
// 		Chapter started = new Chapter("2. Getting Started");
// 		
// 		b.add(preface);
// 		b.add(overview);
// 		b.add(started);
// 		
// 		//chapter 1
// 		Paragraph hello = new Paragraph("Intro paragraph of chapter 1");
// 		Section intro = new Section("Introduction");
// 		intro.add(new Paragraph("1st para of intro section of chapter 1"));
// 		intro.add(new Paragraph("2nd para of intro section of chapter 1"));
// 		
// 		Section features = new Section("Features");
// 		features.add(new Paragraph("1st para of features section of chapter 1"));
// 		
// 		Section help = new Section("Help");
// 		help.add(new Paragraph("1st para of help section of chapter 1"));
// 		help.add(new Paragraph("2nd para of help section of chapter 1"));
// 		
// 		overview.add(hello);
// 		overview.add(intro);
// 		overview.add(features);
// 		overview.add(help);
// 		
// 		//chapter 2
// 		Section install = new Section("Installation");
// 		install.add(new Paragraph("1st para of install section of chapter 2"));
// 		
// 		// 2 subsections of section 1
// 		Section unzip = new Section("Unzipping");
// 		unzip.add(new Paragraph("1st para of unzip subsection of install section chapter 2"));
// 		
// 		Section classpath = new Section("Setting CLASSPATH");
// 		classpath.add(new Paragraph("1st para of classpath subsection of install section chapter 2"));
// 		
// 		Section path = new Section("Setting PATH");
// 		path.add(new Paragraph("1st para of setting PAHT subsection of install chapter 2"));
// 		path.add(new Paragraph("2nd para of setting PATH subsection of install chapter 2"));
// 		
// 		install.add(unzip);
// 		install.add(classpath);
// 		install.add(path);
// 		
// 		started.add(install);
// 		
// 		//New: print the book!
// 		b.print();
	} 
}
