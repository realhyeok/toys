let searchResultColNames = ['게시글관리번호', '번호', '제목', '작성자', '날짜', '조회수'];
let searchResultColModel = [
  { name: "bbsMgtNo", index: "bbsMgtNo", align: "center", hidden: true },
  { name: "bbsNum", index: "bbsNum", align: "left", width: "12%" },
  { name: "bbsTitle", index: "bbsTitle", align: "center", width: "50%" },
  { name: "bbsWriter", index: "bbsWriter", align: "center", width: "14%" },
  { name: "bbsDate", index: "bbsDate", align: "center", width: "12%" },
  { name: "bbsHit", index: "bbsHit", align: "center", width: "12%" },
];

$(function () {
  $("#mainGrid").jqGrid({
    colNames: searchResultColNames,
    colModel: searchResultColModel,
    rowNum: 10,
    pager: "pager",
    caption: "게시판"
  });


});
