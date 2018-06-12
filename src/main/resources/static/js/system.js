$(function () {
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
            $.ajax({
                url: "/set-system",
                data: jsonString,
                type: "POST",
                contentType: 'application/json;charset=UTF-8',
                dataType: "JSON",
                success: function (data) {
                    if (data.code == 200) {
                        console.log("成功" + data.data);
                        window.location.href = "show-table.html";
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
    });
});
