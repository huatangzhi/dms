
    var url;
    function addUser(){
        $('#dlg').dialog('open').dialog('setTitle','添加用户');
        $('#userName').removeAttr("readonly");
        $('#fm').form('clear');
        url = '/admin/addUser';
    }

    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
            if (!o[this.name].push) {
            o[this.name] = [ o[this.name] ];
        }
        o[this.name].push(this.value || '');
            } else {
        o[this.name] = this.value || '';
        }
    });
        return o;
    };

    function saveUser() {
    $.ajax({
            url: url,
            type: "POST",
            data : JSON.stringify($('#fm').serializeObject()),
            contentType:"application/json",
            success: function (result) {
                console.log(result.msg);
                if (result.msg == "用户添加成功") {
                    console.log("same");
                    $('#dlg').dialog('close');
                    $('#dg').datagrid('reload');
                }
                else {
                    $('#dlg').dialog('close');
                }
            },
            error: function () {
                alert(result.msg);
            }
        })
    }

    function removeUser(){
        var row = $('#dg').datagrid('getSelected');
        url = "/admin/delUser";
        if (row){
            console.log(row.name);
            $.messager.confirm('Confirm','确定要删除用户吗',function(r){
            if (r){
                $.post(url,{name:row.name},function(result){
                     if (result.msg == "用户删除成功"){
                        $('#dg').datagrid('reload');	// reload the user data
                    } else {
                        $.messager.show({	// show error message
                        title: 'Error',
                        msg: result.msg
                        });
                     }
                    });
                }
            });
        }
    }

    function editUser(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
        $('#dlg').dialog('open').dialog('setTitle','编辑用户');
        $('#fm').form('load',row);
        $('#userName').attr("readonly","true");
        url = '/admin/updateUser';
    }
}