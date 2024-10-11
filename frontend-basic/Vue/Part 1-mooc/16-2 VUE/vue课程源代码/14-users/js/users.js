const { reactive } = Vue;

const list = reactive([
    { id: 1, name: 'Lux', nickname: '光辉女郎', gender: '女' },
    { id: 2, name: 'Jax', nickname: '武器大师', gender: '男' },
    { id: 3, name: 'Ashe', nickname: '寒冰射手', gender: '女' },
    { id: 4, name: 'VN', nickname: '暗夜猎手', gender: '女' },
    { id: 5, name: 'Raze', nickname: '符文法师', gender: '男' }
]);

const user = reactive({
    id: '',
    name: '',
    nickname: '',
    gender: '',
    // 当 type=1时，添加新用户； type=2时，编辑用户信息
    type: 1
})

// 点击编辑按钮时，将当前行数据显示到表单中
// 核心： 根据 id 获取当前行数据，再赋值给 user 
function edit (id) {
    // 返回满足条件的单元值
    let tmp = list.find(item => item.id == id)
    
    user.id = tmp.id;
    user.name = tmp.name;
    user.nickname = tmp.nickname;
    user.gender = tmp.gender;
    user.type = 2;
}

// 点击确定按钮时，将修改数据更新回list数组
// 核心： 根据 user.id 找到 list 中的数据，再将user中的数据更新回list
function modify () {
    // 表单验证
    if (user.name.trim().length == 0) {
        alert('姓名必填');
        return;
    }

    if (user.nickname.trim().length == 0) {
        alert('昵称必填');
        return;
    }

    if (user.gender != '男' && user.gender != '女') {
        alert('性别必须是男或女');
        return;
    }

    let tmp = list.find(item => item.id == user.id);
    console.log(user);
    console.log(tmp);
    // 将 user 中的数据更新到 tmp 中
    tmp.name = user.name;
    tmp.nickname = user.nickname;
    tmp.gender = user.gender;

     // 清空表单
     user.id = '';
     user.name = '';
     user.nickname = '';
     user.gender = '';
     user.type = 1;
}

function del (id) {
    if(confirm('您确定删除该用户吗?')) {
        // findIndex: 返回满足条件的单元索引
        let index = list.findIndex(item => item.id == id)

        // 参数1: 要删除的单元索引
        // 参数2: 删除接下来的几个单元
        list.splice(index, 1);
    }
}

function add () {
    // 表单验证
    if (user.name.trim().length == 0) {
        alert('姓名必填');
        return;
    }

    if (user.nickname.trim().length == 0) {
        alert('昵称必填');
        return;
    }

    if (user.gender != '男' && user.gender != '女') {
        alert('性别必须是男或女');
        return;
    }

    list.push({
        id: list[list.length - 1].id + 1,
        name: user.name,
        nickname: user.nickname,
        gender: user.gender
    })

    // 清空表单
    user.name = '';
    user.nickname = '';
    user.gender = '';
}

function handle () {
    if (user.type == 1) {
        add();
    } else {
        modify();
    }
}

const app = {
    setup () {
        return { list, user, del, add, edit, modify, handle }
    }
}

Vue.createApp(app).mount('.main');