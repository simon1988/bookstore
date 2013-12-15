Ext.define('HelloExt.controller.Orders', {
    extend: 'Ext.app.Controller',
    views: [
            'order.List'
        ],
    stores: [
             'Orders'
         ],

         models: ['Order'],
         
         init: function() {
     		console.log('Initialized Orders! This happens before the Application launch function is called');
             this.control({
                 'orderlist': {
                     itemdblclick: this.editOrder
                 },
     	        'Orderedit button[action=save]': {
     	            click: this.updateOrder
     	        }
             });
         },

         editOrder: function(grid, record) {
             console.log('Double clicked on ' + record.get('name'));
             var view = Ext.widget('Orderedit');

             view.down('form').loadRecord(record);
         },
         
         updateOrder: function(button) {
             var win    = button.up('window'),
                 form   = win.down('form'),
                 record = form.getRecord(),
                 values = form.getValues();

             record.set(values);
             win.close();
             this.getOrdersStore().sync();
         }

});