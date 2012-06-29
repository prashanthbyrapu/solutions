Ext.require([	]);

Ext.application({
	name:'HelloExt',
	launch: function(){
		Ext.create('Ext.container.Viewport',{
			layout:'fit',
			item:[
			{
				title: 'Admission Form',
			}
				]
		}
	}
});
		/*	{
					xtype:'Form',
					layout:'Form',
					collapsible:'true',
					title:'Personal Information',
					bodyPadding: ' 5 5 0',
					width:400
					fieldDefaults*/