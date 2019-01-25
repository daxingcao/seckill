class TableGenerate {

    constructor(contain, url, toolbar, type) {
        this.contain = contain;
        this.url = url;
        this.toolbar = toolbar;
        this.type = type;
    }

    createTable(columns) {
        this.contain.bootstrapTable({
            showColumns: true,
            method: 'post',
            pagination: true,
            uniqueId: 'id',
            search: true,
            pageList: [1, 5, 10, 20],
            pageSize: 5,
            toolbar: this.toolbar,
            sidePagination: this.type,
            paginationLoop: false,
            paginationPreText: '上一页',
            paginationNextText: '下一页',
            url: this.url,
            columns: columns,
            queryParams: function (params) {
                let data = {
                    pageNumber: this.pageNumber,
                    pageSize: this.pageSize,
                    order: params.order,
                    sort: params.sort
                }
                return data;
            }
        })
    }

    search(parameter) {
        this.contain.bootstrapTable('refreshOptions', {
            queryParams: function (params) {
                let datas = {
                    pageSize: this.pageSize,
                    pageNumber: this.pageNumber,
                    order: params.order,
                    sort: params.sort,
                    data: parameter
                }
                return datas;
            }
        })
    }

    getAllSelections() {
        return this.contain.bootstrapTable('getAllSelections');
    }

    getRowData(index){
        return this.contain.bootstrapTable('getRowByUniqueId',index);
    }

}