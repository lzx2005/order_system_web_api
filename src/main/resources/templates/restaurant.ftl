<#include "layout/layout.ftl"/>
<@layout>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=1dbfc0a150429d83820dac18eef985c9"></script>
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
                            <button type="button" class="btn btn-danger" onclick="restaurantScript.deleteRest('${(x.restaurantId)!}')">删除</button>
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
                    添加一个餐厅
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="exampleInputEmail1">名称</label>
                    <input type="text" class="form-control" id="name" placeholder="填写一个您的餐馆的名称">
                </div>

                <div class="form-group">
                    <label for="name">选择地点</label>
                    <div id="container" tabindex="0"></div>
                </div>


                <div class="form-group col-md-6">
                    <label for="exampleInputEmail1">标签</label>
                    <input type="text" class="form-control" id="tag" placeholder="填写一个您的餐馆的标签">
                </div>
                <div class="form-group col-md-6">
                    <label for="exampleInputEmail1">优惠</label>
                    <input type="text" class="form-control" id="preferential" placeholder="填写优惠信息">
                </div>

                <div class="form-group col-md-6">
                    <label for="exampleInputEmail1">评分</label>
                    <input type="text" class="form-control" id="score" placeholder="填写一个您的餐馆的评分">
                </div>
                <div class="form-group col-md-6">
                    <label for="exampleInputEmail1">月销售</label>
                    <input type="text" class="form-control" id="soldPerMonth" placeholder="填写您餐馆每月的销售数量">
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail1">图标</label>
                    <input type="file" class="form-control" id="fileInput" placeholder="上传图标"/>
                    <br/>
                    <img src="" id="avatar_img" style="display: none" width="30%">
                    <input type="hidden" class="form-control" id="avatar" placeholder="BASE64"/>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="restaurantScript.restCreateSubmit(this)">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
    var marker;
    $(function () {
        var restaurants = ${restaurants};
        var total = restaurants.data.total;
        var page = restaurants.data.pageNum;
        pagerScript.createRestaurantPager("#pager",total,page);


        var map = new AMap.Map('container',{
            resizeEnable: true,
            zoom: 15,
            center: [120.15255689620972, 30.26670878332787]
        });

        map.on('click', function(e) {
            if(typeof(marker)!="undefined"){
                map.remove(marker);
            }
            marker = new AMap.Marker({
                icon : 'http://vdata.amap.com/icons/b18/1/2.png',//24px*24px
                position : e.lnglat,
                offset : new AMap.Pixel(-12,-12),
                map : map
            });
        });
    });

    $("#fileInput").change(function(){
        var file = this.files[0];
        //判断类型是不是图片
        if(!/image\/\w+/.test(file.type)){

            swal("请确保文件为图像类型");
            $("#fileInput").val("");
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            image_base64=this.result.split(",")[1];
            //就是base64
            $("#avatar").val(image_base64)
            $("#avatar_img").attr("src","data:image/png;base64,"+image_base64);
            $("#avatar_img").css("display","block");
        }
    });
</script>
</@layout>