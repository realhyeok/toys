<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- board detail -->
      <div class="container">
        <h2>게시글 등록 화면</h2>
        <form id="board_insert_form">
          <table class="board_detail" style="width: 1019px;" border="1">
            <colgroup>
              <col width="10%">
              <col width="30%">
              <col width="10%">
              <col width="15%">
              <col width="10%">
              <col width="10%">
            </colgroup>
            <tbody>
              <tr>
                <td scope="row">제목</td>
                <td>
                  <input type="text" name="bdTitle">
                </td>
                <td scope="row">등록자</td>
                <td><input type="text" name="bdWriter" value="${userVO.nickname}" readonly></td>
                <td scope="row">비밀번호</td>
                <td>
                  <input type="text" name="bdPw">
                </td>
              </tr>
              <tr>
                <th scope="row">내용</th>
                <td colspan="5">
                  <textarea name="bdContent" id="" cols="125" rows="15"></textarea>
                </td>
              </tr>
              <tr>
                <th scope="row">첨부파일</th>
                <td colspan="5">
                  <input type="file" id="bdAttach" name="file"/>
                  <a href="#" name="file-delete">삭제</a>
                </td>
              </tr>
            </tbody>
          </table>

        </form>

        <div class="buttonList">
          <button type="button" class="saveBtn" form="bboard_insert_form">저장</button>
          <button type="button" class="deleteBtn">삭제</button>
          <button type="button" class="goList" onclick="location.href='/board'">목록</button>
        </div>

      </div>
      
<script>
    let saveBtn = document.querySelector('.saveBtn');

    saveBtn.addEventListener('click', () => {
      
      const boardFrm = document.getElementById('board_insert_form');

      if(boardFrm.bdPw.value === "") {
        Swal.fire({
          icon : "error",
          title : "비밀번호가 비어있습니다.",
          showConfirmButton : false,
          timer : 1500
        });
        return false;
      } else if(boardFrm.bdTitle.value === "") {
          Swal.fire({
            icon : "error",
            title : "제목을 정확히 입력하세요.",
            showConfirmButton : false,
            timer : 1500
          });
          return false;
        } 

      $.ajax({
        type: "post",
        url: "/boardInsert.do",
        enctype: 'multipart/form-data',
        data: $('#board_insert_form').serialize(),
        success: function (resp) {
          if(resp.success === "success"){
            alert("게시글이 등록되었습니다.")
            location.href = "/board";
          }
        },
        error: function (err) {
          console.log("boardInsert error : " + err.status);
        }
      });
    });

    $("a[name='file-delete']").on("click", function(e) {
            e.preventDefault();
            deleteFile($(this));
    });
    function deleteFile(obj) {
        document.getElementById('bdAttach').value = "";
    }

</script>