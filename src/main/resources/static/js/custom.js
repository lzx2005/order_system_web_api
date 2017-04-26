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
    createRestaurantPager:function (pageContainer,total,pageIndex) {
        var pager = BootstrapPagination($(pageContainer), {
            layoutScheme: "firstpage,prevpage,pagenumber,nextpage,lastpage,righttext",
            //记录总数。
            total: total,
            pageSize: 10,
            //当前页索引编号。从其开始（从0开始）的整数。
            pageIndex: pageIndex,
            //当分页更改后引发此事件。
            pageChanged: function (pageIndex, pageSize) {
                window.location.href = "/console/restaurant?page="+pageIndex;
            },
        });
        return pager;
    },
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
                window.location.href = "/console/dish?page="+pageIndex;
            },
        });
        return pager;
    },
    createDishTypePager:function (pageContainer,total,pageIndex) {
        var pager = BootstrapPagination($(pageContainer), {
            layoutScheme: "firstpage,prevpage,pagenumber,nextpage,lastpage,righttext",
            //记录总数。
            total: total,
            pageSize: 10,
            //当前页索引编号。从其开始（从0开始）的整数。
            pageIndex: pageIndex,
            //当分页更改后引发此事件。
            pageChanged: function (pageIndex, pageSize) {
                window.location.href = "/console/dishType?page="+pageIndex;
            },
        });
        return pager;
    }
}

var alertScript = {
    alert:function (str) {
        swal(str);
    }
}

var restaurantScript = {
    restCreateSubmit:function (e) {
        $(e).attr("disabled","");
        var name = $("#name").val();
        var position = marker.getPosition();

        if(name.length>0){
            this.createRest(name,position);
        }else{
            $(e).removeAttr("disabled");
            swal("请输入类型名称");
        }
    },
    createRest:function(name,position){
        console.log(name,position);
        var lng = position.getLng();
        var lat = position.getLat();
        $.post("/console/rest/restaurant/create", {
                name: name,
                lng: lng,
                lat: lat
            },
            function (data) {
                if(data['code']==0){
                    console.log("成功");
                    swal({
                            title: "创建成功",
                            text: "创建成功！",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function(){
                            window.location.reload();
                        });
                }else{
                    console.log("失败");
                    swal(data['msg']);
                    $(e).removeAttr("disabled");
                }
            }
        );
    }
}

var dishScript = {
    dishCreateSubmit:function (e) {
        $(e).attr("disabled","");
        var name = $("#name").val();
        var price = $("#price").val();
        var type = $("#dishTypeSelect").val();
        var belongRest = $("#restaurantSelect").val();
        if(isNaN(price)){
            swal("价格必须是数字！");
            $(e).removeAttr("disabled");
            return;
        }
        if(name.length>0&&type>0){
            dishScript.createDish(name,price,type,belongRest,e)
        }else{
            $(e).removeAttr("disabled");
            swal("请输入类型名称");
        }
    },
    dishEditSubmit:function (e) {
        $(e).attr("disabled","");
        var dishId = $("#dishId").val();
        var name = $("#editName").val();
        var price = $("#editPrice").val();
        var type = $("#dishTypeSelectEdit").val();
        var belongRest = $("#restaurantSelectEdit").val();
        if(isNaN(price)){
            swal("价格必须是数字！");
            $(e).removeAttr("disabled");
            return;
        }
        if(name.length>0&&type>0){
            dishScript.editDish(dishId,name,price,type,belongRest,e)
        }else{
            $(e).removeAttr("disabled");
            swal("请输入类型名称");
        }
    },
    createDish:function (name,price,type,belongRest,e) {
        $.post("/console/rest/dish/create", {
                name: name,
                price: price,
                type: type,
                belongRest:belongRest
            },
            function (data) {
                if(data['code']==0){
                    console.log("成功");
                    swal({
                            title: "创建成功",
                            text: "创建成功！",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function(){
                            window.location.reload();
                        });
                }else{
                    console.log("失败");
                    swal(data['msg']);
                    $(e).removeAttr("disabled");
                }
            }
        );
    },
    editDish:function(dishId,name,price,type,belongRest,e){

        $.post("/console/rest/dish/edit", {
                dishId: dishId,
                name: name,
                price: price,
                type: type,
                belongRest:belongRest
            },
            function (data) {
                if(data['code']==0){
                    console.log("成功");
                    swal({
                            title: "修改成功",
                            text: "修改成功！",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        },
                        function(){
                            window.location.reload();
                        });
                }else{
                    console.log("失败");
                    swal(data['msg']);
                    $(e).removeAttr("disabled");
                }
            }
        );
    },
    deleteDish:function (dishId) {
        swal({
                title: "确认删除?",
                text: "确认删除这个菜品吗？删除后无法恢复",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "删除",
                cancelButtonText:"取消",
                closeOnConfirm: false
            },
            function(){
                $.post("/console/rest/dish/delete", {
                        dishId: dishId
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

var dishTypeScript = {
    dishTypeCreateSubmit:function (e) {
        $(e).attr("disabled","");
        var typeName = $("#name").val();
        if(typeName.length>0){
            dishTypeScript.createDishType(typeName)
        }else{
            $(e).removeAttr("disabled");
            swal("请输入类型名称");
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