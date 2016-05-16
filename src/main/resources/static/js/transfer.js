
function getXmlEditor(name) {
    var editor = ace.edit(name);
    editor.setTheme("ace/theme/monokai");
    editor.getSession().setMode("ace/mode/xml");
    editor.setOptions({maxLines: Infinity});
    editor.setPrintMarginColumn(1000);
    return editor;
}

function getTransferRequest(name, basicResponse) {
    var request = getXmlEditor(name);
    request.transfer = function () {
        $.ajax({
            beforeSend: function (xhrObj) {
                xhrObj.setRequestHeader("Content-Type", "application/xml");
                xhrObj.setRequestHeader("Accept", "application/xml");
            },
            type: "POST",
            url: "transfer",
            data: request.getValue(),
            dataType: "xml",
            success: function (response) {
                basicResponse.setValue(vkbeautify.xml(new XMLSerializer().serializeToString(response)));
            }
        });
    }
    return request;
}

var basicResponse = getXmlEditor("basicResponse");
var basicRequest = getTransferRequest("basicRequest", basicResponse);

var externalEntityResponse = getXmlEditor("externalEntityResponse");
var externalEntityRequest = getTransferRequest("externalEntityRequest", externalEntityResponse);

var expansionEntityResponse = getXmlEditor("expansionEntityResponse");
var expansionEntityRequest = getTransferRequest("expansionEntityRequest", expansionEntityResponse);