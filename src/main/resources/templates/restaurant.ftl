<#include "layout/layout.ftl"/>
<@layout>
    <h1 class="page-header">我的餐厅</h1>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>名称</th>
                <th>坐标</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>-</td>
                <td>-</td>
                <td>-</td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createDishModal">创建一个餐厅</button>
                </td>
            </tr>
            <#if restaurants??&&restaurants.data.total gt 0>
                <#list restaurants.data.list as x>
                    <tr>
                        <td>${(x.restaurantName)!}</td>
                        <td>
                            <#list x.position as y>
                                 ${(y)!}
                            </#list>
                        </td>
                        <td>${(x.createTime?date)!}</td>
                        <td>
                            <button type="button" class="btn btn-primary">编辑</button>
                            <button type="button" class="btn btn-danger">删除</button>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr>
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
<script type="text/javascript">
    $(function () {
        var restaurants = ${restaurants};
        var total = restaurants.data.total;
        var page = restaurants.data.pageNum;
        pagerScript.createRestaurantPager("#pager",total,page);


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
                        }
                    }else{
                        console.log("失败");
                        alert(data['msg']);
                    }
                }
        );
    });
</script>
</@layout>