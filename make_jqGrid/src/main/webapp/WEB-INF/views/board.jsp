<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
	<link rel="stylesheet" href="/resources/jqGrid/css/ui.jqgrid.css">
</head>
<body>
	<!-- jqGrid -->
	<div class="board">
		<div class="boardBtnDiv">
			<button type="button" class="detailBtn">상세보기</button>
			<button type="button" class="insertBtn" onclick="location.href='/boardInsert'">등록</button>
		</div>
		<div class="tableTest">
			<table id="mainGrid"></table>
			<div id="pager"></div>
		</div>
	</div>
<script src="/resources/jqGrid/js/i18n/grid.locale-kr.js"></script>
<script src="/resources/jqGrid/js/minified/jquery.jqGrid.min.js"></script>
<script src="/resources/js/board.js"></script>
</body>
