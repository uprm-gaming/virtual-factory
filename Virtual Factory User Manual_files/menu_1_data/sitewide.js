<!-- hide
function addToFavorites(title, url){
if (document.all)
window.external.AddFavorite(url, title);
else if (window.sidebar)
window.sidebar.addPanel(title, url, "")
}
// Popup window function
function newWindow(url) {
popupWindow = window.open(url,'popUpWindow','height=600,width=600,left=50,top=50,resizable=yes,scrollbars=1,toolbar=no,menubar=no,location=no,directories=no,status=no');
}

function codeInPopup(textareaId,currentView) {
var sCode = document.getElementById(textareaId).innerHTML;
/*sCode = sCode.replace(/(\r\n|\n|\r)/gm, "<br>");
sCode = sCode.replace(/\t/g, '&nbsp;&nbsp;&nbsp;&nbsp;');*/
var styleSheet = '';
if (currentView == 'Mobile') {
styleSheet = '/common/mobile/mobile.css';
}
else {
styleSheet = '/quackit.css';
}
sOutput = '<html><head><title>Code Example</title>';
sOutput = sOutput + '<link rel="stylesheet" href="' + styleSheet + '">';
sOutput = sOutput + '</head><body id="code-popup">';
sOutput = sOutput + '<textarea class="example-code" id="exampleCode">';
sOutput = sOutput + sCode;
sOutput = sOutput + '</textarea>';
sOutput = sOutput + '<p><a href="JavaScript:window.print();">Print</a> | <a href="javascript:self.close()">Close Window</a></p><p>Code from Quackit.com</p>';
sOutput = sOutput + '</body></html>';
var codeWindow = window.open('','codeWindow','height=600,width=600');
codeWindow.document.write(sOutput);
}
// end hide -->