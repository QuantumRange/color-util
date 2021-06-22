package de.quantumrange.colorUtil.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RGBColorTest {

	@Test
	void getRed() {
		assertEquals(132, new RGBColor(132, 50, 0, 72).getRed());
	}

	@Test
	void getGreen() {
		assertEquals(50, new RGBColor(132, 50, 0, 72).getGreen());
	}

	@Test
	void getBlue() {
		assertEquals(0, new RGBColor(132, 50, 0, 72).getBlue());
	}

	@Test
	void getAlpha() {
		assertEquals(72, new RGBColor(132, 50, 0, 72).getAlpha());
	}

	@Test
	void setRed() {
		RGBColor color = new RGBColor(132, 50, 0, 72);
		color.setRed(155);
		assertEquals(155, color.getRed());
	}

	@Test
	void setGreen() {
		RGBColor color = new RGBColor(132, 50, 0, 72);
		color.setGreen(74);
		assertEquals(74, color.getGreen());
	}

	@Test
	void setBlue() {
		RGBColor color = new RGBColor(132, 50, 0, 72);
		color.setBlue(19);
		assertEquals(19, color.getBlue());
	}

	@Test
	void setAlpha() {
		RGBColor color = new RGBColor(132, 50, 0, 72);
		color.setAlpha(0);
		assertEquals(0, color.getAlpha());
	}

}