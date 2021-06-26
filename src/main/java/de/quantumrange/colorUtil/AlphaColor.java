package de.quantumrange.colorUtil;

public interface AlphaColor<T extends Number> {

	/**
	 * Get the alpha channel of this color.
	 *
	 * @return alpha channel
	 */
	T getAlpha();

	/**
	 * Set the alpha channel of this color.
	 *
	 * @param alpha channel value
	 */
	void setAlpha(T alpha);

	/**
	 * Get the alpha channel of this color in RGB like type (between 0-255).
	 *
	 * @return alpha channel (between 0-255)
	 */
	int getRGBAlpha();

	/**
	 * Set the alpha channel of this color in RGB like type (between 0-255).
	 *
	 * @param alpha alpha channel value  (between 0-255)
	 */
	void setRGBAlpha(int alpha);

	/**
	 * Generate String for toString() methods for the Color.
	 *
	 * @return nothing if alpha is 255 (max) and alpha value if is it not 255.
	 */
	default String generateAlphaString() {
		if (getRGBAlpha() == 255) return "";
		else return ",A:%s".formatted(getAlpha());
	}

	/**
	 * Generate String for toHex() methods for {@link Color}
	 *
	 * @return nothing if alpha is 255 (max) and alpha value if is it not 255.
	 */
	default String generateHexAlphaString() {
		if (getRGBAlpha() == 255) return "";
		else return "%02x".formatted(getRGBAlpha());
	}

}
