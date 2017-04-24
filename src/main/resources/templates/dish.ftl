<#include "layout/layout.ftl"/>
<@layout>
    <h1 class="page-header">我的菜单</h1>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>名称</th>
                <th>价格(元/份)</th>
                <th>类型</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>-</td>
                <td>-</td>
                <td>-</td>
                <td>-</td>
                <td>-</td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createDishModal">添加一个菜品</button>
                </td>
            </tr>
            <#if dishes??&&dishes.data.size gt 0>
                <#list dishes.data.list as x>
                    <tr>
                        <td>${(x.id)!}</td>
                        <td>${(x.name)!}</td>
                        <td>${(x.price)!}</td>
                        <td>${(x.type_name)!}</td>
                        <td>${(x.create_time)!}</td>
                        <td>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editDishModal" onclick="writeToInput(this,${(x.type)!},'${(x.belong_rest)!}')">编辑</button>
                            <button type="button" class="btn btn-danger" onclick="dishScript.deleteDish(${(x.id)!})">删除</button>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </#if>
            </tbody>
        </table>
        <nav>
            <ul id="pager" class="pagination">
            </ul>
        </nav>
    </div>


<div class="modal fade" id="createDishModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加一个菜品
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="name">选择你的餐厅</label>
                    <select class="form-control" id="restaurantSelect">

                    </select>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">名称</label>
                    <input type="text" class="form-control" id="name" placeholder="名称">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">价格</label>
                    <input type="text" class="form-control" id="price" placeholder="价格">
                </div>

                <div class="form-group">
                    <label for="name">选择一个类型</label>
                    <select class="form-control" id="dishTypeSelect">

                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="dishScript.dishCreateSubmit(this)">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>



<div class="modal fade" id="editDishModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    编辑菜品
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="name">选择你的餐厅</label>
                    <select class="form-control" id="restaurantSelectEdit">

                    </select>
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1">名称</label>
                    <input type="text" class="form-control" id="editName" placeholder="名称">
                    <input type="hidden" class="form-control" id="dishId">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">价格</label>
                    <input type="text" class="form-control" id="editPrice" placeholder="价格">
                </div>

                <div class="form-group">
                    <label for="name">选择一个类型</label>
                    <select class="form-control" id="dishTypeSelectEdit">
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="dishScript.dishEditSubmit(this)">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
    $(function () {
        var dishes = ${dishes};
        var total = dishes.data.total;
        var page = dishes.data.pageNum;
        pagerScript.createDishPager("#pager",total,page);
        $.get("/console/rest/dishType/getAllMyDishType", {
                    typeName: "1"
                },
                function (data) {
                    if(data['code']==0){
                        console.log("成功");
                        var list = data['data'];
                        for(var i=0;i<list.length;i++){
                            var dishType = list[i];
                            $("#dishTypeSelect").append("<option value='"+dishType.typeId+"'>"+dishType.typeName+"</option>")
                            $("#dishTypeSelectEdit").append("<option value='"+dishType.typeId+"'>"+dishType.typeName+"</option>")
                        }
                    }else{
                        console.log("失败");
                        alert(data['msg']);
                    }
                }
        );

        $.get("/console/rest/restaurant/getAllMyRestaurant", {
                    typeName: "1"
                },
                function (data) {
                    if(data['code']==0){
                        console.log("成功");
                        var list = data['data'];
                        for(var i=0;i<list.length;i++){
                            var restaurant = list[i];
                            $("#restaurantSelect").append("<option value='"+restaurant.restaurantId+"'>"+restaurant.restaurantName+"</option>")
                            $("#restaurantSelectEdit").append("<option value='"+restaurant.restaurantId+"'>"+restaurant.restaurantName+"</option>")
                        }
                    }else{
                        console.log("失败");
                        alert(data['msg']);
                    }
                }
        );

    });

    $('#editDishModal').on('show.bs.modal', function () {
        console.log(11);
    })

    var writeToInput = function(e,typeId,rest){
        var children = $(e).parent().parent().children();
        console.log($(children[1]).text());
        $("#dishId").val($(children[0]).text());
        $("#editName").val($(children[1]).text());
        $("#editPrice").val($(children[2]).text());
        $("#dishTypeSelectEdit").val(typeId);
        $("#restaurantSelectEdit").val(rest);

    };
</script>
</@layout>