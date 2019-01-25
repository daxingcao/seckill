<#include "header.ftl" />
<#macro html5 title="默认标题" charset="utf-8" body_style="">
<!DOCTYPE html>
<head>
	<title>${title}</title>
	<meta http-equiv="Content-Type" charset="${charset}" content="text/html; charset=UTF-8">
	<@cdx.head/>
</head>
<body style="${body_style}">
	<#nested/>
</body>
</html>
</#macro>