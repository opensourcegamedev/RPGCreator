/*
* Coypright (c) 2015 - 2017 Justin Kuenzel
* Apache 2.0 License
*/

package com.jukusoft.rpgcreator.engine.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 26.08.2015.
 */
public class ByteUtils
{
	/**
	 * @param b
	 * @return int
	 */
	public static int byteToInt(byte b)
	{
		// http://openbook.rheinwerk-verlag.de/javainsel/javainsel_18_001.html
		return b & 0xFF;
	}

	/**
	 * @param i
	 * @return int
	 */
	public static byte intToByte(int i)
	{
		// Eine explizite Typanpassung mit (int)(b & 0xff) ist nicht noetig, da
		// der Compiler bei der arithmetischen Und-Operation automatisch in ein
		// int konvertiert.
		return (byte) i;
	}

	/**
	 * convert long to byte array
	 *
	 * @param x
	 * @return byte array
	 */
	public static byte[] longToBytes(long x)
	{
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(x);

		return buffer.array();
	}

	/**
	 * convert byte array to long
	 *
	 * @param bytes
	 * @return long
	 */
	public static long bytesToLong(byte[] bytes)
	{
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes);
		buffer.flip();

		return buffer.getLong();
	}

	/**
	 * convert integer to byte array
	 *
	 * @param x
	 * @return byte array
	 */
	public static byte[] intToBytes(int x)
	{
		ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
		byteBuffer.putInt(x);
		byteBuffer.flip();

		return byteBuffer.array();
	}

	/**
	 * convert byte array to integer
	 *
	 * @param bytes
	 *            byte array
	 * @return x
	 */
	public static int bytesToInteger(byte[] bytes)
	{
		ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
		byteBuffer.put(bytes);
		byteBuffer.flip();

		return byteBuffer.getInt();
	}

	/**
	* converts 4 bytes to integer
	 *
	 * @link http://stackoverflow.com/questions/2840190/java-convert-4-bytes-to-int
	*/
	public static int byteToInteger (byte b1, byte b2, byte b3, byte b4) {
		return ((0xFF & b1) << 24) | ((0xFF & b2) << 16) |
				((0xFF & b3) << 8) | (0xFF & b4);
	}

	/**
	 * convert char to byte
	 *
	 * @param c
	 * @return byte1
	 */
	public static byte charToByte(char c)
	{
		return (byte) c;
	}

	/**
	 * convert byte to char
	 *
	 * @param b
	 * @return c
	 */
	public static char byteToChar(byte b)
	{
		return (char) (b & 0x00FF);
	}

	/**
	 * convert string to byte array
	 *
	 * @param str
	 *            string
	 * @param charset
	 *            charset
	 *
	 * @return byte array
	 */
	public static byte[] getBytesFromString(String str, Charset charset)
	{
		return str.getBytes(charset);
	}

	/**
	 * convert string to byte array
	 *
	 * @param str
	 *
	 * @return byte array
	 */
	public static byte[] getBytesFromString(String str)
	{
		return str.getBytes(StandardCharsets.UTF_8);
	}

	/**
	 * convert byte array to string
	 *
	 * @param bytes
	 *            byte array
	 * @param charset
	 *            charset
	 *
	 * @return string
	 */
	public static String getStringFromBytes(byte[] bytes, Charset charset)
	{
		return new String(bytes, charset);
	}

	/**
	 * convert byte array to string
	 *
	 * @param bytes
	 *            byte array
	 *
	 * @return string
	 */
	public static String getStringFromBytes(byte[] bytes)
	{
		return new String(bytes, StandardCharsets.UTF_8);
	}

	/**
	 * return the byte on the position of the byte buffer
	 *
	 * @param byteBuffer
	 *            byte buffer
	 * @param pos
	 *            position
	 *
	 * @return byte
	 */
	public static byte getByteOnPosition(ByteBuffer byteBuffer, int pos)
	{
		return byteBuffer.get(pos);
	}

	/**
	 * return the bit on the position of the byte data
	 *
	 * @param data
	 *            byte array
	 * @param pos
	 *            position
	 *
	 * @return bit as integer
	 */
	public static int getBit(byte data[], int pos)
	{
		int posByte = pos / 8;
		int posBit = pos % 8;
		byte valByte = data[posByte];
		int valInt = valByte >> (8 - (posBit + 1)) & 0x0001;

		return valInt;
	}

	/**
	 * combines 2 integers to 1 long for optimization of databases and so on.
	 *
	 * @param x
	 *            integer1
	 * @param y
	 *            integer2
	 *
	 * @return long
	 */
	public static long getLongFromIntegers(int x, int y)
	{
		/*
		 * You can save numbers from -2. 147. 483. 648 to 2. 147. 483. 648 in 1
		 * integer A long is combined with 2 integers
		 */

		return (((long) x) << 32) | (y & 0xffffffffL);
	}

	/**
	 * If 2 integers are combined to 1 long, this method can return the first
	 * integer
	 *
	 * @param l
	 *            long
	 *
	 * @return long
	 */
	public static int getFirstIntegerFromLong(long l)
	{
		return (int) (l >> 32);
	}

	/**
	 * If 2 integers are combined to 1 long, this method can return the second
	 * integer
	 *
	 * @param l
	 *            long
	 *
	 * @return integer
	 */
	public static int getSecondIntegerFromLong(long l)
	{
		return (int) l;
	}

	/**
	 * Optimization for multiplization an long with 2.
	 *
	 * For example: You can use Long res = 20l * 2, but this isnt really fast.
	 * An faster way is to use bit shifting. All bits are shifted 1 position to
	 * the left.
	 *
	 * @param l
	 * @return Long
	 */
	public static Long getTwice(Long l)
	{
		return Long.rotateLeft(l, 1);
	}

	/**
	 * Optimization for multiplization an long with 2.
	 *
	 * For example: You can use Long res = 20l * 2, but this isnt really fast.
	 * An faster way is to use bit shifting. All bits are shifted 1 position to
	 * the left.
	 *
	 * @param l
	 * @return Long
	 */
	public static Long mul2(Long l)
	{
		return Long.rotateLeft(l, 1);
	}

	/**
	 * Optimization for multiplization an long with 2.
	 *
	 * For example: You can use Long res = 20l * 4, but this isnt really fast.
	 * An faster way is to use bit shifting. All bits are shifted 2 position to
	 * the left.
	 *
	 * @param l
	 * @return Long
	 */
	public static Long mul4(Long l)
	{
		return Long.rotateLeft(l, 2);
	}

	/**
	 * set an bit on an integer
	 *
	 * @param n
	 *            number
	 * @param pos
	 *            position
	 *
	 * @return i
	 *
	 * @link http://openbook.rheinwerk-verlag.de/javainsel/javainsel_18_001.html
	 */
	static int setBit(int n, int pos)
	{
		return n | (1 << pos);
	}

	/**
	 * remove bit of an integer
	 *
	 * @param n
	 *            number
	 * @param pos
	 *            position
	 *
	 * @return i
	 *
	 * @link http://openbook.rheinwerk-verlag.de/javainsel/javainsel_18_001.html
	 */
	static int clearBit(int n, int pos)
	{
		return n & ~(1 << pos);
	}

	/**
	 * flips an bit
	 *
	 * @param n
	 *            number
	 * @param pos
	 *            position
	 *
	 * @return i
	 *
	 * @link http://openbook.rheinwerk-verlag.de/javainsel/javainsel_18_001.html
	 */
	static int flipBit(int n, int pos)
	{
		return n ^ (1 << pos);
	}

	/**
	 * check, if bit is set.
	 *
	 * @param n
	 *            number
	 * @param pos
	 *            position
	 *
	 * @return b
	 *
	 * @link http://openbook.rheinwerk-verlag.de/javainsel/javainsel_18_001.html
	 */
	static boolean testBit(int n, int pos)
	{
		int mask = 1 << pos;
		return (n & mask) == mask;
	}
}
