$(function () {

    $("#inputPackageName").on("keyup", function () {
        var packageName = $("#inputPackageName").val();
        var moduleName = $("#inputModuleName").val();
        if (moduleName) {
            $("#fullyDefinedPackageName").html(packageName + "." + moduleName);
        } else {
            $("#fullyDefinedPackageName").html(packageName);
        }
    });
    $("#inputModuleName").on("keyup", function () {
        var packageName = $("#inputPackageName").val();
        var moduleName = $("#inputModuleName").val();
        if (packageName) {
            $("#fullyDefinedPackageName").html(packageName + "." + moduleName);
        } else {
            $("#fullyDefinedPackageName").html(moduleName);
        }
    });

    // 测试连接
    $("#submitSystem").on("click", function () {
        var target = $("#inputTarget").val();
        var packageName = $("#inputPackageName").val();
        var moduleName = $("#inputModuleName").val();
        var dataAccessType = $("#inputDataAccessType").val();
        var skipNumTable = $("#inputSkipNumTable").val();
        var skipNumFiled = $("#inputSkipNumFiled").val();
        var data = {
            "target": target,
            "packageName": packageName,
            "moduleName": moduleName,
            "dataAccessType": dataAccessType,
            "skipNumTable": skipNumTable,
            "skipNumFiled": skipNumFiled
        };
        //data数据转为json字符串
        var jsonString = JSON.stringify(data);
        if (target && packageName && dataAccessType && skipNumTable && skipNumFiled) {
            swal({
                title: "确定设置系统相关参数吗？",
                text: "确认设置后 , 将开始生成代码",
                icon: "info",
                buttons: true,
                dangerMode: true,
            }).then((value) => {
                if (value) {
                    $.ajax({
                        url: "/set-system",
                        data: jsonString,
                        type: "POST",
                        contentType: 'application/json;charset=UTF-8',
                        dataType: "JSON",
                        success: function (data) {
                            if (data.code == 200) {
                                swal({
                                    title: "漂亮！",
                                    text: "系统参数信息正确 , 开始生成代码。",
                                    icon: "success",
                                    showConfirmButton: false
                                }).then((value) => {
                                    window.location.href = "show-table.html";
                                });
                            } else {
                                swal({
                                    title: "黄色警报！",
                                    text: "设置系统参数信息失败 , 请检查数据。",
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
    });
});
