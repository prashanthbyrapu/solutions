Ext.application({
    name: 'HelloExt',
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'form',
			bodyPadding:'50px 5px 0',
            //var numval(value)
			items: [
                {
                    title: 'Hello Ext',
                },
				{	
					xtype:'fieldset',
		            title: 'Personal Information',
				    collapsible: true,
		            defaultType: 'textfield',
						width: '75%',
						//height: '100%',
				    layout: {
						type: 'vbox',
						pack: 'start',
						align: 'stretch'
					},
		            /*defaults: {
				        anchor: '40%'
		            },*/
		            items :[{
				        fieldLabel: 'First Name',
						name: 'Fname',
						allowBlank:'false',
						
						},{
		                fieldLabel: 'Last Name',
		                name: 'lname',
						
			            },
						{
		                fieldLabel: 'Mobile',
		                name: 'mobile',
						
			            },
						{
						fieldLabel: 'Fax',
		                name: 'fax',
						
			            }
		                ],
					
					
				},
				{	
					xtype:'fieldset',
		            title: 'Adress',
				    collapsible: true,
		            defaultType: 'textarea',
				    layout: 'anchor',
		            defaults: {
				        anchor: '50%'
		            },
		            items :[{
				        fieldLabel: 'Home Address',
						name: 'haddr',
						AllowBlank:'false'
						},{
		                fieldLabel: 'Office Adress',
		                name: 'oaddr'
			            },{
						xtype:'textfield',
		                fieldLabel: 'Email:',
		                name: 'email',
						vtype:'email'
			            }
		                
				
				
						]
				}

						]
		           
								
        });
    }
});