Ext.define('HelloExt.view.order.List' ,{
    extend: 'Ext.grid.Panel',
    alias: 'widget.orderlist',

    title: 'All orders',
    store: 'Orders',
    
    initComponent: function() {
        this.columns = [
                        {header: 'Id',  dataIndex: 'id',  flex: 1},
                        {header: 'Date', dataIndex: 'orderDate', flex: 1}
                    ];
        this.callParent(arguments);
    }
});