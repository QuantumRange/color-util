package de.quantumrange.colorUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.regex.Pattern;

public interface Color<T> {

	/**
	 * It parse HEX Colors into RGBColors.
	 *
	 * @param str is the Color in Text form. Example: #00FF2F, ...
	 * @return itself
	 */
	T parse(String str);

	/**
	 * Tests if the text can be parsed.
	 *
	 * @param str is the Color in Text form. Example #00FF2F, ...
	 * @return is the text parsable.
	 */
	default boolean canParse(String str) {
		return pattern().matcher(str).find();
	}

	/**
	 * A getter for the Pattern.
	 *
	 * @see Pattern
	 * @return the pattern for this Color.
	 */
	Pattern pattern();

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

}
