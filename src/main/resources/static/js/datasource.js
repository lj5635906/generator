$(function () {
    //=======================================点击按钮将值传入密码页面=============================================
    // 测试连接
    $("#verifyConfig").on("click", function () {
        var dbType = $("#inputDbType").val();
        var host = $("#inputHost").val();
        var port = $("#inputPort").val();
        var databaseName = $("#inputDatabaseName").val();
        var username = $("#inputUsername").val();
        var password = $("#inputPassword").val();
        var data = {
            "dbType": dbType,
            "host": host,
            "port": port,
            "databaseName": databaseName,
            "username": username,
            "password": password
        };
        //data数据转为json字符串
        var jsonString = JSON.stringify(data);
        if (host && port && databaseName && password && username) {
            $.ajax({
                url: "/set-datasource",
                data: jsonString,
                type: "POST",
                contentType: 'application/json;charset=UTF-8',
                dataType: "JSON",
                success: function (data) {
                    if (data.code == 200) {
                        swal({
                            title: "漂亮！",
                            text: "连接成功 , 数据正确。",
                            icon: "success"
                        });
                    } else {
                        swal({
                            title: "黄色警报！",
                            text: "连接失败 , 请检查数据。",
                            icon: "warning"
                        });
                    }
                },
                error: function (err) {
                    console.log(err)
                    swal({
                        title: "红色警报！",
                        text: "服务器出现异常 , 请检查服务器是否正常运行。",
                        icon: "error"
                    });
                }
            });
        } else {
            swal({
                title: "友情提示！",
                text: "请填写完整数据源信息。",
                timer: 2000,
                showConfirmButton: false
            });
        }
    });

    $("#submitConfig").on("click", function () {
        var dbType = $("#inputDbType").val();
        var host = $("#inputHost").val();
        var port = $("#inputPort").val();
        var databaseName = $("#inputDatabaseName").val();
        var username = $("#inputUsername").val();
        var password = $("#inputPassword").val();
        var data = {
            "dbType": dbType,
            "host": host,
            "port": port,
            "databaseName": databaseName,
            "username": username,
            "password": password
        };
        //data数据转为json字符串
        var jsonString = JSON.stringify(data);
        if (host && port && databaseName && password && username) {
            swal({
                    title: "确定设置数据源吗？",
                    text: "确认设置后 , 将进行设置系统参数",
                    icon: "info",
                    buttons: true,
                    dangerMode: true,
                }).then((value) => {
                    if (value) {
                        $.ajax({
                            url: "/set-datasource",
                            data: jsonString,
                            type: "POST",
                            contentType: 'application/json;charset=UTF-8',
                            dataType: "JSON",
                            success: function (data) {
                                if (data.code == 200) {
                                    swal({
                                        title: "漂亮！",
                                        text: "数据源信息正确 , 开始设置系统参数。",
                                        icon: "success",
                                        showConfirmButton: false
                                    }).then((value) => {
                                        window.location.href = "system.html";
                                    });
                                } else {
                                    swal({
                                        title: "黄色警报！",
                                        text: "设置数据源失败 , 请检查数据。",
                                        icon: "warning"
                                    });
                                }
                            },
                            error: function (err) {
                                console.log(err)
                                swal({
                                    title: "红色警报！",
                                    text: "服务器出现异常 , 请检查服务器是否正常运行。",
                                    icon: "error"
                                });
                            }
                        });
                    }
                });
        } else {
            swal({
                title: "友情提示！",
                text: "请填写完整数据源信息。",
                timer: 2000,
                showConfirmButton: false
            });
        }
    })
});
