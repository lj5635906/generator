$(function () {
    //=======================================点击按钮将值传入密码页面=============================================
    // 测试连接
    $("#verifyConfig").on("click", function () {
        var url = $("#inputUrl").val();
        var driverClassName = $("#inputDriverClassName").val();
        var username = $("#inputUsername").val();
        var password = $("#inputPassword").val();
        var data = {
            "url": url,
            "driverClassName": driverClassName,
            "username": username,
            "password": password
        };
        //data数据转为json字符串
        var jsonString = JSON.stringify(data);
        if (url && username && password && username) {
            $.ajax({
                url: "/verify-connection",
                data: jsonString,
                type: "POST",
                contentType: 'application/json;charset=UTF-8',
                dataType: "JSON",
                success: function (data) {
                    if (data.code == 200) {
                        alert("连接成功 , 数据正确")
                    } else {
                        alert("连接失败 , 请检查数据")
                    }
                },
                error: function (err) {
                }
            });
        } else {
            alert("请输入正确数据数据")
        }
    });

    $("#submitConfig").on("click", function () {
        var url = $("#inputUrl").val();
        var driverClassName = $("#inputDriverClassName").val();
        var username = $("#inputUsername").val();
        var password = $("#inputPassword").val();
        var data = {
            "url": url,
            "driverClassName": driverClassName,
            "username": username,
            "password": password
        };
        //data数据转为json字符串
        var jsonString = JSON.stringify(data);
        if (url && username && password && username) {
            $.ajax({
                url: "/set-datasource",
                data: jsonString,
                type: "POST",
                contentType: 'application/json;charset=UTF-8',
                dataType: "JSON",
                success: function (data) {
                    if (data.code == 200) {
                        console.log("成功" + data.data);
                        window.location.href = "system.html";
                    } else {
                        console.log(data.code);
                    }
                },
                error: function (err) {
                }
            });
        } else {
            alert("请输入正确数据数据")
        }
    })
});
