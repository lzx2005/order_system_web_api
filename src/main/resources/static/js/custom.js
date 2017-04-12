/*$(function () {
    var demo1 = BootstrapPagination($("#pager"), {
        layoutScheme: "firstpage,prevpage,pagenumber,nextpage,lastpage,righttext",
        //记录总数。
        total: 101,
        //当前页索引编号。从其开始（从0开始）的整数。
        pageIndex: 2,
        //当分页更改后引发此事件。
        pageChanged: function (pageIndex, pageSize) {
            alert("page changed. pageIndex:" + pageIndex + ",pageSize:" + pageSize)
        },
    });
});*/

var pagerScript = {
    createDishPager:function (pageContainer,total,pageIndex) {
        var pager = BootstrapPagination($(pageContainer), {
            layoutScheme: "firstpage,prevpage,pagenumber,nextpage,lastpage,righttext",
            //记录总数。
            total: total,
            pageSize: 10,
            //当前页索引编号。从其开始（从0开始）的整数。
            pageIndex: pageIndex,
            //当分页更改后引发此事件。
            pageChanged: function (pageIndex, pageSize) {
                alert("page changed. pageIndex:" + pageIndex + ",pageSize:" + pageSize)
            },
        });
        return pager;
    }
}

var alertScript = {
    createDish:function () {
        swal({
                title: "创建一个菜品",
                showCancelButton: true,
                closeOnConfirm: false,
                text: 'A custom <input placeholder="sad"/> message.',
                html: true
            },
            function(){
                swal("Nice!", "You wrote: success");
            });
    },
    editDish:function (dishId) {
        
    },
    deleteDish:function (dishId) {
        
    }
}

var dishTypeScript = {
    dishTypeCreateSubmit:function () {
        var typeName = $("#name").val();
        if(typeName.length>0){
            dishTypeScript.createDishType(typeName)
        }else{
            alert("请输入类型名称");
        }
    },
    createDishType:function (typeName) {
        console.log(typeName);
        $.post("/console/rest/dishType/create", {
                typeName: typeName
            },
            function (data) {
                if(data['code']==0){
                    console.log("成功");
                    window.location.reload();
                }else{
                    console.log("失败");
                    alert(data['msg']);
                }
            }
        );
    },
    deleteDishType:function (typeId) {
        swal({
            title: "确认删除?",
            text: "确认删除这个类型吗？删除后无法恢复",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText:"取消",
            closeOnConfirm: false
        },
        function(){
            $.post("/console/rest/dishType/delete", {
                    typeId: typeId
                },
                function (data) {
                    if(data['code']==0){
                        console.log("成功");
                        swal({
                            title: "删除成功",
                            text: "删除成功！",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function(){
                            window.location.reload();
                        });
                    }else{
                        console.log("失败");
                        swal({
                            title: "删除失败",
                            text: "删除失败！"+data['msg'],
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function(){
                        });
                    }
                }
            );
        });



    }
}