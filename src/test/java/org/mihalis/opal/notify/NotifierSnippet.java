/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron at gmail dot com) - initial API and implementation 
 *******************************************************************************/
package org.mihalis.opal.notify;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.mihalis.opal.notify.NotifierColorsFactory.NotifierTheme;

/**
 * This snippet demonstrates the notifier widget
 * 
 */
public class NotifierSnippet {
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Notifier Snippet");
		shell.setSize(200, 200);
		shell.setLayout(new FillLayout(SWT.VERTICAL));

		final int[] counter = new int[1];
		counter[0] = 0;

		// Yellow theme (default)
		final Button testerYellow = new Button(shell, SWT.PUSH);
		testerYellow.setText("Push me [Yellow theme]!");
		testerYellow.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				Notifier.notify("New Mail message", "Laurent CARON (lcaron@...)<br/><br/>Test message #" + counter[0] + "...");
				counter[0]++;
			}

		});

		// Blue theme
		final Button testerBlue = new Button(shell, SWT.PUSH);
		testerBlue.setText("Push me [Blue theme]!");
		testerBlue.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				Notifier.notify("New Mail message", "Laurent CARON (lcaron@...)<br/><br/>Test message #" + counter[0] + "...", NotifierTheme.BLUE_THEME);
				counter[0]++;
			}

		});

		// Grey theme
		final Button testerGrey = new Button(shell, SWT.PUSH);
		testerGrey.setText("Push me [Gray theme]!");
		testerGrey.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				Notifier.notify("New Mail message", "Laurent CARON (lcaron@...)<br/><br/>Test message #" + counter[0] + "...", NotifierTheme.GRAY_THEME);
				counter[0]++;
			}

		});

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
