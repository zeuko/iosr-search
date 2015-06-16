package com.iosr.search.impl;

import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;


public class ScrapyRunner implements  Runnable{

	private Process process;
	private String url;
	
	private String scrapyRun ="C:\\Python27\\Scripts\\scrapy.exe runspider C:\\scrapy\\ios\\iosr\\spiders\\spider.py -a start_urls=\"";
	public ScrapyRunner(String url){
		this.url = url;
	}
	
	public void run(){
		try {
			setProcess(Runtime
			.getRuntime()
			.exec(scrapyRun + url + "\""));


		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Process getProcess() {
		return process;
	}


	public void setProcess(Process process) {
		this.process = process;
	}
	
	public void destroyProcess(){
		process.destroy();
	}
}
