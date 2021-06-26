# color-util
[![Maven](https://github.com/QuantumRange/color-util/actions/workflows/maven.yml/badge.svg)](https://github.com/QuantumRange/color-util/actions/workflows/maven.yml)
</br>
Java Color Util

## Supported Color Types
All color classes have common features.
The following are the ways to create any color class:
**XXX**Color stands for any color coding. So for example: **RGB**Color or **HSL**Color.
```java
new XXXColor(/* A standard Java Color, for example: Color.WHITE or new Color(5,255,20) */);
new XXXColor(/* Any other Color Type. */);
new XXXColor().fromBytes(/* The bytes created by the same Color Type. */);
new XXXColor().fromColor(/* A standard Java Color */);
```
### RGB
#### Other ways to create the color
```java
new RGBColor();
new RGBColor(255, 52, 1);
new RGBColor(255, 52, 1, 25);
```
### CMYK
#### Other ways to create the color
```java
new CMYKColor();
new CMYKColor(0f, 0.46f, 0.65f, 0.42f);
```
### HSL
#### Other ways to create the color
### HSV
#### Other ways to create the color