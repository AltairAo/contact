/**
 * This view is an example list of people.
 */
Ext.define('app.view.main.List', {
    extend: 'Ext.grid.Grid',
    xtype: 'mainlist',

    requires: [
        'app.store.Personnel'
    ],

    title: 'Contactor',

    store: {
        type: 'personnel'
    },

    columns: [
        { text: 'Name',  dataIndex: 'name', width: 100 },
        { text: 'Phone', dataIndex: 'phoneno', width: 100 },
        { text: 'Location', dataIndex: 'location', width: 100 }
        
    ],

    listeners: {
        select: 'onItemSelected'
    }
});
