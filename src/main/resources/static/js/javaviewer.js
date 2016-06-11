function getJavaViewer(name) {
    var editor = ace.edit(name);
    editor.setTheme("ace/theme/monokai");
    editor.getSession().setMode("ace/mode/java");
    editor.setOptions({maxLines: Infinity});
    editor.setPrintMarginColumn(1000);
    editor.setReadOnly(true);
    return editor;
}

var rawSQLAuthenticationProvider = getJavaViewer("rawSQLAuthenticationProvider");
var preparedStatementAuthenticationProvider = getJavaViewer("preparedStatementAuthenticationProvider");