let searchResultColNames = ['순서', '제목', '작성자', '등록일'];
let searchResultColModel = [
  { name: "bdNo", index: "bdNo", align: "center", width : "12%" },
  { name: "bdTitle", index: "bdTitle", align: "left", width: "50%" },
  { name: "bdWriter", index: "bdWriter", align: "center", width: "12%" },
  { name: "bdRegdate", index: "bdRegdate", align: "center", width: "14%", formatter:'date', formatoptions: {srcformat: 'U/1000', newformat:'Y/m/d H:i:s'}},
];


$(document).ready(function () {
  test();
});

function test(){	
  // $.ajax({
  //   type: "post",
  //   url: "/getBoardDatas",
  //   dataType: "json",
  //   success: function (resp) {
  //     console.log(resp);
  //     resp.forEach((e) => {
  //       console.log(e);
  //       console.log(e.bdTitle);
  //     });
  //   }
  // });
  
	$("#mainGrid").jqGrid({
    url : '/getBoardDatas',
		datatype : "json",
		mtype: "post",
		colNames: searchResultColNames,
		colModel: searchResultColModel,
		rowNum: 10,
		pager: "#pager",
    emptyrecords : "데이터가 없습니다.",
		caption: `총 : 미구현`,
		multiselect : true,
		multiboxonly : true,
		sortname : 'bdNo',
		sortorder : 'desc',
		gridview : true,
		width: 1019,
		height: 261
	});
}


$('.detailBtn').click(function (e) { 
  e.preventDefault();
  let info = $("#mainGrid").jqGrid("getGridParam", "selrow");
  let rowInfo = $("#mainGrid").jqGrid('getRowData', info);
  if(rowInfo.bdNo == null) return false;
  location.href = "/boardDetail.do?bdNo=" + rowInfo.bdNo;
});
