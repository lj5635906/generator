$(function () {

    $(document).ready(function () {
        $(".fakeloader").fakeLoader({
            // timeToHide:10200,
            bgColor: "#6cd0f4",
            spinner: "spinner4"
        });
    });

    // 获取数据库所有表
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
                $(".fakeloader").hide();
            } else {
                $(".fakeloader").hide();
                swal({
                    title: "黄色警报！",
                    text: "获取数据表失败 , 请检查数据。",
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

    // 获取当前数据访问层框架 允许生成的模块
    $.ajax({
        url: "/modules",
        type: "GET",
        contentType: 'application/json;charset=UTF-8',
        dataType: "JSON",
        success: function (data) {
            if (data.code == 200) {
                var result = data.data;
                for (var i = 0; i < result.length; i++) {
                    var accessModule = "<label class='checkbox-inline'>"
                        + "<input type='checkbox' name='generatorModule' value='" + result[i] + "'> "
                        + result[i] + " </label>";
                    $("#access-module").append(accessModule);
                }
            } else {
                swal({
                    title: "黄色警报！",
                    text: "获取模块信息失败 , 请检查数据。",
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
            swal({
                title: "友情提示！",
                text: "请勾选需要生成的表。",
                timer: 2000,
                showConfirmButton: false
            });
            return;
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
            swal({
                title: "友情提示！",
                text: "请勾选需要生成的模块。",
                timer: 2000,
                showConfirmButton: false
            });
            return;
        }

        var data = {
            "tableNames": generator,
            "modules": modules
        };

        //data数据转为json字符串
        var jsonString = JSON.stringify(data);
        swal({
            title: "确定开始生成代码吗？",
            text: "确认后 , 将进行生成代码",
            icon: "info",
            buttons: true,
            dangerMode: true,
            buttons: {
                cancel: "取消",
                catch: {
                    text: "开始",
                    value: "catch",
                    closeModal: false,
                },
            }
        }).then(value => {
            return fetch("http://localhost:8080/generator", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: jsonString
            });
        }).then(result => {
                console.log(result)
            return result.status;
        }).then(code => {
                if(code == 200){
                swal({
                    title: "漂亮！",
                    text: "代码已经生成完毕 , 请开始你的表演吧。",
                    icon: "success",
                    showConfirmButton: false
                }).then((value) => {
                    // window.location.href = "show-table.html";
                });
            }else {
                swal({
                    title: "红色警报！",
                    text: "代码生成失败 , 请检查服务器。",
                    icon: "warning"
                });
            }
        }).catch(err => {
                if (err) {
                    swal({
                        title: "红色警报！",
                        text: "服务器出现异常 , 请检查服务器是否正常运行。",
                        icon: "error"
                    });
                } else {
                    swal.stopLoading();
            swal.close();
        }
        });
    })

});
