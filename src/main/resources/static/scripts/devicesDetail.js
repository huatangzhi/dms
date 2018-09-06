
    var url;
    function addDevice(){

    $('#dlg').dialog('open').dialog('setTitle','添加设备');
    $('#deviceName').removeAttr("readonly");
    $('#fm').form('clear');
    url = '/user/addDevice';
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

    function saveDevice() {
    $.ajax({
            url: url,
            type: "POST",
            data : JSON.stringify($('#fm').serializeObject()),
            contentType:"application/json",
            success: function (result) {
                console.log(result.msg);
                if (result.msg == "设备添加成功") {
                    console.log("same");
                    $('#dlg').dialog('close');
                    $('#dg').datagrid('reload');
                } else if (result.msg == "该设备已存在") {
                    // console.log(result.msg);
                    // $('#dlg').dialog('close');
                    alert(result.msg);
                } else {

                    $('#dlg').dialog('close');
                }
            },
            error: function () {
                alert(result.msg);
            }
        }
    )
}

    function removeDevice(){
        var row = $('#dg').datagrid('getSelected');
        url = "/user/delDevice";
        if (row){
            console.log(row.name);
            $.messager.confirm('Confirm','确定要删除设备吗',function(r){
                if (r){
                    $.post(url,{name:row.name},function(result){
                        $('#dg').datagrid('reload');
                        // if (result.msg == "设备删除成功"){
                        //     	// reload the user data
                        // } else {
                        //     $.messager.show({	// show error message
                        //         title: 'Error',
                        //         msg: result.msg
                        //     });
                        // }
                    });
                }
            });
        }
    }

    function editDevice(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
    $('#dlg').dialog('open').dialog('setTitle','编辑设备');
    $('#fm').form('load',row);
    $('#deviceName').attr("readonly","true");
    url = '/user/updateDevice';
}
}

    function userLogout() {
        console("logout");
        $.post("/user/logout");
    }