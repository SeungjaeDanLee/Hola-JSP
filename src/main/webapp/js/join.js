var isCheckId = false;
var isCheckNickName = false;

function checkForm() {
    var id = $("[name=user-id]");
    var pw = $("[name=user-pw]");
    var pwck = $("[name=user-pwck]");
    var email = $("[name=user-email]");
    var name = $("[name=user-name]");
    var nickname = $("[name=user-nickname]");
    var phone = $("[name=user-phone]");
    var phoneNumber = phone.val().replace(/[^0-9]/g, '');

    if(id.val() == "") {
        alert("아이디를 입력해주세요.");
        id.focus();
        return false;
    } else if(pw.val() == "") {
        alert("비밀번호를 입력해주세요.");
        pw.focus();
        return false;
    } else if(pw.val() != pwck.val()) {
        alert("비밀번호가 일치하지 않습니다.");
        pwck.focus();
        return false;
    } else if(email.val() == "") {
        alert("이메일을 입력해주세요.");
        email.focus();
        return false;
    } else if(name.val() == "") {
        alert("이름을 입력해주세요.");
        name.focus();
        return false;
    } else if(nickname.val() == "") {
        alert("닉네임을 입력해주세요.");
        nickname.focus();
        return false;
    } else if(phone.val() == "") {
        alert("전화번호를 입력해주세요.");
        phone.focus();
        return false;
    } else if (phoneNumber.length !== 11) {
        alert("전화번호를 숫자 11자리로 입력해주세요.");
        phone.focus();
        return false;
    } 
    
	 if (!isCheckId) {
        alert("아이디 중복체크를 확인해주세요.");
        id.focus();
        return false;
    } 
	if (!isCheckNickName) {
        alert("닉네임 중복체크를 확인해주세요.");
        nickname.focus();
        return false;
    } 
	
    $("[name=member]").submit();
}

function checkId(){
    var inputId = $("[name=user-id]").val();
    $.ajax({
        type: "post",
        url: "/JSP_Project_Team2/checkId",
        data: {"inputId": inputId},
        success: function(data){
            console.log(data);
            $("#result1").text(data);
            isCheckId = true;
        }, error: function(){
            console.log("서버 요청 실패");
            isCheckId = false;
        }
    });
}

function checkNickName(){
    var inputNickName = $("[name=user-nickname]").val();
    $.ajax({
        type: "post",
        url: "/JSP_Project_Team2/checkNickName",
        data: {"inputNickName": inputNickName},
        success: function(data){
            console.log(data);
            $("#result2").text(data);
            isCheckNickName = true;
        }, error: function(){
            console.log("서버 요청 실패");
            isCheckNickName = false;
        }
    });
}