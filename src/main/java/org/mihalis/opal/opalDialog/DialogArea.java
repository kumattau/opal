/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron at gmail dot com) - Initial implementation and API
 *******************************************************************************/
package org.mihalis.opal.opalDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.mihalis.opal.utils.SWTGraphicUtil;

/**
 * This abstract class if the mother of MessageArea and FooterArea classes
 */
abstract class DialogArea {
	private static final String MORE_DETAILS_IMAGE = "moreDetails.png";
	private static final String FEWER_DETAILS_IMAGE = "fewerDetails.png";
	private static final String WINDOWS_DEFAULT_FONT = "Segoe UI";
	private static final String MAC_OS_DEFAULT_FONT = "Lucida Grande";
	protected final Dialog parent;
	private boolean initialised;

	/**
	 * Constructor
	 * 
	 * @param parent parent dialog
	 */
	public DialogArea(final Dialog parent) {
		this.parent = parent;
	}

	/**
	 * Render the content of an area
	 */
	abstract void render();

	/**
	 * @return the initialised field
	 */
	boolean isInitialised() {
		return this.initialised;
	}

	/**
	 * @param initialised the initialised value to set
	 */
	void setInitialised(final boolean initialised) {
		this.initialised = initialised;
	}

	/**
	 * @return the normal font used by the dialog box
	 */
	protected Font getNormalFont() {
		if (SWTGraphicUtil.isMacOS()) {
			return getFont(MAC_OS_DEFAULT_FONT, 11, SWT.NONE);
		} else {
			return getFont(WINDOWS_DEFAULT_FONT, 9, SWT.NONE);
		}
	}

	/**
	 * @return the bigger font used by the dialog box
	 */
	protected Font getBiggerFont() {
		if (SWTGraphicUtil.isMacOS()) {
			return getFont(MAC_OS_DEFAULT_FONT, 13, SWT.NONE);
		} else {
			return getFont(WINDOWS_DEFAULT_FONT, 11, SWT.NONE);
		}
	}

	/**
	 * Build a font
	 * 
	 * @param name name of the font
	 * @param size size of the font
	 * @param style style of the font
	 * @return the font
	 */
	private Font getFont(final String name, final int size, final int style) {
		final Font font = new Font(Display.getCurrent(), name, size, style);
		this.parent.shell.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(final DisposeEvent e) {
				SWTGraphicUtil.safeDispose(font);
			}
		});
		return font;
	}

	/**
	 * @return the title's color (blue)
	 */
	protected Color getTitleColor() {
		final Color color = new Color(Display.getCurrent(), 35, 107, 178);
		SWTGraphicUtil.addDisposer(this.parent.shell, color);
		return color;
	}

	/**
	 * @return the grey color
	 */
	protected Color getGreyColor() {
		final Color color = new Color(Display.getCurrent(), 240, 240, 240);
		SWTGraphicUtil.addDisposer(this.parent.shell, color);
		return color;
	}

	/**
	 * @return the image "fewer details"
	 */
	protected Image getFewerDetailsImage() {
		return loadImage("images/" + FEWER_DETAILS_IMAGE);
	}

	/**
	 * @return the image "more details"
	 */
	protected Image getMoreDetailsImage() {
		return loadImage("images/" + MORE_DETAILS_IMAGE);
	}

	/**
	 * Loads an image
	 * 
	 * @param fileName file name of the image
	 * @return the image
	 */
	private Image loadImage(final String fileName) {
		final Image image = SWTGraphicUtil.createImageFromFile(fileName);
		SWTGraphicUtil.addDisposer(this.parent.shell, image);
		return image;
	}

}
