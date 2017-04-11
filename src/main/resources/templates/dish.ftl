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
            <#if dishes??&&dishes.data.numberOfElements gt 0>
                <#list dishes.data.content as x>
                    <tr>
                        <td>${(x.id)}</td>
                        <td>${(x.name)}</td>
                        <td>${(x.price)}</td>
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
<script type="text/javascript">
    $(function () {
        var dishes = ${dishes};
        //console.log(total);
        var total = dishes.data.totalElements;
        var page = dishes.data.number;
        pagerScript.createDishPager("#pager",total,page);
    });
</script>
</@layout>