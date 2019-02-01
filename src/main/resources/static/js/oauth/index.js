let oauth = {
    init: function () {
        //创建表格对象
        var table = new TableGenerate($("#table"), this.URL.clientList(), '#operate_button', 'server');
        //初始化表格
        table.createTable(oauth.table.columns);
        //赋值给table字段,后续要用
        oauth.table.object = table;
        //给元素添加点击事件
        oauth.clickEvent();
    },
    selectSearch: function () {
        let parameter = {
            clientName: $("#client_name").val()
        };
        oauth.table.object.search(parameter);
    },
    resetRefresh: function () {
        oauth.table.object.search(null);
    },
    batchDelete: function () {
        let idList = oauth.commonMethod.extractId(this.table.object.getAllSelections(), 'id');
        if (idList.length > 0) {
            $.ajax({
                url: oauth.URL.delClient(),
                data: JSON.stringify(idList),
                contentType: 'application/json',
                type: 'post',
                success: function (data) {
                    if (data.success) {
                        oauth.table.object.search(null);
                    }
                }
            })
        } else {
            common.add_popover({
                element: $("#batch_delete"),
                direction: 'left',
                type: 'success',
                timeout: 1000,
                msg: '至少选择一项数据!'
            })
        }
    },
    addOrUpdateClient: function (isAdd) {
        $.ajax({
            url: isAdd ? oauth.URL.addClient() : oauth.URL.updateClient(),
            data: $("#client_form").serialize(),
            type: 'post',
            success: function (data) {
                if (data.success) {
                    $("#client_model").modal('hide');
                    oauth.resetRefresh();
                } else {
                    common.add_popover({
                        element: $("#client_modal_title"),
                        direction: 'left',
                        type: 'warning',
                        msg: data.msg
                    });
                }
            }
        })
    },
    showUpdateModal: function (index) {
        let data = this.table.object.getRowData(index);
        try {
            if (!common.is_empty(data.id)) {
                $("#client_modal_title").text("更新应用");
                oauth.clearInputVal('client_form');
                $("input[name='id']").val(index);
                $("input[name='clientName']").val(data.clientName);
                let ele = $("input[name='clientId']");
                ele.val(data.clientId);
                ele.attr('disabled', true);
                $("#client_model").modal('show');
            } else {
                throw new Error('主键id为空');
            }
        } catch (e) {
            console.log(e);
        }
    },
    clearInputVal: function (formId) {
        $("#" + formId + " input").val('');
    },
    clickEvent: function () {
        //根据条件查询数据
        $("#condition_search").on('click',function () {
            oauth.selectSearch();
        });
        //重置刷新
        $("#condition_reset").on('click',function () {
            oauth.resetRefresh();
        });
        //批量删除
        $("#batch_delete").on('click',function () {
            oauth.batchDelete();
        });
        //提交表单,添加应用
        $("#submit_form").on('click',function () {
            let id = $("input[name='id']").val();
            if(common.is_empty(id)){
                oauth.addOrUpdateClient(true);
            }else{
                oauth.addOrUpdateClient(false);
            }
        });
        //显示添加应用模态框
        $("#add_client").on('click',function () {
            oauth.clearInputVal('client_form');
            $("#client_modal_title").text("添加应用");
            $("input[name='clientId']").attr('disabled',false);
            $("#client_model").modal('show');
        });
    },
    URL: {
        addClient: function () {
            return '/admin/client/addClient.do';
        },
        delClient: function () {
            return '/admin/client/batchDeleteClient.do';
        },
        clientList: function () {
            return '/admin/client/clientList.do';
        },
        updateClient: function () {
            return '/admin/client/updateClient.do';
        }
    },
    commonMethod: {
        //提取数组中的单个字段组成新的数组
        extractId: function (arr, field) {
            let data = [];
            for (let i = 0; i < arr.length; i++) {
                data.push(arr[i][field]);
            }
            return data;
        }
    },
    table: {
        object: undefined,
        //字段列表
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            }, {
                title: '应用ID',
                field: 'clientId',
                align: 'center'
            }, {
                title: '应用名称',
                field: 'clientName',
                align: 'center'
            }, {
                title: '过期时间',
                field: 'expiryDate',
                align: 'center',
                sortable: true
            }, {
                title: '创建时间',
                field: 'createDate',
                align: 'center',
                sortable: true
            }, {
                title: '操作',
                align: 'center',
                formatter: function (value, row) {
                    let html = "<a onclick='oauth.showUpdateModal(" + row.id + ")'>修改</a>";
                    return html;
                }
            }
        ]
    }
};
