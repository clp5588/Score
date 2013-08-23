function find(a){
    if($(a).hasClass("add")){
        $(a).removeClass("add").html("<i class='icon-plus-sign'></i>添加");
        $("#res_table").show("blind",{},500);
        $("#res_form").hide("blind",{},500);
        $("#sub").hide();
        $("#res_search").show("slide",{},500);
    }else{
        $(a).addClass("add").html("<i class='icon-minus-sign'></i>取消");
        $("#res_table").hide("blind",{},500);
        $("#res_form").show("blind",{},500);
        $("#sub").show("slide",{},500);
        $("#res_search").hide();
        $("#res_form input,#res_form select").val("");
        $("#iconDiv").empty();
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
        if(resParentId.split("|")[1]=-1){
            if(resIcon==""){
                $.globalMessenger().post({
                    message : "挂载顶级模块,图片不能为空!",
                    type : "error",
                    id : "resIcon"
                });
                return;
            }
        }
        $("#main").load("/sys/resource/add.json",{
            resName:resName,
            resUrl:resUrl,
            resIcon:resIcon,
            resParentId:resParentId.split("|")[0],
            resType:resType,
            resAuth:resAuth
        },function(response,status,xhr){
            alert(response);
        });
    }
}
$(function(){
    $("#fset_icons ul li").click(function(){
        $("#fset_icons").hide();
        $(this).show();
        $("#resIcon").val($(this).find("i").attr("class"));
        $("#iconDiv").empty().append($(this)).find("li");
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
});