Ext.define('app.view.main.List', {
    extend: 'Ext.grid.Panel',
    xtype: 'mainlist',
    title:'Personal',
    id: 'ContactList',
	initComponent : function() {
		var me = this;
		//var name = null;
		Ext.define('ModelList', {
			extend : 'Ext.data.Model',
			idProperty : 'Userid',
			fields : [ 'Userid','name','phoneno','location' ]
		});
		var store = Ext.create('Ext.data.Store', {
			model : 'ModelList',
			  autoLoad : true,
				remoteSort : true,
				proxy : {
					type : 'ajax',
					url : '/contact/test',
					extraParams : me.extraParams || null,
					reader : {
						type : 'json',
						root : 'list',
						totalProperty : 'totalRecord',
						successProperty : "success"
					}
				},
				sorters : [ {
					property : 'name',
					direction : 'ASC'
				} ]
		});
		var columns=[{
			text : 'id',
			dataIndex : 'Userid',
			hidden: true
		},{ 
			text: 'Name',  
			dataIndex: 'name' 
		},{ 
			text: 'Phoneno', 
			dataIndex: 'phoneno',
			flex: 1 
		},{
			text: 'Location', 
			dataIndex: 'location',
			flex: 1 
		}];
		var toolbars=Ext.create('Ext.toolbar.Toolbar', {
			items : [ {
				xtype : 'button',
				iconCls: 'fa-home',
				text : 'Add',
				handler : function(btn, eventObj) {
					
					var win=Ext.create('Ext.window.Window', {
						layout: {
				            align: 'fit'
				        },
				        title : 'AddContact',
			    		items:[{
			    			xtype : 'textfield',
			    			fieldLabel : 'Name',
			    			id: 'name'
			    			
			    		},{
			    			xtype : 'textfield',
			    			fieldLabel : 'Phoneno',
			    			id : 'phoneno'
			    			
			    		},{
			    			xtype : 'textfield',
			    			fieldLabel : 'Location',
			    			id : 'location'
			    			
			    		},{
			    			xtype : 'button',
			    			text  : 'Confirm',
			    			handler : function(){
			    				var name = Ext.getCmp('name').getValue();
			    				var phoneno = Ext.getCmp('phoneno').getValue();
			    				var location = Ext.getCmp('location').getValue();
			    				Ext.Ajax.request({
			    					url : '/contact/add',
			    					params : {
			    						name : name,
			    						phoneno: phoneno,
			    						location: location
			    					},
			    					method : "POST",
			    					success : function(response) {
			    						store.reload();
			    					},
			    					failure : function(response) {
			    						store.reload();
			    					}
			    				});
			    				
			    				win.close();
			    			}
			    		},{
			    			xtype : 'button',
			    			text  : 'Cancel',
			    			handler : function(){
			    				win.close();
			    			}
			    			
			    		}]
			    	});
			    	win.show();
				}
			}, {
				xtype : 'button',
				text : 'Modify',
				iconCls: 'fa-home',
				handler : function(btn, eventObj) {
					var grid = Ext.getCmp("ContactList");
					var userid = grid.getSelectionModel().getSelection()[0].get('Userid');
					var name = grid.getSelectionModel().getSelection()[0].get('name');
					var phoneno = grid.getSelectionModel().getSelection()[0].get('phoneno');
					var location = grid.getSelectionModel().getSelection()[0].get('location');
					
					var win=Ext.create('Ext.window.Window', {
						layout: {
				            align: 'fit'
				        },
				        title : 'ModifyContact',
			    		items:[{
			    			xtype : 'textfield',
			    			fieldLabel : 'Id',
			    			id: 'id',
			    			hidden : true,
			    			value : userid
			    		},{
			    			xtype : 'textfield',
			    			fieldLabel : 'Name',
			    			id: 'name',
			    			value : name
			    			
			    		},{
			    			xtype : 'textfield',
			    			fieldLabel : 'Phoneno',
			    			id : 'phoneno',
			    			value : phoneno
			    			
			    		},{
			    			xtype : 'textfield',
			    			fieldLabel : 'Location',
			    			id : 'location',
			    			value : location
			    			
			    		},{
			    			xtype : 'button',
			    			text  : 'Confirm',
			    			handler : function(){
			    				var id = Ext.getCmp('id').getValue();
			    				var name = Ext.getCmp('name').getValue();
			    				var phoneno = Ext.getCmp('phoneno').getValue();
			    				var location = Ext.getCmp('location').getValue();
			    				Ext.Ajax.request({
			    					url : '/contact/modify',
			    					params : {
			    						id : id,
			    						name : name,
			    						phoneno: phoneno,
			    						location: location
			    					},
			    					method : "POST",
			    					success : function(response) {
			    						store.reload();
			    					},
			    					failure : function(response) {
			    						store.reload();
			    					}
			    				});
			    				
			    				win.close();
			    			}
			    		},{
			    			xtype : 'button',
			    			text  : 'Cancel',
			    			handler : function(){
			    				win.close();
			    			}
			    			
			    		}]
			    	});
			    	win.show();
				}
				
			}, {
				xtype : 'button',
				text : 'Delete',
				iconCls: 'fa-home',
				handler: function(){
					var grid = Ext.getCmp("ContactList");
					if(grid!=null){
						var userid = grid.getSelectionModel().getSelection()[0].get('Userid');
						Ext.Ajax.request({
							url : '/contact/delete',
							params : {
								id : userid
							},
							method : "POST",
							success : function(response) {
								store.reload();
							},
							failure : function(response) {
								store.reload();
							}
					
						});
					}else{
						alert('Please select a row');
					}
				}
			} ,{
				xtype : 'button',
				text : 'Search',
				iconCls: 'fa-home',
				handler : function(){
					var win=Ext.create('Ext.window.Window', {
						layout: {
				            align: 'fit'
				        },
				        title : 'SearchContact',
			    		items:[{
			    			xtype : 'textfield',
			    			fieldLabel : 'Name',
			    			id: 'name'
			    			
			    		},{
			    			xtype : 'button',
			    			text  : 'Search',
			    			handler : function(btn, eventObj){	    					
			    				var params={
			    						name : Ext.getCmp('name').getValue()
			    				};	 
			    				Ext.apply(store.proxy.extraParams, params);
			    				//alert(params.name);
			    				//alert(store.proxy.extraParams);
			    				store.reload();	    				
			    				win.close();
			    			}
			    		},{
			    			xtype : 'button',
			    			text  : 'Cancel',
			    			handler : function(){
			    				win.close();
			    			}
			    			
			    		}]
			    	});
			    	win.show();
				}
			}]
		});
		Ext.apply(this,{
			store : store,
			tbar : toolbars,
			columns : columns,
			bbar :  Ext.create('Ext.PagingToolbar', {
				store : me.getStore(),
				displayInfo : true,
				pageSize: 20
			}),
			
			
		});
		store.loadPage(1);

		this.callParent(arguments);
	}
});
