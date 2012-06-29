Ext.define('MyApp.view.MyPanel', {
    extend: 'Ext.panel.Panel',

    height: 356,
    width: 412,
    layout: {
        type: 'absolute'
    },
    title: 'Address',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'tabpanel',
                    activeTab: 0,
                    items: [
                        {
                            xtype: 'panel',
                            title: 'Home'
                        },
                        {
                            xtype: 'panel',
                            title: 'Office'
                        },
                        {
                            xtype: 'panel',
                            title: 'Emergency',
                            titleCollapse: true
                        }
                    ]
                },
                {
                    xtype: 'textareafield',
                    height: 90,
                    width: 360,
                    fieldLabel: 'Address',
                    labelWidth: 50,
                    x: 10,
                    y: 30
                },
                {
                    xtype: 'textfield',
                    width: 360,
                    fieldLabel: 'Phone 1',
                    labelWidth: 50,
                    x: 10,
                    y: 140
                },
                {
                    xtype: 'textfield',
                    width: 360,
                    fieldLabel: 'Phone 2',
                    labelWidth: 50,
                    x: 10,
                    y: 170
                },
                {
                    xtype: 'textfield',
                    width: 360,
                    fieldLabel: 'e-mail',
                    labelWidth: 50,
                    vtype: 'email',
                    x: 10,
                    y: 200
                }
            ]
        });

        me.callParent(arguments);
    }

});