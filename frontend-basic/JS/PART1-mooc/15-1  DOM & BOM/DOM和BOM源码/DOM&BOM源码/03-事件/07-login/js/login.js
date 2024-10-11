// 获取文本框\密码框对象
var account = document.querySelector('#account');
var pwd = document.querySelector('#pwd');

// 1. 文本框\密码框获取焦点时，边框变为桔黄色
account.onfocus = function () {
    this.style.border = "1px solid orange";
}
pwd.onfocus = function () {
    this.style.border = "1px solid orange";
}

// 2. 文本框\密码框失去焦点时，验证账号\密码长度，边框颜色变回
// 账号长度 6-12 位； 密码长度 8-16 位
account.onblur = function () {
    if (this.value.length < 6 || this.value.length > 12) {
        alert('账号长度不正确，请重新输入')
    }
    this.style.border = "";
}
pwd.onblur = function () {
    if (this.value.length < 8 || this.value.length > 18) {
        alert('密码长度不正确，请重新输入')
    }
    this.style.border = "";
}

// 3. 表单提交时，验证账号\密码长度，未通过验证则阻止表单提交
document.querySelector('form').onsubmit = function (e) {
    if (account.value.length < 6 || account.value.length > 12) {
        alert('账号长度不正确，请重新输入');
        return false;
    }
    if (pwd.value.length < 8 || pwd.value.length > 18) {
        alert('密码长度不正确，请重新输入');
        return false;
    }

    // 阻止表单提交动作
    // e.preventDefault();
    // return false;
}