package com.vendenet.utilidades;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.vendenet.utilidades.constantes.ConstantesVendenet;

public class ImageUtil {
	public static boolean resize(File savedFile, int newX, int newY,
			String extension) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(savedFile);
			if (img != null) {
				int x = img.getWidth();
				int y = img.getHeight();
				float relacionX = new Float(newX * 100) / x;
				float relacionY = new Float(newY * 100) / y;
				float diferenciaX = x - newX;
				float diferenciaY = y - newY;
				int nuevoX = y;
				int nuevoY = newY;
				if (relacionX <= relacionY) {
					if (diferenciaX > 0) {
						nuevoX = new Float(x - (diferenciaX)).intValue();
						nuevoY = new Float(y - (y * diferenciaX / x))
								.intValue();
					} else {
						nuevoX = x;
						nuevoY = y;
					}
				} else {
					if (diferenciaY > 0) {
						nuevoX = new Float(x - (x * diferenciaY / y))
								.intValue();
						nuevoY = new Float(y - (diferenciaY)).intValue();
					} else {
						nuevoX = x;
						nuevoY = y;
					}
				}
				ImageIO.write(getScaledInstance(img,(nuevoX > 477 ? 477: nuevoX),nuevoY,RenderingHints.VALUE_INTERPOLATION_BILINEAR,true), extension, savedFile);
				return true;
			}
		} catch (IOException e) {
		}
		return false;

	}

	public static boolean createImageSmall(File savedFile, File savedFilePeque,
			String extension) {
		BufferedImage img = null;
		int newX = ConstantesVendenet.TamanyoXFotoPeque;
		int newY = ConstantesVendenet.TamanyoYFotoPeque;
		try {
			img = ImageIO.read(savedFile);
			if (img != null) {
				int x = img.getWidth();
				int y = img.getHeight();
				float relacionX = new Float(newX * 100) / x;
				float relacionY = new Float(newY * 100) / y;
				float diferenciaX = x - newX;
				float diferenciaY = y - newY;
				int nuevoX = y;
				int nuevoY = newY;
				if (relacionX <= relacionY) {
					if (diferenciaX > 0) {
						nuevoX = new Float(x - (diferenciaX)).intValue();
						nuevoY = new Float(y - (y * diferenciaX / x))
								.intValue();
					} else {
						nuevoX = x;
						nuevoY = y;
					}
				} else {
					if (diferenciaY > 0) {
						nuevoX = new Float(x - (x * diferenciaY / y))
								.intValue();
						nuevoY = new Float(y - (diferenciaY)).intValue();
					} else {
						nuevoX = x;
						nuevoY = y;
					}
				}
				BufferedImage dimg = new BufferedImage(nuevoX, nuevoY, img
						.getType());
				Graphics2D g = dimg.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g.drawImage(img, 0, 0, nuevoX, nuevoY, 0, 0, x, y, null);
				g.dispose();
				ImageIO.write(dimg, extension, savedFilePeque);
				return true;
			}
		} catch (IOException e) {
		}
		return false;

	}

	public static boolean createLinkImageSmall(File savedFile,
			File savedFileEnlace, String ultimoToken) {
		BufferedImage img = null;
		int newX = ConstantesVendenet.TamanyoXFotoPeque;
		int newY = ConstantesVendenet.TamanyoYFotoPeque;
		try {
			img = ImageIO.read(savedFile);
			if (img != null) {
				int x = img.getWidth();
				int y = img.getHeight();
				float relacionX = new Float(newX * 100) / x;
				float relacionY = new Float(newY * 100) / y;
				float diferenciaX = x - newX;
				float diferenciaY = y - newY;
				int nuevoX = y;
				int nuevoY = newY;
				if (relacionX <= relacionY) {
					if (diferenciaX > 0) {
						nuevoX = new Float(x - (diferenciaX)).intValue();
						nuevoY = new Float(y - (y * diferenciaX / x))
								.intValue();
					} else {
						nuevoX = x;
						nuevoY = y;
					}
				} else {
					if (diferenciaY > 0) {
						nuevoX = new Float(x - (x * diferenciaY / y))
								.intValue();
						nuevoY = new Float(y - (diferenciaY)).intValue();
					} else {
						nuevoX = x;
						nuevoY = y;
					}
				}
				BufferedImage dimg = new BufferedImage(nuevoX, nuevoY, img
						.getType());
				Graphics2D g = dimg.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g.drawImage(img, 0, 0, nuevoX, nuevoY, 0, 0, x, y, null);
				g.dispose();
				ImageIO.write(dimg, ultimoToken, savedFileEnlace);
				return true;
			}
		} catch (IOException e) {
		}
		return false;

	}

	public static boolean createImage(File savedFile, File newSavedFile,
			String extension, int newX, int newY) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(savedFile);
			if (img != null) {
				int x = img.getWidth();
				int y = img.getHeight();
				float relacionX = new Float(newX * 100) / x;
				float relacionY = new Float(newY * 100) / y;
				float diferenciaX = x - newX;
				float diferenciaY = y - newY;
				int nuevoX = y;
				int nuevoY = newY;
				if (relacionX <= relacionY) {
					if (diferenciaX > 0) {
						nuevoX = new Float(x - (diferenciaX)).intValue();
						nuevoY = new Float(y - (y * diferenciaX / x))
								.intValue();
					} else {
						nuevoX = x;
						nuevoY = y;
					}
				} else {
					if (diferenciaY > 0) {
						nuevoX = new Float(x - (x * diferenciaY / y))
								.intValue();
						nuevoY = new Float(y - (diferenciaY)).intValue();
					} else {
						nuevoX = x;
						nuevoY = y;
					}
				}
				/*BufferedImage dimg = new BufferedImage(nuevoX, nuevoY, img
						.getType());*/
				/*Graphics2D g = dimg.createGraphics();
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BICUBIC);
				g.drawImage(img, 0, 0, nuevoX, nuevoY, 0, 0, x, y, null);
				g.dispose();
				ImageIO.write(dimg, extension, newSavedFile);*/
				ImageIO.write(getScaledInstance(img,nuevoX,nuevoY,RenderingHints.VALUE_INTERPOLATION_BILINEAR,true), extension, newSavedFile);
				return true;
		}
		} catch (IOException e) {
		}
		return false;
	}

	static public BufferedImage getScaledInstance(BufferedImage img, int targetWidth,
			int targetHeight, Object hint, boolean higherQuality) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage) img;
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}
		do {
			if (higherQuality && w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}
			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();
			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}

}
