let oauth = {
    URL: {
        addClient: function () {
            return '/admin/client/addClient.do';
        },
        delClient: function () {
            return '/admin/client/batchDeleteClient.do';
        },
        clientList: function () {
            return '/admin/client/clientList.do';
        }
    },
    init: function () {
        //创建表格对象
        var table = new TableGenerate($("#table"), this.URL.clientList(), '#operate_button', 'server');
        //初始化表格
        table.createTable(oauth.table.columns);
        //赋值给table字段,后续要用
        oauth.table.object = table;
    },
    selectSearch: function () {
        let parameter = {
            clientName: $("#client_name").val()
        };
        oauth.table.object.search(parameter);
    },
    conditionReset: function () {
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
                    console.log(data);
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
    addClient: function () {
        $.ajax({
            url: oauth.URL.addClient(),
            data: $("#client_form").serialize(),
            type: 'post',
            success: function (data) {
                console.log(data);
            }
        })
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
                align: 'center'
            }
        ]
    }
};
