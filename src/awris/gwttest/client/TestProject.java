package awris.gwttest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class TestProject implements EntryPoint {

	public void onModuleLoad() {
		EditorTest editor = new EditorTest();
		editor.setText("<i>Hello</i>");
		RootPanel.get().add(editor);
	}
	
}
