<@cdx.html5 title="应用列表" body_style="background-color: ghostwhite;">
<div>
    <div class="common-panel">
        <div class="form-group search-contain">
            <div class="search-group col-md-3">
                <label for="client_name" class="control-label col-md-3 search-padding-format">应用名&emsp;:</label>
                <div class="col-md-7 search-padding-format">
                    <input type="text" class="form-control" id="client_name" placeholder="应用名">
                </div>
            </div>
            <div class="search-group col-md-3">
                <button class="btn btn-info" id="condition_search">查询</button>
                <button class="btn btn-warning" id="condition_reset">重置</button>
            </div>
        </div>
    </div>
    <div class="common-panel">
        <div>
            <div id="operate_button">
                <button id="add_client" class="btn btn-default"><i class="glyphicon glyphicon-plus"></i></button>
                <button id="batch_delete" class="btn btn-danger"><i class="glyphicon glyphicon-trash"></i></button>
            </div>
            <table id="table"></table>
        </div>
    </div>
</div>
<div class="modal" id="client_model">
    <div class="modal-content modal-content-format">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">添加应用</h4>
        </div>
        <div class="modal-body">
            <form id="client_form" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-4 control-label">Client Name</label>
                    <div class="col-sm-6">
                        <input type="text" name="clientName" class="form-control" placeholder="请输入应用名称" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Client ID</label>
                    <div class="col-sm-6">
                        <input type="text" name="clientId" class="form-control" placeholder="请输入应用ID" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">Client Secret</label>
                    <div class="col-sm-6">
                        <input type="password" name="clientSecret" class="form-control" placeholder="请输入应用Secret" />
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button id="submit_form" class="btn btn-success">提交</button>
        </div>
    </div>
</div>
<script src="/static/js/oauth/index.js"></script>
<script type="text/javascript">
    //初始化表格
    oauth.init();
    //根据条件查询数据
    $("#condition_search").on('click',function () {
        oauth.selectSearch();
    });
    $("#condition_reset").on('click',function () {
        oauth.conditionReset();
    });
    $("#batch_delete").on('click',function () {
        oauth.batchDelete();
    });
    $("#submit_form").on('click',function () {
        oauth.addClient();
    })
    $("#add_client").on('click',function () {
        $("#client_model").modal('show');
    })
</script>
</@cdx.html5>