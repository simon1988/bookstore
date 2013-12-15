Ext.define('HelloExt.store.Orders', {
    extend: 'Ext.data.Store',
    model: 'HelloExt.model.Order',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: 'ajax/getAllOrders.do'
        },
        reader: {
            type: 'json',
            root: ''
        }
    }
});