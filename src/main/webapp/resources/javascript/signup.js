
let msg = `${msg}`;
if (msg==Signup_ERR) alert("회원가입에 실패했습니다. 다시 시도해 주세요.");
function formCheck(frm) {
    var idmsg ='';
    var pwdmsg ='';
    var namemsg ='';

    if(frm.memberId.value.length<3) {
        setidMessage('id의 길이는 3이상이어야 합니다.', frm.memberId);
        return false;
    }
    if(frm.password.value.length<3) {
        setpwdMessage('pw의 길이는 3이상이어야 합니다.', frm.password);
        return false;
    }
    return true;
}

function setidMessage(idmsg, element){
    document.getElementById("idmsg").innerHTML = `<i class="fa fa-exclamation-circle"> ${idmsg}</i>`;

    if(element) {
        element.select();
    }
}
function setpwdMessage(pwdmsg, element){
    document.getElementById("pwdmsg").innerHTML = `<i class="fa fa-exclamation-circle"> ${pwdmsg}</i>`;

    if(element) {
        element.select();
    }
}