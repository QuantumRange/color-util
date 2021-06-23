package de.quantumrange.colorUtil.impl;

import de.quantumrange.colorUtil.AlphaColor;
import de.quantumrange.colorUtil.Color;

import java.nio.ByteBuffer;
import java.util.regex.Pattern;

public class CMYKColor implements Color<CMYKColor>, AlphaColor<Float> {

	/**
	 * c Clay
	 * m Magenta
	 * y Yellow
	 * k Key
	 * a Alpha
	 */
	private float c, m, y, k, a;

	public CMYKColor(float c, float m, float y, float k) {
		this.c = c;
		this.m = m;
		this.y = y;
		this.k = k;
		this.a = 1.0f;
	}

	public CMYKColor(float c, float m, float y, float k, float a) {
		this.c = c;
		this.m = m;
		this.y = y;
		this.k = k;
		this.a = a;
	}

	public CMYKColor() {
		this(0f, 0f, 0f, 1f);
	}

	public CMYKColor(java.awt.Color color) {
		fromColor(color);
	}

	public CMYKColor(Color<?> color) {
		this(color.toColor());
	}

	@Override
	public java.awt.Color toColor() {
		int r = (int) (255 * (1 - c) * (1 - k));
		int g = (int) (255 * (1 - m) * (1 - k));
		int b = (int) (255 * (1 - y) * (1 - k));

		return new java.awt.Color(r, g, b, getRGBAlpha());
	}

	@Override
	public CMYKColor fromColor(java.awt.Color color) {
		float r = color.getRed() / 255f;
		float g = color.getGreen() / 255f;
		float b = color.getBlue() / 255f;

		this.k = 1 - Math.max(r, Math.max(g, b));
		this.c = (1 - r - k) / (1 - k);
		this.m = (1 - g - k) / (1 - k);
		this.y = (1 - b - k) / (1 - k);

		setRGBAlpha(color.getAlpha());
		return this;
	}

	@Override
	public byte[] toBytes() {
		ByteBuffer buffer = ByteBuffer.allocate(20);
		buffer.putFloat(c).putFloat(m).putFloat(y).putFloat(k).putFloat(a);
		return buffer.array();
	}

	@Override
	public CMYKColor fromBytes(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		c = buffer.getFloat();
		m = buffer.getFloat();
		y = buffer.getFloat();
		k = buffer.getFloat();
		a = buffer.getFloat();
		return this;
	}

	/**
	 * @return cyan channel of the Color
	 */
	public float getCyan() {
		return c;
	}

	/**
	 * @param c is the cyan channel of the Color.
	 */
	public void setCyan(float c) {
		this.c = c;
	}

	/**
	 * @return magenta channel of the Color
	 */
	public float getMagenta() {
		return m;
	}

	/**
	 * @param m is the magenta channel of the Color.
	 */
	public void setMagenta(float m) {
		this.m = m;
	}

	/**
	 * @return yellow channel of the Color
	 */
	public float getYellow() {
		return y;
	}

	/**
	 * @param y is the Yellow channel of the Color.
	 */
	public void setYellow(float y) {
		this.y = y;
	}

	/**
	 * @return key channel of the Color
	 */
	public float getKey() {
		return k;
	}

	/**
	 * @param k is the key channel of the Color.
	 */
	public void setKey(float k) {
		this.k = k;
	}

	@Override
	public Float getAlpha() {
		return a;
	}

	@Override
	public void setAlpha(Float alpha) {
		this.a = alpha;
	}

	@Override
	public int getRGBAlpha() {
		return (int) (getAlpha() * 255f);
	}

	@Override
	public void setRGBAlpha(int alpha) {
		setAlpha(alpha / 255f);
	}

	@Override
	public String toString() {
		return "CMYK(C:%f,M:%f,Y:%f,K:%f%s)"
				.formatted(c, m, y, k, generateAlphaString());
	}
}
