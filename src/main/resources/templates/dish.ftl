<#include "layout/layout.ftl"/>
<@layout>
    <h1 class="page-header">我的菜单</h1>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>名称</th>
                <th>价格</th>
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
            <#if dishes??&&dishes.data.numberOfElements gt 0>
                <#list dishes.data.content as x>
                    <tr>
                        <td>${(x.id)}</td>
                        <td>${(x.name)}</td>
                        <td>${(x.price)}元</td>
                        <td>${(x.type)}</td>
                        <td>${(x.createTime)}</td>
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
                在这里添加一些文本
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
    $(function () {
        var dishes = ${dishes};
        var total = dishes.data.totalElements;
        var page = dishes.data.number;
        pagerScript.createDishPager("#pager",total,page);
    });
</script>
</@layout>