/* 
 * Copyright (C) 2009 - 2010 Pierre Tholence
 *
 * This file is part of FirePath
 *
 * FirePath is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FirePath is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FirePath.  If not, see <http://www.gnu.org/licenses/>.
 */

FBL.ns(function() { with (FBL) {
// ************************************************************************************************
// Constants

const panelName = "firepath";

const prefRegExp = new RegExp(panelName + "\.(.*)");

const defaultTimePeriod = 100;

const xhtmlNS = 'http://www.w3.org/1999/xhtml';

// ************************************************************************************************
// XPath Tokens from XPath V1 W3C Recommendation on http://www.w3.org/TR/xpath

var BaseChar = "[\u0041-\u005A]|[\u0061-\u007A]|[\u00C0-\u00D6]|[\u00D8-\u00F6]|[\u00F8-\u00FF]|[\u0100-\u0131]|" +
		"[\u0134-\u013E]|[\u0141-\u0148]|[\u014A-\u017E]|[\u0180-\u01C3]|[\u01CD-\u01F0]|[\u01F4-\u01F5]|[\u01FA-\u0217]|" +
		"[\u0250-\u02A8]|[\u02BB-\u02C1]|\u0386|[\u0388-\u038A]|\u038C|[\u038E-\u03A1]|[\u03A3-\u03CE]|[\u03D0-\u03D6]|" +
		"\u03DA|\u03DC|\u03DE|\u03E0|[\u03E2-\u03F3]|[\u0401-\u040C]|[\u040E-\u044F]|[\u0451-\u045C]|[\u045E-\u0481]|" +
		"[\u0490-\u04C4]|[\u04C7-\u04C8]|[\u04CB-\u04CC]|[\u04D0-\u04EB]|[\u04EE-\u04F5]|[\u04F8-\u04F9]|[\u0531-\u0556]|" +
		"\u0559|[\u0561-\u0586]|[\u05D0-\u05EA]|[\u05F0-\u05F2]|[\u0621-\u063A]|[\u0641-\u064A]|[\u0671-\u06B7]|" +
		"[\u06BA-\u06BE]|[\u06C0-\u06CE]|[\u06D0-\u06D3]|\u06D5|[\u06E5-\u06E6]|[\u0905-\u0939]|\u093D|[\u0958-\u0961]|" +
		"[\u0985-\u098C]|[\u098F-\u0990]|[\u0993-\u09A8]|[\u09AA-\u09B0]|\u09B2|[\u09B6-\u09B9]|[\u09DC-\u09DD]|" +
		"[\u09DF-\u09E1]|[\u09F0-\u09F1]|[\u0A05-\u0A0A]|[\u0A0F-\u0A10]|[\u0A13-\u0A28]|[\u0A2A-\u0A30]|[\u0A32-\u0A33]|" +
		"[\u0A35-\u0A36]|[\u0A38-\u0A39]|[\u0A59-\u0A5C]|\u0A5E|[\u0A72-\u0A74]|[\u0A85-\u0A8B]|\u0A8D|[\u0A8F-\u0A91]|" +
		"[\u0A93-\u0AA8]|[\u0AAA-\u0AB0]|[\u0AB2-\u0AB3]|[\u0AB5-\u0AB9]|\u0ABD|\u0AE0|[\u0B05-\u0B0C]|[\u0B0F-\u0B10]|" +
		"[\u0B13-\u0B28]|[\u0B2A-\u0B30]|[\u0B32-\u0B33]|[\u0B36-\u0B39]|\u0B3D|[\u0B5C-\u0B5D]|[\u0B5F-\u0B61]|" +
		"[\u0B85-\u0B8A]|[\u0B8E-\u0B90]|[\u0B92-\u0B95]|[\u0B99-\u0B9A]|\u0B9C|[\u0B9E-\u0B9F]|[\u0BA3-\u0BA4]|" +
		"[\u0BA8-\u0BAA]|[\u0BAE-\u0BB5]|[\u0BB7-\u0BB9]|[\u0C05-\u0C0C]|[\u0C0E-\u0C10]|[\u0C12-\u0C28]|[\u0C2A-\u0C33]|" +
		"[\u0C35-\u0C39]|[\u0C60-\u0C61]|[\u0C85-\u0C8C]|[\u0C8E-\u0C90]|[\u0C92-\u0CA8]|[\u0CAA-\u0CB3]|[\u0CB5-\u0CB9]|" +
		"\u0CDE|[\u0CE0-\u0CE1]|[\u0D05-\u0D0C]|[\u0D0E-\u0D10]|[\u0D12-\u0D28]|[\u0D2A-\u0D39]|[\u0D60-\u0D61]|" +
		"[\u0E01-\u0E2E]|\u0E30|[\u0E32-\u0E33]|[\u0E40-\u0E45]|[\u0E81-\u0E82]|\u0E84|[\u0E87-\u0E88]|\u0E8A|\u0E8D|" +
		"[\u0E94-\u0E97]|[\u0E99-\u0E9F]|[\u0EA1-\u0EA3]|\u0EA5|\u0EA7|[\u0EAA-\u0EAB]|[\u0EAD-\u0EAE]|\u0EB0|" +
		"[\u0EB2-\u0EB3]|\u0EBD|[\u0EC0-\u0EC4]|[\u0F40-\u0F47]|[\u0F49-\u0F69]|[\u10A0-\u10C5]|[\u10D0-\u10F6]|\u1100|" +
		"[\u1102-\u1103]|[\u1105-\u1107]|\u1109|[\u110B-\u110C]|[\u110E-\u1112]|\u113C|\u113E|\u1140|\u114C|\u114E|\u1150|" +
		"[\u1154-\u1155]|\u1159|[\u115F-\u1161]|\u1163|\u1165|\u1167|\u1169|[\u116D-\u116E]|[\u1172-\u1173]|\u1175|\u119E|" +
		"\u11A8|\u11AB|[\u11AE-\u11AF]|[\u11B7-\u11B8]|\u11BA|[\u11BC-\u11C2]|\u11EB|\u11F0|\u11F9|[\u1E00-\u1E9B]|" +
		"[\u1EA0-\u1EF9]|[\u1F00-\u1F15]|[\u1F18-\u1F1D]|[\u1F20-\u1F45]|[\u1F48-\u1F4D]|[\u1F50-\u1F57]|\u1F59|\u1F5B|\u1F5D|" +
		"[\u1F5F-\u1F7D]|[\u1F80-\u1FB4]|[\u1FB6-\u1FBC]|\u1FBE|[\u1FC2-\u1FC4]|[\u1FC6-\u1FCC]|[\u1FD0-\u1FD3]|[\u1FD6-\u1FDB]|" +
		"[\u1FE0-\u1FEC]|[\u1FF2-\u1FF4]|[\u1FF6-\u1FFC]|\u2126|[\u212A-\u212B]|\u212E|[\u2180-\u2182]|[\u3041-\u3094]|" +
		"[\u30A1-\u30FA]|[\u3105-\u312C]|[\uAC00-\uD7A3]";
		
var Ideographic = "[\u4E00-\u9FA5]|\u3007|[\u3021-\u3029]";

var Letter = BaseChar + "|" + Ideographic;

var NCNameStartChar = Letter + "|_";

var NCNameChar = "[A-Z]|_|[a-z]|[\u00C0-\u00D6]|[\u00D8-\u00F6]|[\u00F8-\u02FF]|[\u0370-\u037D]|[\u037F-\u1FFF]|" +
		"[\u200C-\u200D]|[\u2070-\u218F]|[\u2C00-\u2FEF]|[\u3001-\uD7FF]|[\uF900-\uFDCF]|[\uFDF0-\uFFFD]|" +
		"-|\\.|[0-9]|\u00B7|[\u0300-\u036F]|[\u203F-\u2040]";
		
var NCName = "(?:(?:" +  NCNameStartChar + ")(?:" +  NCNameChar + ")*)";

var QName = NCName + ":" + NCName + "|" + NCName;

var NameTest = "\\*|" + NCName + ":\\*|" + QName;

var NodeType = "comment|text|processing-instruction|node";

var NameOperator = "and|or|mod|div";

var Operator = NameOperator + "|\\*|//|/|\\||\\+|-|!=|<=|<|>=|>|=";

var FunctionName = QName;

var AxisName = "ancestor|ancestor-or-self|attribute|child|descendant|descendant-or-self|following|following-sibling|parent|preceding|" +
		"preceding-sibling|self";

var Literal = "\"[^\"]*\"|'[^']*'";

var Numbers = "\\d+(?:\\.\\d?)?|\\.\\d+";

var ExprToken = "\\(|\\)|\\[|\\]|\\.\\.|@|,|::|" + NameTest + "|" + NodeType + "|" + Operator + "|" + FunctionName + "|" + 
AxisName + "|" + Literal + "|" + Numbers + "|\\.";

var xPathToken = new RegExp(ExprToken, "g");

var selectedToken = new RegExp(NCName + ":(?!:)|" + ExprToken + "|\\s", "g");

var nameTestToken = new RegExp(NameTest);
// Function supported by Firefox
var functionNameToken = new RegExp("concat|substring|substring-after|substring-before|string|local-name|name|namespace-uri|normalize-space" +
		"translate|ceiling|count|floor|last|number|position|round|sum|boolean|contains|false|lang|not|starts-with|string-length|true");

var expressionToken = new RegExp("\\]|\\)|\\*" + NameTest + "|" + Literal + "|" + Numbers + "|\\.");

var getNamespace = new RegExp("([^:]+:).*");

// ************************************************************************************************
// FirePathPanel

Firebug.FirePathPanel = function() {}
Firebug.FirePathPanel.prototype = extend(Firebug.Panel,
{
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// extends Panel ()
	
	name: panelName,
	title: "FirePath",

	initialize: function(context, doc) {
		this.addStyleSheet(doc);
		this.location = this.getDefaultLocation(context);
		this.firePathBar = Firebug.chrome.$('FirePathBar');
		this.firePathBar.initialize();
		this.firePathStatusBar = Firebug.chrome.$("FirePathStatusBar");
		
		this.onMouseDown = bind(this.onMouseDown, this);
		
		this.inspecting = false;
		Firebug.Panel.initialize.apply(this, arguments);
	},
	
	destroy: function(state) {
		state.persistedLocation = this.persisteLocation();
		if (this.firePathBar) {
			this.firePathBar.persiste(state);
		}
		this.stopLoading();
		
		if (this.ioBox) {
			this.ioBox.destroy();
			delete this.ioBox;
		}
		Firebug.Panel.destroy.apply(this, arguments);
	},
	
	initializeNode: function(oldPanelNode) {
		this.panelNode.addEventListener("mousedown", this.onMouseDown, false);

		this.ioBoxContainer = this.document.createElement("div");
		setClass(this.ioBoxContainer, "io-box-container");
		this.panelNode.appendChild(this.ioBoxContainer);
		
		if(!this.ioBox) {
			this.ioBox = new InsideOutBox(this, this.ioBoxContainer);
		}
		Firebug.Panel.initializeNode.apply(this, arguments);
	},

	destroyNode: function() {
		this.panelNode.removeEventListener("mousedown", this.onMouseDown, false);
		Firebug.Panel.destroyNode.apply(this, arguments);
	},
	
	show: function(state) {
		if (this.context.loaded) {
			this.showModules(true);

			if(state) {
				this.restoreLocation(state.persistedLocation);
				this.firePathBar.restore(state);
				this.firePathBar.evaluate(true);
			} else {
				this.ioBox.createObjectBox(this.rootElement || this.location.document);
			}
		}
	},
	
	hide: function() {
		this.showModules(false);
	},
	
	showModules: function(show) {
		try {
			this.firePathBar.show(this.context, show);
			this.firePathStatusBar.show(this.context, show);
		} catch (e) {}
		
		// when there is a visibility: hidden style the collapsed attribute doesn't work correctly
		Firebug.chrome.$("fbPanelStatus").removeAttribute("style");
		collapse(Firebug.chrome.$("fbSearchBox"), show);
		collapse(Firebug.chrome.$("fbPanelStatus"), show);
	},
	
	detach: function(oldChrome, newChrome) {
		Firebug.Panel.detach.apply(this, arguments);
		if(this.context == Firebug.currentContext) {
			this.firePathBar.persiste(this.context);
		}
		this.firePathBar = newChrome.$("FirePathBar");
		this.firePathBar.initialize();
		this.firePathStatusBar = newChrome.$("FirePathStatusBar");
		if(this.context == Firebug.currentContext) {
			this.firePathBar.restore(this.context);
			this.firePathBar.currentContext = this.context;
		}
		if(this.context.browser.detached || (newChrome != Firebug.originalChrome && this.context == Firebug.currentContext)) {
			Firebug.FirePathPanel.LocationHighlightModule.addLocationListener(newChrome);
		} else {
			Firebug.FirePathPanel.LocationHighlightModule.removeLocationListener(oldChrome);
		}
	},
	
	reattach: function(doc) {
		Firebug.Panel.reattach.apply(this, arguments);
		this.addStyleSheet(doc);
	},
	
	supportsObject: function(object) {
		if(object instanceof Window)
			return 2;
		else if (object instanceof Element ||
				object instanceof Text ||
				object instanceof Attr ||
				object instanceof Comment ||
				object instanceof Document )
			return 1;
		else
			return 0;
	},
	
	updateSelection: function(object) {
		var location;
		if(object instanceof Document)
			location =  object.defaultView;
		else
			location = object.ownerDocument.defaultView;
		
		this.navigate(location);
		this.firePathBar.setNode(object, this.inspecting);
	},
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// extends Panel (location menu for iframes)
	
	getLocationList: function() {
		var locations = [];
		var win = this.context.window;
		
		locations.push(this.context.window);
		
		for(var i=0; i<win.frames.length; i++) {
			locations.push(win.frames[i].window);
		}
		return locations;
	},
	
	updateLocation: function() {
		this.firePathBar.updateLocation(this.location);
		this.firePathStatusBar.reset(this.context);
		this.setResult(null, null);
	},
	
	getDefaultLocation: function(context) {
		return context.window;
	},
	
	getObjectDescription: function(object) {
		var win = this.context.window;
		
		var path;
		try{
			path = object.location.host + object.location.pathname;
		} catch(e) {
			path = "" + object.location;
		}

		var name = "";

		if(object == win) name = $STR_XP("topWindow");
		else {
			for(var i=0; i<win.frames.length; i++)
				if(win.frames[i].window== object) {
					name = getElementCSSSelector(object.frameElement); //+ "["+i+"]";
					break;
				}
		}
		
		return {name: name, path: path};
	},
	
	persisteLocation: function() {
		var win = this.context.window;
		for(var i = 0; i < win.frames.length; i++)
			if(win.frames[i].window == this.location) 
				return i;
		return "top";
	},
	
	restoreLocation: function(location) {
		var win =this.context.window;
		
		if(location != "top" && location < win.frames.length)
			this.navigate(this.context.window.frames[location]);
		else
			this.navigate(win);
	},
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// extends Panel (option menu)
	
	getOptionsMenuItems: function()
	{
		return [
			getOptionMenu("showParentToolbar", "showParentToolbar"),
			getOptionMenu("generateAbsoluteXPathMenu", "generateAbsoluteXPath"),
			getOptionMenu("showDOMMenu", "showDOM")
		];
	},
	
	updateOption: function(name, value) {
		if(prefRegExp.test(name)) {
			var option = prefRegExp.exec(name)[1];
			if(option == "showDOM") {
				this.displayResult();
			} else if (option == "showParentToolbar") {
				this.firePathBar.showParentToolbar(value);
			}
		}
	},

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Style sheet management method
		
	addStyleSheet: function(doc) {
		// Make sure the stylesheet isn't appended twice.
		if ($("FirePathStyles", doc))
			return;

		var styleSheet = createStyleSheet(doc,
			"chrome://firepath/skin/FirePathPanel.css");
		styleSheet.setAttribute("id", "FirePathStyles");
		addStyleSheet(doc, styleSheet);
	},
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Result management methods
	
	setResult: function(parent, result) {
		this.rootElement = parent;
		this.evaluationResult = result;
		this.displayResult();
		Firebug.FirePathPanel.ResultHighlightModule.refreshHighlight(this);
	},
	
	displayResult: function() {
		this.stopLoading();
		
		this.setResultInfo();
		
		clearNode(this.ioBoxContainer);
		delete this.ioBox.rootObjectBox;
		
		var showDOM = Firebug.getPref(Firebug.prefDomain, "firepath.showDOM")
		
		if(this.evaluationResult != null && !this.evaluationResult.message) {
			if(isArray(this.evaluationResult)) {
				if(showDOM) {
					this.ioBox.createObjectBox(this.rootElement || this.location.document);
					this.ioBox.selectMultiple(this.evaluationResult);
				} else
					this.ioBox.addObjects(this.evaluationResult);
			} else {
				var type = typeof(this.evaluationResult);
				type = type.charAt(0).toUpperCase() + type.substring(1);
				this.ioBoxContainer.textContent = type + ": " + this.evaluationResult;
			}
		} else if(showDOM){
			this.ioBox.createObjectBox(this.rootElement || this.location.document);
		}
	},
	
	setResultInfo: function() {
		var result = this.evaluationResult;
		if(result == null) {
			this.firePathStatusBar.clearResultInfo(this.context);
		} else if(result.message) {
			this.firePathStatusBar.setResultInfo(this.context, "error", $STR_XP(result.message));
		} else {
			if(FBL.isArray(result)) {
				var whiteSpace = 0;
				result.forEach(function(object) {if(isWhitespaceText(object)) whiteSpace++;})
				switch(result.length) {
					case 0:
						this.firePathStatusBar.setResultInfo(this.context, "warning", $STR_XP("noResultError"));
					break;
					case 1:
						this.firePathStatusBar.setResultInfo(this.context, "ok", $STR_XP("oneResultMessage"));
					break;
					default:
						this.firePathStatusBar.setResultInfo(this.context, "ok", 
							$STR_XP("manyResultMessage", result.length) + 
							(whiteSpace > 0? 
								" (" + (whiteSpace == 1? 
									$STR_XP("oneWhiteSpaceMessage"): 
									$STR_XP("manyWhiteSpaceMessage", whiteSpace)
								) + ")":
							"") );
					break;
				}
			} else {
				switch(typeof(result)) {
					case "string":
						this.firePathStatusBar.setResultInfo(this.context, "ok", $STR_XP("stringResultMessage"));
					break;
					case "number":
						this.firePathStatusBar.setResultInfo(this.context, "ok", $STR_XP("numberResultMessage"));
					break;
					case "boolean":
						this.firePathStatusBar.setResultInfo(this.context, "ok", $STR_XP("booleanResultMessage"));
					break;
				}
			}
		}
	},

	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// SourceBox proxy

	createObjectBox: function(object, isRoot) {
		var tag = getNodeTag(object);
		if (tag)
			return tag.replace({object: object}, this.document);
	},

	getParentObject: function(node) {
		if (this.rootElement && node == this.rootElement)
			return null;

		var parentNode = node ? node.parentNode : null;
		return parentNode;
	},

	getChildObject: function(node, index, previousSibling)
	{
		if(node instanceof Document) {
			if(index == 0)
				return node.documentElement;
		}
		else if (previousSibling) {
			return findNextSibling(previousSibling);
		}
		else {
			var childIndex = 0;
			for (var child = node.firstChild; child; child = child.nextSibling) {
				if (!isWhitespaceText(child) && childIndex++ == index)
					return child;
			}
		}

		return null;
	},
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Result display loading management methods
	
	startLoading: function(objects, task) {
		var step = bind(function(object) {task(object); this.increaseLoading()}, this);
		var end = bind(this.finishLoading, this);
 
 		this.stepper = new Stepper(arrayIterator(objects), step, end);
		
		//this.panelNode.removeChild(this.ioBoxContainer);
		
		this.firePathStatusBar.startLoading(this.context, objects.length);
		this.stepper.start();
	},
	
	stopLoading: function() {
		if(this.stepper) {
			this.stepper.stop();
			this.finishLoading();
		}
	},
	
	increaseLoading: function() {
		this.firePathStatusBar.increaseLoading(this.context);
	},
	
	finishLoading: function() {
		this.firePathStatusBar.stopLoading(this.context);
		
		//this.panelNode.appendChild(this.ioBoxContainer);
		
		delete this.stepper;
	},
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Inspector management methods
	
	// make the panel inspectable (new in Firebug 1.7)
	inspectable: true,
	
	startInspecting: function() {
		this.inspecting = true;
		this.previousLocation = this.location;
		this.previousSelector = this.firePathBar.selector;
		this.setResult(null, null);
	},
	
	stopInspecting: function(inspectingNode, cancelled) {
		this.inspecting = false;
		// in Firebug 1.7 the signature of this method changed
		// before there was only on arg: cancelled.
		if (!Firebug.Inspector._resolveInspectingPanelName) {
			cancelled = inspectingNode;
		}
		if(cancelled) {
			this.navigate(this.previousLocation);
			this.firePathBar.selector = this.previousSelector;
			delete this.previousLocation;
			delete this.previousSelector;
		}
		this.firePathBar.reset();
		this.firePathBar.evaluate();
	},
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Event handlers
	
	onMouseDown: function(event) {
		if (!isLeftClick(event))
			return;

		if (getAncestorByClass(event.target, "nodeTag")) {
			var element = Firebug.getRepObject(event.target);
			
			Firebug.Inspector.highlightObject(null);
			element.scrollIntoView();
			Firebug.Inspector.highlightObject(element, Firebug.currentContext);
		}
	}
});

// ************************************************************************************************
// Special Reps

Firebug.FirePathPanel.Attr = domplate(Firebug.Rep,
{
	tag:
		SPAN({"class": "nodeAttr", _repObject:"$attr"}, "&nbsp;",
			SPAN({"class": "nodeName"}, "$attr.nodeName"), "=&quot;",
			SPAN({"class": "nodeValue"}, "$attr.nodeValue|filterFirePathClassValue"), "&quot;"
		),
	
	className: "attrNode",

	supportsObject: function(object) {
		return object instanceof Attr;
	},
	
	getRealObject: function(attribute) {
		return attribute.ownerElement;
	},
	
	getContextMenuItems: function(attribute, target, context) {
		return getNodeContextMenu(attribute, target, context);
	},
	
	getTooltip: function(attribute) {
		return getXPathFromNode(attribute);
	}
});

Firebug.FirePathPanel.Comment = domplate(Firebug.Rep,
{
	tag:
		DIV({"class": "nodeBox", _repObject: "$object"},
			DIV({"class": "nodeComment"},
				"&lt;!--$object.nodeValue--&gt;"
			)
		),
	
	className: "commentNode",

	supportsObject: function(object) {
		return object instanceof Comment;
	},
	
	getContextMenuItems: function(node, target, context) {
		return getNodeContextMenu(node, target, context);
	},
	
	getTooltip: function(node) {
		return getXPathFromNode(node);
	}
});

Firebug.FirePathPanel.CDATASection = domplate(Firebug.Rep,
{
	tag:
		DIV({"class": "nodeBox", _repObject: "$object"},
			"&lt;![CDATA[",
			SPAN({"class": "nodeText"}, "$object.nodeValue"),
			"]]&gt;"
		),
		
	className: "cdataNode",

	supportsObject: function(object) {
		return object instanceof CDATASection;
	},
	
	getContextMenuItems: function(node, target, context) {
		return getNodeContextMenu(node, target, context);
	},
	
	getTooltip: function(node) {
		return getXPathFromNode(node);
	}
});

Firebug.registerRep(
	Firebug.FirePathPanel.Attr,
	Firebug.FirePathPanel.Comment,
	Firebug.FirePathPanel.CDATASection
);

// ************************************************************************************************
// Extension of FirebugReps.Element and FirebugReps.TextNode to modify the context menu 
// and tooltip when the FirePath tab is selected

function overwriteMethodForPanel(object, methodName, panelName, newMethod ) {
	if(object[methodName] && object[methodName] instanceof Function)
		object["default" + methodName] = object[methodName];
	object[methodName] = function () {
		var selectedPanelName = Firebug.currentContext.panelName;

		if(selectedPanelName != panelName) {
			if(object["default" + methodName])
				return object["default" + methodName].apply(object, arguments);
		} else {
			return newMethod.apply(object, arguments);
		}
	}
}

FirebugReps.Element.getTagName = function(node) {
	return getTagName(node);
}

// Overwrite FirebugReps.Element.getContextMenuItems
overwriteMethodForPanel(
	FirebugReps.Element,
	"getContextMenuItems",
	Firebug.FirePathPanel.prototype.name,
	function(node, target, context) {
		return getNodeContextMenu(node, target, context);
	}
);

// Overwrite FirebugReps.Element.getTooltip
overwriteMethodForPanel(
	FirebugReps.Element,
	"getTooltip",
	Firebug.FirePathPanel.prototype.name,
	function(node) {
		return getXPathFromNode(node);
	}
);

// Overwrite FirebugReps.TextNode.getContextMenuItem
overwriteMethodForPanel(
	FirebugReps.TextNode,
	"getContextMenuItems",
	Firebug.FirePathPanel.prototype.name,
	function(node, target, context) {
		return getNodeContextMenu(node, target, context);
	}
);

// Overwrite FirebugReps.TextNode.getTooltip
overwriteMethodForPanel(
	FirebugReps.TextNode,
	"getTooltip",
	Firebug.FirePathPanel.prototype.name,
	function(node) {
		return getXPathFromNode(node);
	}
);

// Overwrite FirebugReps.TextNode.getRealObject
overwriteMethodForPanel(
	FirebugReps.TextNode,
	"getRealObject",
	Firebug.FirePathPanel.prototype.name,
	function(object) {
		return object.parentNode;
	}
);

// ************************************************************************************************
// Tree definition

//TODO improve this (add the url, ns...)
Firebug.FirePathPanel.Document = domplate(FirebugReps.Document,
{
	tag:
		DIV({"class": "nodeBox containerNodeBox repIgnore", _repObject: "$object"},
			DIV({"class": "nodeLabel"},
				IMG({"class": "twisty"}),
				SPAN({"class": "nodeLabelBox repTarget"},
					"&lt;",
					SPAN({"class": "nodeTag"}, "document"),
					SPAN({"class": "nodeBracket insertBefore"}, "&gt;")
				)
			),
			DIV({"class": "nodeChildBox"}),
			DIV({"class": "nodeCloseLabel"},
				SPAN({"class": "nodeCloseLabelBox repTarget"},
					"&lt;/",
					SPAN({"class": "nodeTag"}, "document"),
					"&gt;"
				)
			 )
		)
});

Firebug.FirePathPanel.Element = domplate(FirebugReps.Element,
{
	tag:
		DIV({"class": "nodeBox containerNodeBox repIgnore", _repObject: "$object"},
			DIV({"class": "nodeLabel"},
				IMG({"class": "twisty"}),
				SPAN({"class": "nodeLabelBox repTarget"},
					"&lt;",
					SPAN({"class": "nodeTag"}, "$object|getTagName"),
					FOR("attr", "$object|attrIterator|filterFirePathClass", Firebug.FirePathPanel.Attr.tag),
					SPAN({"class": "nodeBracket insertBefore"}, "&gt;")
				)
			),
			DIV({"class": "nodeChildBox"}),
			DIV({"class": "nodeCloseLabel"},
				SPAN({"class": "nodeCloseLabelBox repTarget"},
					"&lt;/",
					SPAN({"class": "nodeTag"}, "$object|getTagName"),
					"&gt;"
				)
			 )
		),
	filterFirePathClassValue: 
		function(value) {
			return value.replace(" firepath-matching-node", "");
		},
	filterFirePathClass:
		function(attrs) {
			for (var i=0; i<attrs.length; i++) {
				var attr = attrs[i];
				if(attr.localName == "class" && attr.nodeValue == "firepath-matching-node") {
					attrs.splice(i, 1);
					break;
				}
			}
			return attrs;
		}
});

Firebug.FirePathPanel.TextElement = domplate(Firebug.FirePathPanel.Element,
{
	tag:
		DIV({"class": "nodeBox textNodeBox repIgnore", _repObject: "$object"},
			DIV({"class": "nodeLabel"},
				SPAN({"class": "nodeLabelBox repTarget"},
					"&lt;",
					SPAN({"class": "nodeTag"}, "$object|getTagName"),
					FOR("attr", "$object|attrIterator|filterFirePathClass", Firebug.FirePathPanel.Attr.tag),
					SPAN({"class": "nodeBracket insertBefore"}, "&gt;"),
					SPAN({"class": "nodeText", _repObject: "$object.firstChild"}, "$object.firstChild.nodeValue|escapeWhitespace"),
					"&lt;/",
					SPAN({"class": "nodeTag"}, "$object|getTagName"),
					"&gt;"
				)
			)
		),
	escapeWhitespace: function(value) {
		return escapeWhitespace(value)
	}
});

Firebug.FirePathPanel.EmptyElement = domplate(Firebug.FirePathPanel.Element,
{
	tag:
		DIV({"class": "nodeBox emptyNodeBox repIgnore", _repObject: "$object"},
			DIV({"class": "nodeLabel"},
				SPAN({"class": "nodeLabelBox repTarget"},
					"&lt;",
					SPAN({"class": "nodeTag"}, "$object|getTagName"),
					FOR("attr", "$object|attrIterator|filterFirePathClass", Firebug.FirePathPanel.Attr.tag),
					SPAN({"class": "nodeBracket insertBefore"}, "/&gt;")
				)
			)
		)
});

Firebug.FirePathPanel.TextNode = domplate(Firebug.FirePathPanel.TextElement,
{
	tag:
		DIV({"class": "nodeBox", _repObject: "$object"},
			SPAN({"class": "nodeText"}, "$object.nodeValue|escapeWhitespace")
		)
});

// ************************************************************************************************
// Local Helpers

function getNodeTag(node) {
	if (node instanceof Element) {
		if (node instanceof HTMLAppletElement)
			return Firebug.FirePathPanel.EmptyElement.tag;
		else if (node.firebugIgnore || node.id == "_firebugConsole" || node.className == "firebugHighlight")
			return null;
		else if (isEmptyElement(node))
			return Firebug.FirePathPanel.EmptyElement.tag;
		else if (isPureText(node))
			return Firebug.FirePathPanel.TextElement.tag;
		else
			return Firebug.FirePathPanel.Element.tag;
	}
	else if(node instanceof Document)
		return Firebug.FirePathPanel.Document.tag;
	else if (node instanceof Text)
		return Firebug.FirePathPanel.TextNode.tag;
	else if (node instanceof CDATASection)
		return Firebug.FirePathPanel.CDATASection.tag;
	else if (node instanceof Comment)
		return Firebug.FirePathPanel.Comment.tag;
	else
		return FirebugReps.Nada.tag;
}

function isWhitespaceText(node) {
	if (node instanceof HTMLAppletElement)
		return false;
	return node.nodeType == 3 && isWhitespace(node.nodeValue);
}

function findNextSibling(node) {
	for (var child = node.nextSibling; child; child = child.nextSibling) {
		if (!isWhitespaceText(child))
			return child;
	}
}

function isPureText(element) {
	if(element.firstChild.nodeType == 3
			&& element.childNodes.length == 1
			&& !isWhitespaceText(element.firstChild))
		return true;
	else
		return false;
}

function isEmptyElement(element) {
	for(var child = element.firstChild; child; child = child.nextSibling) {
		if(!isWhitespaceText(child)) return false;
	}
	return true;
}

function getOptionMenu(label, option) {
	var key = Firebug.FirePathPanel.prototype.name + "." + option;
	var value = Firebug.getPref(Firebug.prefDomain, key);
	
	return {label: $STR_XP(label),
		nol10n: true,
		type: "checkbox",
		checked: value,
		command: bindFixed(Firebug.setPref, Firebug, Firebug.prefDomain, key, !value) };
}

function getNodeContextMenu(node, target, context) {

	// This to make sure the context menu is added to the right object
	if(node != Firebug.getRepObject(target)) return;
	
	var firePathBar = context.getPanel(panelName).firePathBar;
	
	var contextMenu = [
		{label: $STR_XP("copy", $STR_XP("xpathSelector")),
		nol10n: true,
		command: bindFixed(copyXPath, FBL, node)},
		{label: $STR_XP("copy", $STR_XP("cssSelector")),
		nol10n: true,
		command: bindFixed(copyCssSelector, FBL, node)}
	];
	
	if(Firebug.getPref(Firebug.prefDomain, "firepath.showParentToolbar")) {
		var panel = context.getPanel(Firebug.FirePathPanel.prototype.name);
		if(panel.rootElement && panel.rootElement != panel.location.document) {
			contextMenu.push(
				{label: $STR_XP("copyFromContext", $STR_XP("xpathSelector")),
				nol10n: true,
				command: bindFixed(copyXPath, FBL, node, panel.rootElement) }
			);
			contextMenu.push(
				{label: $STR_XP("copyFromContext", $STR_XP("cssSelector")),
				nol10n: true,
				command: bindFixed(copyCssSelector, FBL, node, panel.rootElement) }
			);
		}
	}
	
	contextMenu.push(
		{label: $STR_XP("setSelector", $STR_XP(firePathBar.evaluationMode + 'Selector')),
			nol10n: true,
			command: bindFixed(firePathBar.setNode, firePathBar, node)}
	)
		
	var element = null;
	if(node instanceof Element) {
		element = node;
		if(Firebug.getPref(Firebug.prefDomain, "firepath.showParentToolbar"))
			contextMenu.push(
				{label: $STR_XP("setContext"),
					nol10n: true,
					command: bindFixed(firePathBar.setContextNode, firePathBar, node)}
			);
	}
	else if(node instanceof Attr)
		element = node.ownerElement;
	else if(node instanceof Text)
		element = node.parentNode;
		
	if(element) {
		contextMenu.push("-");
		contextMenu.push({label: "ScrollIntoView", command: bindFixed(element.scrollIntoView, element) });
	}
	
	return contextMenu;
}

function copyXPath(node, context) {
	var xpath = getXPathFromNode(node, context);
	copyToClipboard(xpath);
}

function copyCssSelector(node, context) {
	var cssSelector = getCssSelectorFromNode(node, context);
	copyToClipboard(cssSelector);
}

// Generator that create an iterator over an array
function arrayIterator(array) {
	for(var i = 0, length = array.length; i < length; i++) {
		yield array[i];
	}
}

function escapeWhitespace(value) {
	return value.replace(' ', '\u00a0', 'gm');
}

// ************************************************************************************************
// Global Helpers

FBL.$STR_XP = function() {
	var args = cloneArray(arguments);
	var key = args.shift();
	var bundle = document.getElementById("Firepath_strings");
	try{
		if(args.length > 0)
			return bundle.getFormattedString(key, args);
		else
			return bundle.getString(key);
	} catch(e) {
		return key;
	}
}

FBL.isArray = function (object) {
		return FirebugReps.Arr.isArray(object);
}

FBL.isHtmlDocument = function(doc) {
	return doc.contentType === 'text/html';
}

FBL.getTagName = function(node) {
	var ns = node.namespaceURI;
	var prefix = node.lookupPrefix(ns);
	
	//if an element has a namespace it needs a prefix
	if(ns != null && !prefix) {
		prefix = getPrefixFromNS(ns);
	}
	
	var name = node.localName;
	if (isHtmlDocument(node.ownerDocument)) {
		//lower case only for HTML document
		return name.toLowerCase();
	} else {
		return (prefix? prefix + ':': '') + name;
	}
}

FBL.getPrefixFromNS = function(ns) {
	return ns.replace(/.*[^\w](\w+)[^\w]*$/, "$1");
}

FBL.getXPathFromNode = function(node, context) {
	var result = "";
	var stop = false;
	var absolute = Firebug.getPref(Firebug.prefDomain, "firepath.generateAbsoluteXPath");

	var parent = context || node.ownerDocument;
	while (node && node != parent && !stop) {
		var str = "";
		var position = getNodePosition(node);
		switch (node.nodeType) {
			case Node.DOCUMENT_NODE:
			break;
			case Node.ATTRIBUTE_NODE:
				str = "@" + node.name;
			break;
			case Node.COMMENT_NODE:
				str = "comment()";
			break;
			case Node.TEXT_NODE:
				str = "text()";
			break;
			case Node.ELEMENT_NODE:

				var name = getTagName(node);

				if(!absolute && node.id && node.id != "") {
					str = ".//*[@id='" + node.id + "']";
					position = null;
					stop = true;
				} else {
					str = name;
				}
				
			break;
		}
		
		result = str + (position ? "[" + position + "]" : "") + (result? "/": "") + result;
		
		if(node instanceof Attr) node = node.ownerElement;
		else node = node.parentNode;

	}

	return result;
}

const firepathClass = /\s*firepath-matching-node\s*/g;
const multipleSpace = /\s+/g;

FBL.getCssSelectorFromNode = function (node, context) {
	var result = '',
		node,
		parent = context || node.ownerDocument,
		stop = false,
		str;
	
	while (node && node != parent && !stop) {
		if(node.nodeType === Node.ELEMENT_NODE) {
			if(node.id) {
				str = '#' + node.id;
				stop = true;
			} else if(node.className && node.className.replace(firepathClass, '')) {
				str = '.' + node.className.replace(firepathClass, ' ').trim()
					.replace(multipleSpace, ' ').split(' ').join('.');
				stop = true;
			} else {
				str = node.localName.toLowerCase();
			}
			
			result = str + (result? '>' + result: ''); 
		}
		
		if(node instanceof Attr) {
			node = node.ownerElement;
		} else {
			node = node.parentNode;
		}
	}
	
	return result;
}

function getNodePosition(node) {
	if (!node.parentNode)
		return null;
	
	var siblings = node.parentNode.childNodes;
	var count = 0;
	var position;
	
	for (var i = 0; i < siblings.length; i++) {
		var object = siblings[i];
		if(object.nodeType == node.nodeType && object.nodeName == node.nodeName) {
			count++;
			if(object == node) position = count;
		}
	}

	if (count > 1)
		return position;
	else
		return null;
}

// ************************************************************************************************
// Extension of InsideOutBox to support multiple selection

var InsideOutBox = Firebug.InsideOutBox ? Firebug.InsideOutBox : top.InsideOutBox;

InsideOutBox.prototype = extend(InsideOutBox.prototype, {
	
	selectMultiple: function(objects) {
		var filteredObjects =  objects.filter(function(object){return !isWhitespaceText(object);});
		
		this.firstSelection = true;
		this.view.startLoading(filteredObjects, bind(this.selectObject, this));
	},
	
	selectObject: function(object) {
		
		// Get correct object to find or create the objectBox
		var correctObject = object;
		if(object instanceof Attr)
			correctObject = object.ownerElement;
		else if(object instanceof Text)
			correctObject = object.parentNode;
		
		// Find the objectBox (node in the ioBox)
		var objectBox = this.findObjectBox(correctObject);
		
		// Create it if it doesn't exist yet
		if(!objectBox)
			objectBox = this.createObjectBox(correctObject);
		
		// objectBox can be null for object that have the firebugIgnore attribute
		if(objectBox != null) {
			
			// Get the correct node in the ioBox to set its class to selected
			var nodeToSelect = objectBox;
			
			if(object instanceof Attr) {
				// Get the nodes that contains the attributes
				var attributeNodes = getElementsByClass(objectBox, "nodeAttr");
				
				// This get the node that contains the attribute we are looking for
				nodeToSelect = Array.filter(attributeNodes, function(attributeNode) {
					return attributeNode.childNodes[1].textContent == object.name;
				})[0];
			}
			else if(object instanceof Text) {
				// Get the nodes that contains the text
				var textNodes = getElementsByClass(objectBox, "nodeText");
				
				if(textNodes.length == 0) {
					this.expandObjectBox(objectBox);
					textNodes = getElementsByClass(objectBox, "nodeText");
				}
		
				// This get the node that contains the text we are looking for
				nodeToSelect = Array.filter(textNodes, function(textNode) {
					return textNode.textContent == escapeWhitespace(object.nodeValue);
				})[0];
			}
			
			// Set the correct node's class to selected
			setClass(nodeToSelect, "selected");
			
			// Make the objectBox visible 
			this.openObjectBox(objectBox);
			
			// Scroll into the first element selected in the tree
			if(this.firstSelection) {
				this.firstSelection = false;
				scrollIntoCenterView(nodeToSelect, this.view.ioBoxContainer);
			}
		}
	},
	
	addObjects: function(objects) {
		this.view.startLoading(objects, bind(this.addObject, this));
	},
	
	addObject: function(object) {
		var objectBox;
			
		if(object instanceof Attr) {
			objectBox = this.view.createObjectBox(object.ownerElement);
			
			// get the nodes that contains the attributes
			var attributeNodes = getElementsByClass(objectBox, "nodeAttr");
			
			// return the node that contains the attribute we are looking for
			var attr = Array.filter(attributeNodes, function(attributeNode) {
				// the node containing the attribute name
				return attributeNode.childNodes[1].textContent == object.name;
			})[0];
			if(attr) setClass(attr, "selected");
		} else {
			objectBox = this.view.createObjectBox(object);
		}
		
		this.box.appendChild(objectBox);
	}
});

// ************************************************************************************************
// Auto Completion
	
Firebug.FirePathPanel.xPathAutoCompleter = function(evaluator) {
	this.autoCompleter = new Firebug.AutoCompleter(
			null,
			bind(this.getAutoCompleteRange, this),
			bind(this.getAutoCompleteList, this),
			true, true);
	
	this.evaluator = evaluator;
}

Firebug.FirePathPanel.xPathAutoCompleter.prototype = {
	
	reset: function() {
		this.autoCompleter.reset();
	},
	
	complete: function(context, textbox, cycle, reverse) {
		this.autoCompleter.complete(context, textbox, cycle, reverse);
	},
	
	getAutoCompleteRange: function(value, offset, context) {
		
		var preSelection = value.substring(0, offset);
		var result;
		var tokens = [];

		while ((result = selectedToken.exec(preSelection)) != null) {
			tokens.unshift(result[0]);
		}

		var start;
		var end;
		if(tokens.length == 0) {
			start = 0;
			end =  value.length;
		} else if(nameTestToken.test(tokens[0])) {
			start = preSelection.lastIndexOf(tokens[0]);
			end = start + tokens[0].length
		} else {
			start = preSelection.lastIndexOf(tokens[0]) + tokens[0].length;
			var postSelection = value.substring(start);
			var firstTokenIndex = postSelection.search(selectedToken);
			end = (firstTokenIndex == -1? value.length : start + firstTokenIndex);
		}
		
		return{start: start, end: end - 1};
	},
	
	getAutoCompleteList: function(preExpr, expr, postExpr, context) {
		var result;
		var token;
		var previousToken = ""
		var tokens = [];
		var stack = [];
		while ((result = xPathToken.exec(preExpr)) != null) {
			token = result[0];
			if(token == "[") {
				stack.unshift({type: "predicate", name: tokens[0] || ""});
			} else if(token == "(") {
				if(functionNameToken.test(previousToken))
					stack.unshift({type: "function", name: tokens[0] || ""});
				else
					stack.unshift({type: "bracket", name: tokens[0] || ""});
			} else if(token == "]" || token == ")") {
				stack.shift();
			}
			tokens.unshift(token);
			previousToken = token
		}
		
		var returning = [];
		
		var inside = (stack.length > 0? stack[0].type: null);
		
		if (!tokens.length) {
			// At the begining
			returning = extendArrays(["/"], this.getNodeList(expr, inside, tokens, true) || [], this.Functions);
		} else if(tokens[0] == "|") {
			// After union operator
			returning = this.getNodeList(expr, inside, tokens, true) || [];
		} else if(tokens[0] == "::" || tokens[0] == "@") {
			// After an Axis
			returning = this.getNodeList(expr, inside, tokens, false) || [];
		} else if(include(["/", "//"], tokens[0])) {
			// After a separator or the root
			returning = this.getNodeList(expr, inside, tokens, true) || [];
		} else if(include(["(", ",", "["], tokens[0]) ||
				// If the token is * or  name operator, we make sure it's actually an operator
				(tokens[0] != "*" && !include(this.NameOperators, tokens[0]) && include(this.Operators, tokens[0])) ||
				((tokens[0] == "*" || include(this.NameOperators, tokens[0]))
					 && tokens[1] != undefined && !include(extendArrays(["@", "::", "(", "["], this.Operators), tokens[1]))
			) {
			// After "(", "," "[" or an operator
			var nodeList = this.getNodeList(expr, inside, tokens, true);
			if(nodeList)
				returning =  extendArrays(["/"], nodeList, this.Functions);
		} else if(expressionToken.test(tokens[0])) {
			// After an expression
			returning =  this.getOperators(tokens[0], inside);
		} else{
			return [];
		}
		if(tokens[0] == "." && returning.length > 0 && !include(returning, ".")) returning.unshift(".");
		if(tokens[0] == "/" && returning.length > 0 && !include(returning, "/")) returning.unshift("/");

		return returning;
	},
	
	getNodeList: function(expr, inside, tokens, addAxes) {
		var flattenedExpr = this.flattenExpression(tokens);
		var returning = [];
		
		var result = this.evaluator.evaluateExpression(flattenedExpr + "*");
		var attribute = false;
		if(!result) return null;
		
		if(endsWith(flattenedExpr, "@") || endsWith(flattenedExpr, "attribute::")) {
			result.forEach(function(attribute) {
				add(returning, attribute.name);
			});
			attribute = true;
			this.addNamespacesAndSort(returning);
			if(returning.length > 0) returning.unshift("*");
		} else {
			result.forEach(function(node) {
				add(returning, getTagName(node));
			});
			this.addNamespacesAndSort(returning);
			if(this.evaluator.hasResult(flattenedExpr + "comment()")) returning.push("comment()");
			if(this.evaluator.hasResult(flattenedExpr + "node()")) returning.push("node()");
			if(this.evaluator.hasResult(flattenedExpr + "text()")) returning.push("text()");
			if(addAxes) {
				var parentExpression = flattenedExpr.replace(/\/$/, "");
				parentExpression += (endsWith(parentExpression, "/")?"descendant-or-self::node()":"");
				parentExpression = parentExpression || "/";
				result = this.evaluator.evaluateExpression(parentExpression);
				if(result) {
					var hasSelf =  result.some(function(node) {return node.nodeType == 1;});
					var hasAttribute = result.some(function(node) {return node.hasAttributes();});
					var hasAncestor = result.some(function(node) {return node.parentNode && node.parentNode.nodeType==1;});
					var hasDescendant = result.some(function(node) {return node.hasChildNodes();});
					var hasFollowingSibling = result.some(function(node) {return hasNextSibling(node);});
					var hasFollowing = hasFollowingSibling || result.some(function(node) {return hasFollowingNode(node);});
					var hasPrecedingSibling = result.some(function(node) {return hasPreviousSibling(node);});
					var hasPreceding = hasPrecedingSibling || result.some(function(node) {return hasPrecedingNode(node);});
					returning.push(".");
					if(hasAttribute) returning.push("@");
					if(hasAncestor) returning.push("..");
					if(hasAncestor) returning.push("ancestor::");
					if(hasSelf || hasAncestor)returning.push("ancestor-or-self::");
					if(hasDescendant) returning.push("descendant::");
					if(hasSelf || hasDescendant)returning.push("descendant-or-self::");
					if(hasFollowing) returning.push("following::");
					if(hasFollowingSibling) returning.push("following-sibling::");
					if(hasAncestor) returning.push("parent::");
					if(hasPreceding) returning.push("preceding::");
					if(hasPrecedingSibling) returning.push("preceding-sibling::");
					if(hasDescendant) returning.push("child::");
					if(hasSelf)returning.push("self::");
					if(hasAttribute) returning.push("attribute::");
					
					if(hasDescendant) returning.unshift("*");
				}
			} else {
				if(returning.length > 0) returning.unshift("*");
			}
		}
		
		if(returning.indexOf(expr) != -1) {
			returning.unshift(expr + " ");
			if(!attribute) returning.unshift(expr + "/", expr+"[");
			if(inside == "function") returning.unshift(expr + ",", expr + ")");
			if(inside == "bracket") returning.unshift(expr + ")");
			if(inside == "predicate") returning.unshift(expr + "]");
			if(!attribute) returning.unshift(expr+"|");
		}
		return returning;
	},
	
	addNamespacesAndSort: function(returning) {
		var namespaces = [];
		for each(var node in returning) {
			if(getNamespace.test(node)) {
				add(namespaces, getNamespace.exec(node)[1] + "*");
			}
		}
		returning.push.apply(returning, namespaces);
		returning.sort();
	},
	
	getOperators: function(token, inside) {
		var returning;
		if((/\)|"[^"]*"|'[^']*'|\d+(?:\.\d?)?|\.\d+/).test(token))
			returning = cloneArray(this.NumberAndBooleanOperators);
		else
			returning = extendArrays(this.NodeSetOperators, this.NumberAndBooleanOperators);
		
		if(inside == "function") returning.unshift(",", ")");
		if(inside == "bracket") returning.unshift(")");
		if(inside == "predicate") returning.unshift("]");
		
		return returning;
	},
	
	flattenExpression: function(tokens) {
		var inPredicate = 0;
		var inFunctionOrBracket = 0;
		var clone = cloneArray(tokens);
		var token;
		var flattenedExpression = [];
		
		// Piece of code used twice in the method
		function inPF() {
				if(token =="]") inPredicate++;
				else if(token =="[") inPredicate--;
				else if(token ==")") inFunctionOrBracket++;
				else if(token =="(") inFunctionOrBracket--;
		}
		
		while(token = clone.shift()) {
			// If we are in a function or predicate
			if(inPredicate == 0 && inFunctionOrBracket == 0 && (
					include(["(", ",", "["], token) ||
					// If the token is * or  name operator, we make sure it's actually an operator
					(token != "*" && !include(this.NameOperators, token) && include(this.NumberAndBooleanOperators, token)) ||
					((token == "*" || include(this.NameOperators, token))
						 && clone[0] != undefined && !include(extendArrays(["@", "::", "(", "["], this.Operators), clone[0]))
				)
			) 
			{
				//if the last token is the root
				if(include(["/", "//"], flattenedExpression[0]))
					break;
				
				// Find the end of the function or predicate
				do {
					inPF();
					if(inPredicate < 0 || inFunctionOrBracket < 0) break;
				} while(token = clone.shift());
				
				// Remove the function name (if in a function)
				if(inFunctionOrBracket < 0) {
					if(functionNameToken.test(clone[0]))
						token = clone.shift();
					else {
						inFunctionOrBracket = 0;
						inPredicate = 0;
						continue;
					}
				}
				
				// Reset inFunction and inPredicate
				inFunctionOrBracket = 0;
				inPredicate = 0;
				
				flattenedExpression.unshift("/");
			} else {
				inPF();
				if(token =="|") break;
				flattenedExpression.unshift(token);
			}
		}
		return flattenedExpression.join(" ");
	},

	NodeSetOperators: [
		"/",
		"//",
		"|",
		"["
	],
	
	NumberAndBooleanOperators: [
		"and ",
		"or ",
		"< ",
		"<= ",
		"> ",
		">= ",
		"= ",
		"+ ",
		"- ",
		"* ",
		"div ",
		"mod "
	],

	Operators: [
		"and",
		"or",
		"<",
		"<=",
		">",
		">=",
		"=",
		"+",
		"-",
		"*",
		"div",
		"mod",
		"/",
		"//",
		"|"
	],
	
	NameOperators: [
		"and",
		"or",
		"div",
		"mod"
	],

	Functions: [
		"concat(",
		"substring(",
		"substring-after(",
		"substring-before(",
		"string(",
		"local-name(",
		"name(",
		"namespace-uri(",
		"normalize-space(",
		"translate(",
		"ceiling(",
		"count(",
		"floor(",
		"last()",
		"number(",
		"position()",
		"round(",
		"sum(",
		"boolean(",
		"contains(",
		"false()",
		"lang(",
		"not(",
		"starts-with(",
		"string-length(",
		"true()",
	]
}

// ************************************************************************************************
// Auto Completion Helpers

function extendArrays(){
	var newArray = [];
	for (var i = 0; i < arguments.length; i++) {
		newArray.push.apply(newArray, arguments[i]);
	}
	return newArray;
}

function include (arrayOrString, value) {
	try{
		return arrayOrString.indexOf(value) != -1;
	} catch(e) {
		return false;
	}
}

function add(array, value) {
	if(!include(array, value)) {
		array.push(value);
	}
}

function endsWith(string, end) {
	return string.search(end+"$") != -1;
}

function hasNextSibling(node) {
	return !!getNextElement(node.nextSibling)
}

function hasPreviousSibling(node) {
	return !!getPreviousElement(node.previousSibling)
}

function hasFollowingNode(node) {
	var parent = node;
	while(parent = parent.parentNode) {
		if(hasNextSibling(parent))
			return true;
	}
	return false;
}

function hasPrecedingNode(node) {
	var parent = node;
	while(parent = parent.parentNode) {
		if(hasPreviousSibling(parent))
			return true;
	}
	return false;
}

// ************************************************************************************************
// Stepper use to simulate a thread and prevent the UI from freezing

function Stepper(iterator, step, end, timePeriod){
	this.iterator = iterator
	this.step = step;
	this.timePeriod = Math.max(1, timePeriod || defaultTimePeriod);
	this.end = end;
	this.stopped = true;
	this.execute = bind(this.execute, this);
}

Stepper.prototype = {
	start: function() {
		this.timeoutID = setTimeout(this.execute, 0);
	},
	
	execute: function() {
		try {
			for(var i = 0, date = new Date(); i < this.timePeriod; i = Date.now() - date) {
				this.step(this.iterator.next());
			}
			this.timeoutID = setTimeout(this.execute, 0);
		} catch(e) {
			setTimeout(this.end, 0);
		}
	},
	
	stop: function() {
		clearTimeout(this.timeoutID);
		this.iterator.close();
	}
}

// ************************************************************************************************
// location highlighting module

Firebug.FirePathPanel.LocationHighlightModule = extend(Firebug.Module,
{
	// add event listener
	initializeUI: function(detachArgs) {
		this.addLocationListener(Firebug.chrome);
	},
	
	shutdown: function() {
		this.removeLocationListener(Firebug.chrome);
	},
	
	addLocationListener: function(chrome) {
		chrome.$("fbLocationList").addEventListener("mouseover", this.onLocationMouseOver, false);
		chrome.$("fbLocationList").addEventListener("mouseout", this.onLocationMouseOut, false);
	},
	
	removeLocationListener: function(chrome) {
		chrome.$("fbLocationList").removeEventListener("mouseover", this.onLocationMouseOver, false);
		chrome.$("fbLocationList").removeEventListener("mouseout", this.onLocationMouseOut, false);
	},
	
	onLocationMouseOver: function(event) {
		var panel = Firebug.chrome.getSelectedPanel();
	
		if(panel.name == panelName) {
			var repObject = event.originalTarget.repObject;
			if(repObject && repObject.frameElement) {
				Firebug.Inspector.highlightObject(repObject.frameElement, Firebug.currentContext);
			}
		}
	},
	
	onLocationMouseOut: function() {
		Firebug.Inspector.highlightObject(null);
	}
})

// ************************************************************************************************
// result highlighting module

Firebug.FirePathPanel.ResultHighlightModule = extend(Firebug.Module,
{
	initialize: function() {
		this.highlighted = false;
		this.highlightedElement = [];
	},
	
	destroyContext: function(context, persistedState) {
		persistedState.persistedFirePathResultNotHighlighted = !!context.firePathResultNotHighlighted;
		this.clear();
	},
	
	loadedContext: function(context) {
		if(context.persistedState)
			context.firePathResultNotHighlighted = context.persistedState.persistedFirePathResultNotHighlighted 
	},
	
	reattachContext: function(browser, context) {
		this.highlightButton = Firebug.chrome.$("FirePathBarHighlightButton");
	},
	
	showContext: function(browser, context) {
		this.highlightButton = Firebug.chrome.$("FirePathBarHighlightButton");
		this.refreshHighlightButton(context.getPanel(panelName));
	},
	
	hideUI: function() {
		this.clear();
	},
	
	showPanel: function(browser, panel) {
		if(panel) {
			if(panel.name == panelName) {
				this.highlightButton.collapsed = false;
				if(!panel.context.firePathResultNotHighlighted)
					this.highlight(panel.context);
			} else {
				this.highlightButton.collapsed = true;
				this.clear();
			}
		}
	},
	
	refreshHighlightButton: function(panel) {
		if(panel.evaluationResult != null && isArray(panel.evaluationResult) &&
			panel.evaluationResult.some(function(e) {return e.nodeType == 1 && (e.namespaceURI == null || e.namespaceURI == xhtmlNS)})) 
		{
			this.highlightButton.setAttribute("checked", !panel.context.firePathResultNotHighlighted);
			this.highlightButton.setAttribute("disabled", false);
		} else {
			this.highlightButton.setAttribute("checked", false);
			this.highlightButton.setAttribute("disabled", true);
		}
	},
	
	// call when the result has changed
	refreshHighlight: function(panel) {
		this.refreshHighlightButton(panel);
		if(!panel.context.firePathResultNotHighlighted) {
			this.clear();
			this.highlight(panel.context);
		}
	},
	
	toggleHighlight: function() {
		var context = Firebug.currentContext;
		if(!context.firePathResultNotHighlighted) {
			this.clear();
		} else {
			this.highlight(context);
		}
		context.firePathResultNotHighlighted = !context.firePathResultNotHighlighted
		this.highlightButton.setAttribute("checked", !context.firePathResultNotHighlighted);
	},
	
	highlight: function(context) {
		var panel = context.getPanel(panelName);
		var doc = panel.location.document;
		var resultElement = panel.evaluationResult;
		
		var isCSSAttached = doc.getElementById("firepath-matching-node-style");
		
		if(!isCSSAttached) {
			var head = doc.getElementsByTagName("head")[0]
			if(head) {
				if(!context.firePathStyleSheet) {
					var styleSheet = document.createElementNS(xhtmlNS, "style");
					styleSheet.firebugIgnore = true;
					styleSheet.setAttribute("type", "text/css");
					styleSheet.setAttribute("id", "firepath-matching-node-style");
					styleSheet.innerHTML = 
					".firepath-matching-node {" +
					"	outline: 2px dashed #00F;" +
					"}";
					context.firePathStyleSheet = styleSheet;
				}
				head.appendChild(context.firePathStyleSheet);
			} else {
				// if we can't attach the stylesheet there is no need to highlighting the result
				// for instance in XML documents
				return;
			}
		}
		
		var step = bind(function(element) {
			if(element.nodeType == 1 && isVisible(element)) {
				setClass(element, "firepath-matching-node");
				this.highlightedElement.push(element);
			}
		}, this);
		var end = bind(function() {
			delete this.stepper;
		}, this);
 
 		this.stepper = new Stepper(arrayIterator(resultElement), step, end);
 		this.stepper.start();
	},
	
	clear: function(doc) {
		// clear the stylesheet from the doc before evaluating an expression
		if(doc) {
			var styleSheet = doc.getElementById("firepath-matching-node-style");
			if(styleSheet) {
				styleSheet.parentNode.removeChild(styleSheet);
			}
		}
		
		if(this.stepper) {
			this.stepper.stop();
			delete this.stepper;
		}
		var element;
		while(element = this.highlightedElement.pop()) {
			removeClass(element, "firepath-matching-node");
			if (element.className) {
				element.className = element.className.trim();
				if(element.className.length === 0) {
					element.removeAttribute("class");
				}
			}
		}
	}
})

Firebug.registerPanel(Firebug.FirePathPanel);
Firebug.registerModule(Firebug.FirePathPanel.LocationHighlightModule, Firebug.FirePathPanel.ResultHighlightModule);
Firebug.registerUIListener(Firebug.FirePathPanel.ResultHighlightModule);
}});
