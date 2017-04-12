<#include "layout/layout.ftl"/>
<@layout>
    <h1 class="page-header">我的菜品类型</h1>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
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
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createDishTypeModal">添加一个菜品类型</button>
                </td>
            </tr>
            <#if dishTypes??&&dishTypes.data.numberOfElements gt 0>
                <#list dishTypes.data.content as x>
                    <tr>
                        <td>${(x.typeId)!}</td>
                        <td>${(x.typeName)!}</td>
                        <td>${(x.createTime)!}</td>
                        <td>
                            <button type="button" class="btn btn-danger" onclick="dishTypeScript.deleteDishType(${(x.typeId)!})">删除</button>
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


<div class="modal fade" id="createDishTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加一个菜品类型
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="exampleInputEmail1">名称</label>
                    <input type="text" class="form-control" id="name" placeholder="名称">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="dishTypeScript.dishTypeCreateSubmit()">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
    $(function () {
        var dishTypes = ${dishTypes};
        var total = dishTypes.data.totalElements;
        var page = dishTypes.data.number;
        pagerScript.createDishPager("#pager",total,page);
    });
</script>
</@layout>