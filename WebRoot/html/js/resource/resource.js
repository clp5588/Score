function find(a){
    if($(a).hasClass("add")){
        $(a).removeClass("add").html("<i class='icon-plus-sign'></i>添加");
        $("#res_table").show("blind",{},500);
        $("#res_form").hide("blind",{},500);
        $("#sub").hide();
        $("#res_search").show("slide",{},500);
        $("#pager").show();
    }else{
        $(a).addClass("add").html("<i class='icon-minus-sign'></i>取消");
        $("#res_table").hide("blind",{},500);
        $("#res_form").show("blind",{},500);
        $("#sub").show("slide",{},500);
        $("#res_search").hide();
        $("#res_form input,#res_form select").val("");
        $("#iconDiv").empty();
        $("#pager").hide();
    }
}
function sub(){
    if($("#res_form").valid()){
        var resName = $("#resName").val();
        var resUrl = $("#resUrl").val();
        var resIcon = $("#resIcon").val();
        var resParentId = $("#resParentId").val();
        var resType = $("#resType").val();
        var t = document.getElementById("resAuth");
        var resAuth = t.options[t.selectedIndex].value;
        if(resParentId.split("|")[1]==-1){
            if(resIcon==""){
                $.globalMessenger().post({
                    message : "挂载顶级模块,图片不能为空!",
                    type : "error",
                    id : "resIcon"
                });
                return;
            }
        }
        var resId=$("#resId").val();
        if(resId!=""){
            $.ajax({
                url:"/sys/resource/edit.json",
                data:{
                    resId:resId,
                    resName:resName,
                    resUrl:resUrl,
                    resIcon:resIcon,
                    resParentId:resParentId.split("|")[0],
                    resType:resType,
                    resAuth:resAuth
                },
                dataType:"json",
                type:"post",
                success:function(data){
                    if(data=="success"){
                        $.globalMessenger().post({
                            message: "修改成功",
                            type: 'info',
                            hideAfter:1.5
                        });
                    }else{
                        $.globalMessenger().post({
                            message: "修改失败",
                            type: 'error',
                            hideAfter:1.5
                        });
                    }
                    $("#main").load("/sys/resource/manage.json");
                },
                error:function(){
                    $.globalMessenger().post({
                        message: "修改失败",
                        type: 'error',
                        hideAfter:1.5
                    });
                    $("#main").load("/sys/resource/manage.json");
                }
            });
        }else{
            $.ajax({
                url:"/sys/resource/add.json",
                data:{
                    resName:resName,
                    resUrl:resUrl,
                    resIcon:resIcon,
                    resParentId:resParentId.split("|")[0],
                    resType:resType,
                    resAuth:resAuth
                },
                dataType:"json",
                type:"post",
                success:function(data){
                    if(data=="success"){
                        $.globalMessenger().post({
                            message: "添加成功",
                            type: 'info',
                            hideAfter:1.5
                        });
                    }else{
                        $.globalMessenger().post({
                            message: "添加失败",
                            type: 'info',
                            hideAfter:1.5
                        });
                    }
                    $("#main").load("/sys/resource/manage.json");
                },
                error:function(){
                    $.globalMessenger().post({
                        message: "添加失败",
                        type: 'error',
                        hideAfter:1.5
                    });
                    $("#main").load("/sys/resource/manage.json");
                }
            });
        }
    }
}
$(function(){
    $("#fset_icons ul li").click(function(){
        $("#fset_icons").hide();
        $(this).show();
        $("#resIcon").val($(this).find("i").attr("class"));
        $("#iconDiv").empty().append($(this));
        $(this).click(function(){
            $("#fset_icons").toggle();
            $(this).show();
        });
    });
    $("#resParentId").change(function(){
        $("#ff_icon").hide();
        $("#fset_icons").hide();
        if($(this).val()==""){
            $("#resType").val("");
            $("#resAuth").val("");
            return;
        }
        var is_module = $(this).val().split("|")[1];
        if(is_module==-1){
            $("#ff_icon").show();
            $("#fset_icons").show();
            $("#resType").val("1");
            $("#resAuth").val("1");
            $("#resAuth").attr("disabled",true);
        }else if(is_module=="1"){
            $("#ff_icon").show();
            $("#fset_icons").show();
            $("#resType").val("0");
            $("#resAuth").val("2");
            $("#resAuth").attr("disabled",false);
        }else if(is_module=="0"){
            $("#resType").val("-1");
            $("#resAuth").val("0");
            $("#resAuth").attr("disabled",true);
        }
    });
    $("#resAuth option[value='1']").attr("disabled",true);
    $("#resAuth option[value='0']").attr("disabled",true);
    $._messengerDefaults = {
        extraClasses : 'messenger-fixed messenger-theme-air messenger-on-bottom messenger-on-right'
    };
    $("#res_form").validate({
        rules : {
            resName : {
                required : true
            },
            resUrl : {
                required : true
            },
            resParentId : {
                required : true
            },
            resType : {
                required : true
            },
            resAuth : {
                required : true
            }
        },
        messages:{
            resName : {
                required : "资源名称不能为空!"
            },
            resUrl : {
                required : "资源URL不能为空!"
            },
            resParentId : {
                required : "资源挂载点不能为空!"
            },
            resType : {
                required : "资源类型不能为空!"
            },
            resAuth : {
                required : "资源权限不能为空!"
            }
        },
        highlight : function(element, errorClass, validClass) {
            $(element).attr("required",true).focus();
        },
        unhighlight : function(element, errorClass, validClass) {
            $(element).attr("required",false);
        },
        errorPlacement : function(error, element) {
            var id = $(element).attr("id");
            $.globalMessenger().post({
                message : $(error).html(),
                type : "error",
                id : id
            });
        }
    });
    $("#pager ul li").click(function(){
        if($(this).hasClass("active")||$(this).hasClass("disabled")){
            return;
        }
        var page = $(this).text();
        if(page=="»"||page=="«"){
            page=$(this).find("a").attr("data");
        }
        $("#main").load('/sys/resource/manage.json',{current:page});
    });
    $("#sel").click(function(){
        var resName = $("#selName").val();
        $("#main").load('/sys/resource/manage.json',{resName:resName});
    });
});
function del(id){
    $.ajax({
        url:'/sys/resource/delete.json',
        data:{id:id},
        dataType:"json",
        type:'post',
        success:function(data){
            if(data=="success"){
                $.globalMessenger().post({
                    message: "删除成功",
                    type: 'info',
                    hideAfter:1.5
                });
            }else{
                $.globalMessenger().post({
                    message: "删除失败",
                    type: 'error',
                    hideAfter:1.5
                });
            }
            $("#main").load("/sys/resource/manage.json");
        },
        error:function(){
            $.globalMessenger().post({
                message: "删除失败",
                type: 'error',
                hideAfter:1.5
            });
            $("#main").load("/sys/resource/manage.json");
        }
    })
}
function mod(id,name,url,icon,parentId,is_module,auth){
    $("#addbtn").trigger('click');
    $("#resId").val(id);
    var p_is_module;
    if(is_module=="0"){
        p_is_module=1;
    }else if(is_module=="1"){
        p_is_module=-1;
    }else if(is_module=="-1"){
        p_is_module=0;
    }
    $("#resParentId").val(parentId+"|"+p_is_module);
    $("#resParentId").trigger("change");
    $("#resType").val(is_module);
    $("#resAuth").val(auth);
    $("#resName").val(name);
    $("#resUrl").val(url);
    $("#resIcon").val(icon);
    var $icon=$("<li><i class='"+icon+"'></i>"+icon+"</li>");
    $("#iconDiv").empty().append($icon);
    $($icon).click(function(){
        $("#fset_icons").toggle();
        $(this).show();
    });
}
