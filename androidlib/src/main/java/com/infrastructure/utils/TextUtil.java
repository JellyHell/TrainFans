package com.infrastructure.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.KeyEvent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class TextUtil
{

	int m_iTextPosX;
	int m_iTextPosY;
	int m_iTextWidth;
	int m_iTextHeight;
	int m_iFontHeight;
	int m_ipageLineNum;
	int m_iTextBGColor;
	int m_iTextColor;
	int m_iAlpha;
	int m_iRealLine;
	int m_iCurLine;

	String m_strText;
	Vector<String> m_String;
	Paint m_paint;
	int m_iTextSize;
	static int CRYPTKEY = 90;

	public static byte[] CancelHead(byte[] bytes)
	{
		if ((null == bytes) || (bytes.length < 8))
		{
			return null;
		}
		if (('f' == bytes[0]) && ('l' == bytes[1]) && ('y' == bytes[2]) && ('s' == bytes[3]) && ('t' == bytes[4]) && ('m' == bytes[5])
				&& ('!' == bytes[6]))
		{
			int iHeaderLength = 7;

			byte[] bodytemp = new byte[bytes.length - iHeaderLength];
			System.arraycopy(bytes, iHeaderLength, bodytemp, 0, bytes.length - iHeaderLength);
			return bodytemp;
		}
		else
		{
			return bytes;
		}
	}

	/**
	 * 判断字符串是否在一个数组中
	 * 
	 * @param str
	 * @param strs
	 *            数组
	 * @return -1标示不再 1标示在
	 */
	public static int innerStrings(String str, String[] strs)
	{
		for (int i = 0; i < strs.length; i++)
		{
			if (strs[i].equals(str))
			{
				return 1;
			}
		}
		return -1;
	}

	public static int innerIgnoreCaseStrings(String str, String[] strs)
	{
		for (int i = 0; i < strs.length; i++)
		{
			if (strs[i].equalsIgnoreCase(str))
			{
				return 1;
			}
		}
		return -1;
	}

	/**
	 * 压缩数据使用zlib压缩
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] compressData(byte[] bytes)
	{
		ByteArrayOutputStream bis = new ByteArrayOutputStream();
		try
		{
			// Compress the bytes 开始压缩数据,
			byte[] tempByte = new byte[100];
			int compressedDataLength = -1;
			Deflater compresser = new Deflater();
			compresser.setInput(bytes); // 要压缩的数据包
			compresser.finish(); // 完成,
			while (compressedDataLength != 0)
			{
				compressedDataLength = compresser.deflate(tempByte); // 压缩，返回的是数据包经过缩缩后的大小
				bis.write(tempByte, 0, compressedDataLength);
			}
		}
		catch (Exception ex)
		{
			// handle
			ex.printStackTrace();
		}
		return bis.toByteArray();
	}

	/**
	 * 解压数据使用zlib解压
	 * 
	 * @param bytes
	 * @return
	 * @throws DataFormatException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] decompressData(byte[] bytes) throws DataFormatException, IOException
	{
		ByteArrayOutputStream bis = new ByteArrayOutputStream();
		int resultLength = -1;
		try
		{ // Decompress the bytes // 开始解压
			Inflater decompresser = new Inflater();
			decompresser.setInput(bytes, 0, bytes.length); // 对byte[]进行解压，同时可以要解压的数据包中的某一段数据，就好像从zip中解压出某一个文件一样。
			byte[] result = new byte[1024];
			while (resultLength != 0)
			{
				resultLength = decompresser.inflate(result); // 返回的是解压后的的数据包大小，
				bis.write(result, 0, resultLength);
			}
			decompresser.end();
			result = null;
			decompresser = null;
		}
		catch (Exception ex)
		{
		}
		return bis.toByteArray();
	}

	/**
	 * 解密
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] decrypt(byte[] bytes)
	{
		int len = bytes.length;
		for (int i = 0; i < len; i++)
		{
			bytes[i] = (byte) (bytes[i] ^ CRYPTKEY);
		}
		return bytes;
	}

	/**
	 * 加密
	 * 
	 * @param bytes
	 *            要加密的对象字节
	 * @return
	 */
	public static byte[] ecrypt(byte[] bytes)
	{
		int len = bytes.length;
		for (int i = 0; i < len; i++)
		{
			bytes[i] = (byte) (bytes[i] ^ CRYPTKEY);
		}
		return bytes;
	}

	/**
	 * 先压缩后加密
	 * 
	 * @param bytes
	 *            需要操作的对象字节
	 * @return
	 */
	public static byte[] ecryptAfterCompressData(byte[] bytes)
	{
		return ecrypt(compressData(bytes));
	}

	/**
	 * 先解密后解压缩
	 * 
	 * @param bytes
	 *            需要操作的对象字节
	 * @return
	 * @throws Exception
	 */
	public static byte[] decompressAfterdecrypt(byte[] bytes) throws Exception
	{
		if (bytes == null || bytes.length == 0) return null;
		return decompressData(decrypt(bytes));
	}

	public TextUtil()
	{
		m_paint = new Paint();
		m_String = new Vector<String>();
	}

	public TextUtil(String strText, int x, int y, int w, int h, int bgcolor, int txetcolor, int a, int iTextSize)
	{
		m_paint = new Paint();
		m_String = new Vector<String>();

		m_strText = strText;

		m_iTextPosX = x;
		m_iTextPosY = y;
		m_iTextWidth = w;
		m_iTextHeight = h;

		m_iTextBGColor = bgcolor;
		m_iTextColor = txetcolor;

		m_iTextSize = iTextSize;

		m_iAlpha = a;

	}

	/**
	 * @param canvas
	 */
	public void DrawText(Canvas canvas)
	{
		for (int i = m_iCurLine, j = 0; i < m_iRealLine; i++, j++)
		{
			if (j > m_ipageLineNum)
			{
				break;
			}
			canvas.drawText((m_String.elementAt(i)), m_iTextPosX, m_iTextPosY + m_iFontHeight * j, m_paint);
		}
	}

	public void GetTextIfon()
	{
		char ch;
		int w = 0;
		int istart = 0;
		FontMetrics fm = m_paint.getFontMetrics();

		m_iFontHeight = (int) Math.ceil(fm.descent - fm.top) + 2;

		m_ipageLineNum = m_iTextHeight / m_iFontHeight;

		for (int i = 0; i < m_strText.length(); i++)
		{
			ch = m_strText.charAt(i);
			float[] widths = new float[1];
			String srt = String.valueOf(ch);
			m_paint.getTextWidths(srt, widths);

			if (ch == '\n')
			{
				m_iRealLine++;
				m_String.addElement(m_strText.substring(istart, i));
				istart = i + 1;
				w = 0;
			}
			else
			{
				w += (int) (Math.ceil(widths[0]));
				if (w > m_iTextWidth)
				{
					m_iRealLine++;
					m_String.addElement(m_strText.substring(istart, i));
					istart = i;
					i--;
					w = 0;
				}
				else
				{
					if (i == (m_strText.length() - 1))
					{
						m_iRealLine++;
						m_String.addElement(m_strText.substring(istart, m_strText.length()));
					}
				}
			}
		}
	}

	public void InitText(String strText, int x, int y, int w, int h, int bgcolor, int txetcolor, int a, int iTextSize)
	{
		m_iCurLine = 0;
		m_ipageLineNum = 0;
		m_iRealLine = 0;
		m_strText = "";
		m_iTextPosX = 0;
		m_iTextPosY = 0;
		m_iTextWidth = 0;
		m_iTextHeight = 0;
		m_iTextBGColor = 0;
		m_iTextColor = 0;
		m_iTextSize = 0;
		m_iAlpha = 0;

		m_String.clear();

		SetText(strText);
		SetRect(x, y, w, h);
		SetBGColor(bgcolor);
		SetTextColor(txetcolor);
		SetFontSize(iTextSize);
		SetAlpha(a);

		SetPaint();

		GetTextIfon();
	}

	/**
	 * 
	 * @param keyCode
	 * @param event
	 * @return
	 */
	public boolean KeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_DPAD_UP)
		{
			if (m_iCurLine > 0)
			{
				m_iCurLine--;
			}
		}
		else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
		{
			if ((m_iCurLine + m_ipageLineNum) < (m_iRealLine - 1))
			{
				m_iCurLine++;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param a
	 *            Alphaֵ
	 */
	public void SetAlpha(int a)
	{
		m_iAlpha = a;
	}

	/**
	 * 
	 * @param bgcolor
	 */
	public void SetBGColor(int bgcolor)
	{
		m_iTextBGColor = bgcolor;
	}

	/**
	 * 
	 * @param iTextSize
	 */
	public void SetFontSize(int iTextSize)
	{
		m_iTextSize = iTextSize;
	}

	public void SetPaint()
	{
		m_paint.setARGB(m_iAlpha, Color.red(m_iTextColor), Color.green(m_iTextColor), Color.blue(m_iTextColor));
		m_paint.setTextSize(m_iTextSize);
	}

	/**
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void SetRect(int x, int y, int w, int h)
	{
		m_iTextPosX = x;
		m_iTextPosY = y;
		m_iTextWidth = w;
		m_iTextHeight = h;
	}

	/**
	 * @param strText
	 */
	public void SetText(String strText)
	{
		m_strText = strText;
	}

	/**
	 * @param txetcolor
	 */
	public void SetTextColor(int txetcolor)
	{
		m_iTextColor = txetcolor;
	}

	public static boolean isEmpty(String name)
	{
		if (name == null || name.trim().length() == 0)
		{
			return true;
		}
		return false;
	}

	/**
	 * 把回车换行替换成<br>
	 * 
	 * @param source
	 *            待处理的源字串
	 * @return 已将"\r\n"替换成"<br>
	 *         "的字串
	 */
	public static String nl2br(String source)
	{
		return replace("\n", "<br>", source);
	}

	/**
	 * 把haystack中的needle替换成str
	 * 
	 * @param haystack
	 *            待处理的源字串
	 * @param needle
	 *            要被取代的字串
	 * @param str
	 *            替换成str字串
	 * @return 已处理的字串
	 */
	private static String replace(String needle, String str, String haystack)
	{
		if (haystack == null)
		{
			return null;
		}

		int i = 0;

		if ((i = haystack.indexOf(needle, i)) >= 0)
		{
			char[] line = haystack.toCharArray(); // 把字串类转成字符数组
			char[] newString = str.toCharArray();

			int needleLength = needle.length();

			StringBuffer buf = new StringBuffer(line.length);
			buf.append(line, 0, i).append(newString);

			i += needleLength;

			int j = i;

			while ((i = haystack.indexOf(needle, i)) > 0)
			{
				buf.append(line, j, i - j).append(newString);
				i += needleLength;
				j = i;
			}
			buf.append(line, j, line.length - j);
			return buf.toString();
		}

		return haystack;
	}

	/**
	 * 判断字符串中某个字符串出现的次数
	 * 
	 * @param str1
	 * @param str2
	 */
	private static int counter = 0;

	public static int countStr(String str1, String str2)
	{
		if (str1.indexOf(str2) == -1)
		{
			return 0;
		}
		else if (str1.indexOf(str2) != -1)
		{
			counter++;
			countStr(str1.substring(str1.indexOf(str2) + str2.length()), str2);
			return counter;
		}
		return 0;
	}

}