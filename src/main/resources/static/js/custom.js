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