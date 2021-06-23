package de.quantumrange.colorUtil.impl;

import de.quantumrange.colorUtil.AlphaColor;
import de.quantumrange.colorUtil.Color;

import java.nio.ByteBuffer;

public class HSLColor implements Color<HSLColor>, AlphaColor<Float> {

	/**
	 * h Hue
	 * s Saturation
	 * l Lightness
	 * a Alpha
	 */
	private float h, s, l, a;

	public HSLColor(int h, float s, float l) {
		this.h = h;
		this.s = s;
		this.l = l;
		this.a = 1f;
	}

	public HSLColor(float h, float s, float l, float a) {
		this.h = h;
		this.s = s;
		this.l = l;
		this.a = a;
	}

	public HSLColor(Color<?> color) {
		this(color.toColor());
	}

	public HSLColor(java.awt.Color color) {
		fromColor(color);
	}

	@Override
	public java.awt.Color toColor() {
		float h = this.h;
		float s = this.s;
		float l = this.l;

		h = h % 360.0f;
		h /= 360f;

		float q;

		if (l < 0.5) q = l * (1 + s);
		else q = (l + s) - (s * l);

		float p = 2 * l - q;

		float r = Math.max(0, HueToRGB(p, q, h + (1.0f / 3.0f)));
		float g = Math.max(0, HueToRGB(p, q, h));
		float b = Math.max(0, HueToRGB(p, q, h - (1.0f / 3.0f)));

		return new java.awt.Color(Math.min(r, 1.0f), Math.min(g, 1.0f), Math.min(b, 1.0f), a);
	}

	private float HueToRGB(float p, float q, float h) {
		if (h < 0) h += 1;
		if (h > 1) h -= 1;
		if (6 * h < 1) return p + ((q - p) * 6 * h);
		if (2 * h < 1) return q;
		if (3 * h < 2) return p + ((q - p) * 6 * ((2.0f / 3.0f) - h));
		return p;
	}

	@Override
	public HSLColor fromColor(java.awt.Color color) {
		float r = color.getRed() / 255f;
		float g = color.getGreen() / 255f;
		float b = color.getBlue() / 255f;

		float max = Math.max(r, Math.max(g, b));
		float min = Math.min(r, Math.min(g, b));

		this.l = (max + min) / 2f;

		if (max == min) h = 0;
		else if (max == r) h = ((60 * (g - b) / (max - min) + 360)) % 360;
		else if (max == g) h = (60 * (b - r) / (max - min)) + 120;
		else if (max == b) h = (60 * (r - g) / (max - min)) + 240;

		l = (max + min) / 2;

		if (max == min) s = 0;
		else if (l <= .5f) s = (max - min) / (max + min);
		else s = (max - min) / (2 - max - min);
		setRGBAlpha(color.getAlpha());

		return this;
	}

	@Override
	public byte[] toBytes() {
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.putFloat(h).putFloat(s).putFloat(l).putFloat(a);
		return buffer.array();
	}

	/**
	 * Calculate Complementary color.
	 *
	 * @return the complementary color.
	 */
	public HSLColor getComplementary() {
		return new HSLColor((h + 180f) % 360f, s, l, a);
	}

	@Override
	public HSLColor fromBytes(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		h = buffer.getFloat();
		s = buffer.getFloat();
		l = buffer.getFloat();
		a = buffer.getFloat();
		return this;
	}

	public float getHue() {
		return h;
	}

	public void setHue(float h) {
		this.h = h;
	}

	public float getSaturation() {
		return s;
	}

	public void setSaturation(float s) {
		this.s = s;
	}

	public float getLightness() {
		return l;
	}

	public void setLightness(float l) {
		this.l = l;
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
		return "HSL(H:%f,S:%f,L:%f%s)"
				.formatted(h, s, l, generateAlphaString());
	}
}
