package de.quantumrange.colorUtil.impl;

import de.quantumrange.colorUtil.Color;

import java.nio.ByteBuffer;
import java.util.regex.Pattern;

public class RGBColor implements Color<RGBColor> {

	private static final Pattern PATTERN = Pattern.compile("#[\\d]{2}[\\d]{2}[\\d]{2}");
	private int rgb;

	public RGBColor(java.awt.Color color) {
		this.rgb = toInt(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}

	public RGBColor(Color<?> color) {
		this(color.toColor());
	}

	public RGBColor(int r, int g, int b) {
		this(r, g, b, 255);
	}

	public RGBColor(int r, int g, int b, int a) {
		this.rgb = toInt(r, g, b, a);
	}

	private int toInt(int r, int g, int b, int a) {
		r = Math.max(Math.min(r, 255), 0);
		g = Math.max(Math.min(g, 255), 0);
		b = Math.max(Math.min(b, 255), 0);
		a = Math.max(Math.min(a, 255), 0);

		return /*0x00000000 |*/ (r << 24) | (g << 16) | (b << 8) | (a);
	}

	@Override
	public RGBColor parse(String str) {
		int r = Integer.parseInt(str.substring(0, 2), 16);
		int g = Integer.parseInt(str.substring(2, 4), 16);
		int b = Integer.parseInt(str.substring(4, 6), 16);
		int a = str.length() == 8 ? Integer.parseInt(str.substring(6, 8), 16) : 255;

		this.rgb = toInt(r, g, b, a);
		return this;
	}

	@Override
	public Pattern pattern() {
		return PATTERN;
	}

	@Override
	public java.awt.Color toColor() {
		return new java.awt.Color(rgb);
	}

	@Override
	public RGBColor fromColor(java.awt.Color color) {
		this.rgb = color.getRGB();
		return this;
	}

	@Override
	public byte[] toBytes() {
		return new byte[] {(byte) getRed(), (byte) getGreen(), (byte) getBlue(), (byte) getAlpha()};
	}

	@Override
	public RGBColor fromBytes(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		rgb = buffer.getInt();
		return this;
	}

	/**
	 * @return the red value of the color
	 */
	public int getRed() {
		return (rgb >> 24) & 0xFF;
	}

	/**
	 * @return the green value of the color
	 */
	public int getGreen() {
		return (rgb >> 16) & 0xFF;
	}

	/**
	 * @return the blue value of the color
	 */
	public int getBlue() {
		return (rgb >> 8) & 0xFF;
	}

	/**
	 * @return the alpha value of the color
	 */
	public int getAlpha() {
		return rgb & 0xFF;
	}

	/**
	 * @param red is the red value in the color.
	 * @return itself
	 */
	public RGBColor setRed(int red) {
		red = Math.max(Math.min(red, 255), 0);
		rgb = (rgb & 0x00FFFFFF) | (red << 24);
		return this;
	}

	/**
	 * @param green is the green value in the color.
	 * @return itself
	 */
	public RGBColor setGreen(int green) {
		green = Math.max(Math.min(green, 255), 0);
		rgb = (rgb & 0xFF00FFFF) | (green << 16);
		return this;
	}

	/**
	 * @param blue is the blue value in the color.
	 * @return itself
	 */
	public RGBColor setBlue(int blue) {
		blue = Math.max(Math.min(blue, 255), 0);
		rgb = (rgb & 0xFFFF00FF) | (blue << 8);
		return this;
	}

	/**
	 * @param alpha is the alpha value in the color.
	 * @return itself
	 */
	public RGBColor setAlpha(int alpha) {
		alpha = Math.max(Math.min(alpha, 255), 0);
		rgb = (rgb & 0xFFFFFF00) | (alpha);
		return this;
	}

	/**
	 * @return
	 */
	public int getRGB() {
		return rgb;
	}

	@Override
	public String toString() {
		return "RGB(R:%d,G:%d,B:%d%s)".formatted(getRed(),
													getGreen(),
													getBlue(),
													getAlpha() == 255 ? "" : ",A:%d".formatted(getAlpha()));
	}
}
