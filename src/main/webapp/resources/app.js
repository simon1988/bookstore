Ext.application({
	requires: ['Ext.container.Viewport'],
	name : 'HelloExt',
	appFolder: 'resources/app',
	
    controllers: [
                  'Orders'
              ],
              
	launch : function() {
		Ext.create('Ext.container.Viewport', {
			layout : 'fit',
			items : [ {
				title : 'Hello Ext',
				html : 'Hello! Welcome to Ext JS.',
				xtype: 'orderlist'
			} ]
		});
	}
});