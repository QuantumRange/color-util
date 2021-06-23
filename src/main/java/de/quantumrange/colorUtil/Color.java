package de.quantumrange.colorUtil;

import de.quantumrange.colorUtil.impl.HSLColor;
import de.quantumrange.colorUtil.impl.RGBColor;

public interface Color<T> {

	/**
	 * Converts the Color to the Java standard {@link java.awt.Color} implementation.
	 *
	 * @see java.awt.Color
	 * @return the Color as Java standard {@link java.awt.Color} implementation.
	 */
	java.awt.Color toColor();

	/**
	 * Getting Color from Java standard {@link java.awt.Color} implementation.
	 *
	 * @param color the Java standard {@link java.awt.Color} implementation.
	 * @return itself
	 */
	T fromColor(java.awt.Color color);

	/**
	 * Compress the Color into bytes.
	 *
	 * @return the Color represented as bytes.
	 */
	byte[] toBytes();

	/**
	 * Read Color from Bytes
	 *
	 * @param bytes are the color in Bytes.
	 * @return itself
	 */
	T fromBytes(byte[] bytes);

	/**
	 * Calculate the Hex code für the color.
	 *
	 * @return the Color as Hex
	 */
	default String toHex() {
		RGBColor c = new RGBColor(this);

//		return "#%2s%2s%2s%s"
//				.formatted(
//						Integer.toString(c.getRed(), 16),
//						Integer.toString(c.getGreen(), 16),
//						Integer.toString(c.getBlue(), 16),
//						c.generateHexAlphaString()
//				);
		return "#%02x%02x%02x%s"
				.formatted(
						c.getRed(),
						c.getGreen(),
						c.getBlue(),
						c.generateHexAlphaString()
				);
	}

}
