package de.quantumrange.colorUtil.impl;

import de.quantumrange.colorUtil.AlphaColor;
import de.quantumrange.colorUtil.Color;

public class RGBColor implements Color<RGBColor>, AlphaColor<Integer> {

	/**
	 * rgba Red,Green,Blue,Alpha
	 */
	private int rgba;

	public RGBColor(java.awt.Color color) {
		this.rgba = toInt(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}

	public RGBColor(Color<?> color) {
		this(color.toColor());
	}

	public RGBColor() {
		this.rgba = 0;
	}

	public RGBColor(int r, int g, int b) {
		this(r, g, b, 255);
	}

	public RGBColor(int r, int g, int b, int a) {
		this.rgba = toInt(r, g, b, a);
	}

	private int toInt(int r, int g, int b, int a) {
		r = Math.max(Math.min(r, 255), 0);
		g = Math.max(Math.min(g, 255), 0);
		b = Math.max(Math.min(b, 255), 0);
		a = Math.max(Math.min(a, 255), 0);

		return /*0x00000000 |*/ (r << 24) | (g << 16) | (b << 8) | (a);
	}

	@Override
	public java.awt.Color toColor() {
		return new java.awt.Color(getRed(), getGreen(), getBlue(), getAlpha());
	}

	@Override
	public RGBColor fromColor(java.awt.Color color) {
		this.rgba = color.getRGB();
		return this;
	}

	@Override
	public byte[] toBytes() {
		return new byte[] {(byte) getRed(), (byte) getGreen(), (byte) getBlue(), (byte) getAlpha().intValue()};
	}

	@Override
	public RGBColor fromBytes(byte[] bytes) {
		rgba = toInt(bytes[0], bytes[1], bytes[2], bytes[3]);
		return this;
	}

	/**
	 * @return the red channel of the color
	 */
	public int getRed() {
		return (rgba >> 24) & 0xFF;
	}

	/**
	 * @return the green channel of the color
	 */
	public int getGreen() {
		return (rgba >> 16) & 0xFF;
	}

	/**
	 * @return the blue channel of the color
	 */
	public int getBlue() {
		return (rgba >> 8) & 0xFF;
	}

	@Override
	public Integer getAlpha() {
		return rgba & 0xFF;
	}

	@Override
	public int getRGBAlpha() {
		return getAlpha();
	}

	/**
	 * @param red is the red channel in the color.
	 */
	public void setRed(int red) {
		red = Math.max(Math.min(red, 255), 0);
		rgba = (rgba & 0x00FFFFFF) | (red << 24);
	}

	/**
	 * @param green is the green channel in the color.
	 */
	public void setGreen(int green) {
		green = Math.max(Math.min(green, 255), 0);
		rgba = (rgba & 0xFF00FFFF) | (green << 16);
	}

	/**
	 * @param blue is the blue channel in the color.
	 */
	public void setBlue(int blue) {
		blue = Math.max(Math.min(blue, 255), 0);
		rgba = (rgba & 0xFFFF00FF) | (blue << 8);
	}

	@Override
	public void setRGBAlpha(int alpha) {
		setAlpha(alpha);
	}

	@Override
	public void setAlpha(Integer alpha) {
		alpha = Math.max(Math.min(alpha, 255), 0);
		rgba = (rgba & 0xFFFFFF00) | (alpha);
	}

	@Override
	public String toString() {
		return "RGB(R:%d,G:%d,B:%d%s)".formatted(getRed(),
													getGreen(),
													getBlue(),
													generateAlphaString());
	}
}
