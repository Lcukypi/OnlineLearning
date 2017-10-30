package com.github.barteksc.sample;

import java.util.List;

public class FirstEvent {

	private List<File> mMsg;

	public FirstEvent(List<File> msg) {
		mMsg = msg;
	}
	public List<File> getMsg(){
		return mMsg;
	}
}
