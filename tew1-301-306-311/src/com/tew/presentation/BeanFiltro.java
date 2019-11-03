package com.tew.presentation;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "filtro")
public class BeanFiltro {

	private int min;
	private int max;

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}