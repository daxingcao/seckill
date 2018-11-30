<#macro html5 title="默认标题" charset="utf-8">
<html>
<head>
	<title>${title}</title>
	<meta http-equiv="Content-Type" charset="${charset}" content="text/html; charset=UTF-8">
	<@cdx.head/>
</head>
<body>
	<#nested/>
</body>
</html>
</#macro>