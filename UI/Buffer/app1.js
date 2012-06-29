Ext.require([
    'Ext.form.*',
    'Ext.layout.container.Column',
    'Ext.tab.Panel.*',
	'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*'
]);

Ext.application({
    name: 'HelloExt',
    launch: function() {

		Ext.define('Company', {
    extend: 'Ext.data.Model',
    fields: [
       {name: 'company'},
       {name: 'price',      type: 'float', convert: null,     defaultValue: undefined},
       {name: 'change',     type: 'float', convert: null,     defaultValue: undefined},
       {name: 'pctChange',  type: 'float', convert: null,     defaultValue: undefined},
       {name: 'lastChange', type: 'date',  dateFormat: 'n/j h:ia', defaultValue: undefined}
    ],
    idProperty: 'company'
});
        Ext.create('Ext.form.Panel', {
            //layout: '',
			width:500,
				renderTo: Ext.getBody(),
			
            items: [
         {
					xtype: 'form',
			        layout: 'form',
			        collapsible: true,
			        id: 'simpleForm',
				    url: 'save-form.php',
				    frame: true,
			        title: 'Simple Form',
			        bodyPadding: '50 50 0 ', // top left bottom right
			        width: 500, 
			        fieldDefaults: {
		            msgTarget: 'side',
		            labelWidth: 5,
						width: 500,
				},
			    defaultType: 'textfield',
		        items: [
				{
					fieldLabel: 'First Name',
					
		            name: 'first',
				    allowBlank:false
				},
				{
					fieldLabel: 'Last Name',
		            
				    name: 'last'
		        },
				{	
		            fieldLabel: 'Company',
				    name: 'company'
		        }, 
				{
		            fieldLabel: 'Email',
				
		            name: 'email',
				    vtype:'email'
		        }, 
				{
		            fieldLabel: 'DOB',
				    name: 'dob',
			        xtype: 'datefield'
		        },
				{
			        fieldLabel: 'Age',
					name: 'age',
		            xtype: 'numberfield',
				    minValue: 0,
		            maxValue: 100
				}
            ]
        }]

					
})}});