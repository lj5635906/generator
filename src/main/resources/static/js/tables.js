$(function () {

    $.ajax({
        url: "/tables",
        type: "GET",
        contentType: 'application/json;charset=UTF-8',
        dataType: "JSON",
        success: function (data) {
            if (data.code == 200) {
                var result = data.data;
                for (var i = 0; i < result.length; i++) {
                    $(".table").append("<tr>"
                        + "<td><input type='checkbox' name='tableName' value='" + result[i].name + "' /></td>"
                        + "<td>" + result[i].name + "</td>"
                        + "<td>" + result[i].comment + "</td>"
                        + "</tr>"
                    )
                }
            } else {
                console.log(data.code);
            }
        },
        error: function (err) {
        }
    });

    // checkbox 全选、反选
    $("#table-all").click(function () {
        var checkStatus = $("#table-all").is(":checked");
        if (checkStatus) {
            var ids = document.getElementsByName("tableName");
            for (var i = 0; i < ids.length; i++) {
                ids[i].checked = true;
            }
        } else {
            var ids = document.getElementsByName("tableName");
            for (var i = 0; i < ids.length; i++) {
                ids[i].checked = false;
            }
        }
    })

    // 生成代码
    $(".btn").click(function () {
        // 需要生成对应的表
        var id = document.getElementsByName('tableName');
        var generator = new Array();
        for (var i = 0; i < id.length; i++) {
            if (id[i].checked) {
                generator.push(id[i].value);
            }
        }
        if (generator.length == 0) {
            alert("请选择需要创建的表")
        }

        // 需要生成的模块
        var id = document.getElementsByName('generatorModule');
        // 需要生成对应的表
        var modules = new Array();
        for (var i = 0; i < id.length; i++) {
            if (id[i].checked) {
                modules.push(id[i].value);
            }
        }
        if (modules.length == 0) {
            alert("请选择需要生成的模块")
        }

        var data = {
            "tableNames": generator,
            "modules": modules
        };

        //data数据转为json字符串
        var jsonString = JSON.stringify(data);
        $.ajax({
            url: "/generator",
            data: jsonString,
            type: "POST",
            contentType: 'application/json;charset=UTF-8',
            dataType: "JSON",
            success: function (data) {
                if (data.code == 200) {
                    alert("代码生成完毕")
                    window.location.href = "system.html";
                } else {
                    console.log(data.code);
                }
            },
            error: function (err) {
            }
        });
    })

});
