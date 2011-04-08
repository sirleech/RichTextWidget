package awris.gwttest.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.logical.shared.InitializeEvent;

public class EditorTest extends Composite implements HasText {
	interface EditorTestUiBinder extends UiBinder<Widget, EditorTest> { }
	private static EditorTestUiBinder uiBinder = GWT.create(EditorTestUiBinder.class);

	@UiField
	RichTextArea txtEditor;
	
	public EditorTest() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("txtEditor")
	void onTxtEditorInitialize(InitializeEvent event) {
		// Style sheet
		String styleSheet =
//			"h1 {" +
//			" color: red;" +
//			"}" +
//			"h2 {" +
//			" color: green;" +
//			"}" +
//			"h3 {" +
//			" color: blue;" +
//			"}" +
			"table {" +
			" border: 1px solid black;" +
			"}" +
			"tr {" +
			" border: 1px  solid black;" +
			"}" +
			"td {" +
			" border: 1px  solid black;" +
			"}";
//		
		// Adjust style
		txtEditor.setHTML("Insert line item notes here. You can use formatting commands like ctrl+b and ctrl+i.");
		IFrameElement frame = IFrameElement.as(txtEditor.getElement());
		Document document = frame.getContentDocument();
		BodyElement body = document.getBody();
		HeadElement head = HeadElement.as(Element.as(body.getPreviousSibling()));
		StyleElement style = document.createStyleElement();
		style.setInnerText(styleSheet);
		head.appendChild(style);
	}

	@UiHandler("btnBold")
	void onBoldClick(ClickEvent e) {
		txtEditor.getFormatter().toggleBold();
	}
	
	@UiHandler("btnItalics")
	void onItalicsClick(ClickEvent e) {
		txtEditor.getFormatter().toggleItalic();
	}
	
	@UiHandler("btnUnderline")
	void onUnderlineClick(ClickEvent e) {
		txtEditor.getFormatter().toggleUnderline();
	}
	
	@UiHandler("btnStrikeThrough")
	void onStrikeThroughClick(ClickEvent e) {
		txtEditor.getFormatter().toggleStrikethrough();
	}
	
	@UiHandler("btnRemoveFormatting")
	void onRemoveFormattingClick(ClickEvent e) {
		txtEditor.getFormatter().removeFormat();
	}
	
	@UiHandler("btnUnorderedList")
	void onUnorderedListClick(ClickEvent e) {
		txtEditor.getFormatter().insertUnorderedList();
	}
	
	@UiHandler("btnOrderedList")
	void onOrderedListClick(ClickEvent e) {
		txtEditor.getFormatter().insertOrderedList();
	}

	@UiHandler("btnUndo")
	void onUndoClick(ClickEvent e) {
		txtEditor.getFormatter().undo();
	}
	
	@UiHandler("btnRedo")
	void onRedoClick(ClickEvent e) {
		txtEditor.getFormatter().redo();
	}
	
	@UiHandler("btnHeading1")
	void onHeading1Click(ClickEvent e) {
		txtEditor.getFormatter().insertHTML("<h1></h1>");
	}
	
	@UiHandler("btnHeading2")
	void onHeading2Click(ClickEvent e) {
		txtEditor.getFormatter().insertHTML("<h2></h2>");
	}
	
	@UiHandler("btnHeading3")
	void onHeading3Click(ClickEvent e) {
		txtEditor.getFormatter().insertHTML("<h3></h3>");
	}
	
	@UiHandler("btnAddTable")
	void onAddTableClick(ClickEvent e) {
		txtEditor.getFormatter().insertHTML("<table><tr><td>Header 1</td><td>Header 2</td></tr><tr><td></td><td></td></tr></table>");
	}
	
	String url = "http://www.bom.gov.au/images/ui/bom_logo.gif";
	@UiHandler("btnAddImage")
	void onAddImageClick(ClickEvent e) {
		txtEditor.getExtendedFormatter().insertImage(url);
	}
	
	public void setText(String text) {
		txtEditor.setHTML(text);
	}

	public String getText() {
		return txtEditor.getHTML();
	}

	@UiHandler("txtEditor")
	void onTxtEditorKeyPress(KeyPressEvent event) {
		if (! event.isControlKeyDown() ) {
			return;
		}
		boolean isHandled=true;
		switch ( Character.toUpperCase(event.getCharCode()) ) {
			case 'A':
				txtEditor.getFormatter().selectAll();
				break;
			case 'B':
				onBoldClick(null);
				break;
			case 'U':
				onUnderlineClick(null);
				break;
			case 'I':
				onItalicsClick(null);
				break;
			case 'T':
				onStrikeThroughClick(null);
				break;
			case 'Y':
				onRedoClick(null);
				break;
			case 'Z':
				onUndoClick(null);
				break;
			case '1':
				onHeading1Click(null);
				break;
			case '2':
				onHeading2Click(null);
				break;
			case '3':
				onHeading3Click(null);
				break;
			default:
				isHandled = false;
		}
		if ( isHandled ) {
			event.stopPropagation();
			event.preventDefault();
		}
	}

}
